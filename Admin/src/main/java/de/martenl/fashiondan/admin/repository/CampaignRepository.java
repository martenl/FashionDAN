package de.martenl.fashiondan.admin.repository;

import de.martenl.fashiondan.admin.domain.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
}
