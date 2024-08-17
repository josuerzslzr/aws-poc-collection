package com.josue.cdk;

import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class CdkEcsFargateApp {
    public static void main(final String[] args) {
        App app = new App();

        System.out.println("CDK_DEFAULT_ACCOUNT => " + System.getenv("CDK_DEFAULT_ACCOUNT"));
        System.out.println("CDK_DEFAULT_REGION => " + System.getenv("CDK_DEFAULT_REGION"));
        System.out.println("ECR_IMAGE_REPO => " + System.getenv("ECR_IMAGE_REPO"));

        new CdkEcsFargateStack(app, "CdkEcsFargateStack", StackProps.builder()

                .env(Environment.builder()
                        .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                        .region(System.getenv("CDK_DEFAULT_REGION"))
                        .build())

                .build());

        app.synth();
    }
}
