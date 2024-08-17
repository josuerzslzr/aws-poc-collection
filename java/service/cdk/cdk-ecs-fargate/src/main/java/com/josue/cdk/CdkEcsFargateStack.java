package com.josue.cdk;

import java.util.Arrays;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.dynamodb.ITable;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.ecr.IRepository;
import software.amazon.awscdk.services.ecr.Repository;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.elasticloadbalancingv2.HealthCheck;
import software.amazon.awscdk.services.elasticloadbalancingv2.Protocol;
import software.amazon.awscdk.services.iam.Effect;
import software.amazon.awscdk.services.iam.ManagedPolicy;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.constructs.Construct;

public class CdkEcsFargateStack extends Stack {
    public CdkEcsFargateStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkEcsFargateStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // Create a Fargate Service with an Application Load Balancer
        ApplicationLoadBalancedFargateService service = ApplicationLoadBalancedFargateService.Builder
                .create(this, "josue-fargate-service")
                .taskImageOptions(ApplicationLoadBalancedTaskImageOptions.builder()
                        .image(ContainerImage
                                .fromRegistry("%s.dkr.ecr.%s.amazonaws.com/%s:latest".formatted(
                                        props.getEnv().getAccount(),
                                        props.getEnv().getRegion(),
                                        System.getenv("ECR_IMAGE_REPO"))))
                        .containerPort(8080)
                        .containerName("josue-spring-ws")

                        .build())
                .publicLoadBalancer(true)
                .memoryLimitMiB(1024)
                .cpu(512)
                .build();

        // Custom Health check for spring actuator health URL
        service.getTargetGroup().configureHealthCheck(
                HealthCheck.builder()
                        .path("/actuator/health")
                        .protocol(Protocol.HTTP)
                        .timeout(Duration.seconds(35))
                        .interval(Duration.seconds(37))
                        .healthyThresholdCount(2)
                        .unhealthyThresholdCount(2)
                        .build());

        // Grant the ECS task role read permissions on the ECR repository
        IRepository ecrRepository = Repository.fromRepositoryName(this, "josue-ecr-repo",
                System.getenv("ECR_IMAGE_REPO"));

        ecrRepository.grantPull(service.getTaskDefinition().getExecutionRole());

        // Grant the container role read/write access to customer table
        ITable customerTable = Table.fromTableName(this, id, "customer");
        service.getTaskDefinition().getTaskRole().addToPrincipalPolicy(
                PolicyStatement.Builder.create()
                        .resources(Arrays.asList(customerTable.getTableArn()))
                        .effect(Effect.ALLOW)
                        .actions(Arrays.asList("dynamodb:*"))
                        .build());
    }
}
