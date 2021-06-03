package de.martenl.fashiondan.admin.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {


    public Query() {

    }

    public String hello(final int count) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append("Hello!");
        }
        return builder.toString();
    }
}
