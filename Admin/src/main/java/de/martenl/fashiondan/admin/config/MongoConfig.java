package de.martenl.fashiondan.admin.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Bean
    public MongoClient mongo() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyToClusterSettings(block -> block.hosts(List.of(new ServerAddress("mongo", 27017))))
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(this.mongoClient(), "fashionDAN");
    }

    @Override
    protected String getDatabaseName() {
        return "fashionDAN";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyToClusterSettings(block -> block.hosts(List.of(new ServerAddress("mongo", 27017))));
    }
}
