package de.martenl.fashiondan.admin.graphql;

import de.martenl.fashiondan.admin.domain.Campaign;
import de.martenl.fashiondan.admin.service.CampaignService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

public class CampaignQueries implements GraphQLQueryResolver {

    private final CampaignService campaignService;

    public CampaignQueries(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Cacheable
    public List<Campaign> getCampaigns(final int page) {
        return campaignService.getCampaigns(page);
    }

    @Cacheable
    public Optional<Campaign> getCampaign(final String id) {
        return campaignService.getCampaign(id);
    }
}
