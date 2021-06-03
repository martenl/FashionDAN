package de.martenl.fashiondan.admin.config;

import de.martenl.fashiondan.admin.graphql.*;
import de.martenl.fashiondan.admin.service.*;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
public class GraphqlConfig {

    @Autowired
    CampaignService campaignService;

    @Autowired
    UserService userService;

    @Autowired
    AdUploadService adUploadService;

    @Autowired
    UploadService uploadService;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    TokenService tokenService;

    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

    @Bean
    public Query query() {
        return new Query();
    }

    @Bean
    public UserResolver userResolver() {
        return new UserResolver(tokenService);
    }

    @Bean
    public UserMutations userMutations() {
        return new UserMutations(userService, authenticationProvider);
    }

    @Bean
    public CampaignResolver campaignResolver() {
        return new CampaignResolver();
    }

    @Bean
    public CampaignQueries campaignQueries() {
        return new CampaignQueries(campaignService);
    }

    @Bean
    public CampaignMutations mutation() {
        return new CampaignMutations(campaignService, uploadService, adUploadService);
    }
}
