package de.martenl.fashiondan.admin.graphql;

import de.martenl.fashiondan.admin.domain.Campaign;
import graphql.kickstart.tools.GraphQLResolver;

public class CampaignResolver implements GraphQLResolver<Campaign> {

    public String getToken(final Campaign campaign) {
        return "my-cool-token";
    }
}
