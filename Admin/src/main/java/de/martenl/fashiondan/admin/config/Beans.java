package de.martenl.fashiondan.admin.config;

import de.martenl.fashiondan.admin.repository.CampaignRepository;
import de.martenl.fashiondan.admin.repository.UserRepository;
import de.martenl.fashiondan.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class Beans {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CampaignRepository campaignRepository;
    @Value("${upload.directory}")
    private String uploadDirectory;

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    CampaignService campaignService() {
        return new CampaignServiceImpl(campaignRepository);
    }

    @Bean
    AdUploadService adUploadService() {
        return new AdUploadService();
    }

    @Bean
    Executor executor() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    UploadService uploadService(Executor executor) {
        return new FileSystemUploadService(uploadDirectory, executor);
    }
}
