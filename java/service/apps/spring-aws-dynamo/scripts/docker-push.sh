
# Pre-requisites
# - Ensure environment variables have been properly set: AWS_ACCOUNT_ID, AWS_REGION, ECR_IMAGE_REPO
# - Ensure your AWS CLI loaded credentials have permissions to push docker images to your account's AWS Container Registry
# - Ensure the ECR repository indicated by ECR_IMAGE_REPO already exists in AWS account

#./mvnw spring-boot:build-image
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com
docker tag spring-aws-dynamo:0.0.3-SNAPSHOT ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_IMAGE_REPO}
docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${ECR_IMAGE_REPO}
