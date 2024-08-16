package com.josue.cdk.data;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.dynamodb.TableProps;

public class CdkDynamoStack extends Stack {
    public CdkDynamoStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkDynamoStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Table customerTable = new Table(this, "customer-table", TableProps.builder()
                .tableName("customer")
                .partitionKey(Attribute.builder()
                        .name("customerKey")
                        .type(AttributeType.STRING)
                        .build())
                /*
                 * .sortKey(Attribute.builder()
                 * .name("pk")
                 * .type(AttributeType.STRING)
                 * .build())
                 */
                .build());
    }
}
