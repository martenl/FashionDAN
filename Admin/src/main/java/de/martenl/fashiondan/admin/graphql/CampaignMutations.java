package de.martenl.fashiondan.admin.graphql;

import de.martenl.fashiondan.admin.domain.Campaign;
import de.martenl.fashiondan.admin.service.AdUploadService;
import de.martenl.fashiondan.admin.service.CampaignService;
import de.martenl.fashiondan.admin.service.UploadService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;

public class CampaignMutations implements GraphQLMutationResolver {

    private final CampaignService campaignService;
    private final UploadService uploadService;
    private final AdUploadService adUploadService;

    @Autowired
    public CampaignMutations(final CampaignService campaignService, final UploadService uploadService, AdUploadService adUploadService) {
        this.campaignService = campaignService;
        this.uploadService = uploadService;
        this.adUploadService = adUploadService;
    }

    public Campaign createCampaign(String name) {
        final var campaign = campaignService.createCampaign(name, "yesterday", "tomorrow");
        return campaign;
    }

    public boolean startCampaign(final String id, final int startDate) {
        System.out.printf("campaign %s starts at %d%n", id, startDate);
        adUploadService.uploadAd();
        return true;
    }

    public String uploadCreative(final Part creativeFile, final DataFetchingEnvironment environment) {
        final Part uploadedFile = environment.getArgument("creativeFile");
        uploadService.storeUploadedFile(uploadedFile);
        return "id-123";
    }
}
