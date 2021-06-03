package de.martenl.fashiondan.admin.service;

import de.martenl.fashiondan.admin.domain.Campaign;
import de.martenl.fashiondan.admin.repository.CampaignRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(final CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public List<Campaign> getCampaigns(int page) {
        return campaignRepository.findAll(PageRequest.of(page, 10)).getContent();
    }

    @Override
    //@Cacheable
    public Optional<Campaign> getCampaign(final String id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign createCampaign(String name, String startDate, String endDate) {
        Random rnd = new Random();
        final var id = (new UUID(rnd.nextLong(), rnd.nextLong())).toString();
        final var campaign = new Campaign(id, name, startDate, endDate);
        return campaignRepository.insert(campaign);
    }

    @Override
    //@CacheEvict
    public boolean updateCampaign(String id, String name, String startDate, String endDate) {
        final var campaignOptional = campaignRepository.findById(id);
        if (campaignOptional.isEmpty()) return false;
        final var updatedCampaign = campaignOptional.map(oldCampaign -> new Campaign(
                oldCampaign.getId(),
                name != null ? name : oldCampaign.getName(),
                startDate != null ? startDate : oldCampaign.getStartDate(),
                endDate != null ? endDate : oldCampaign.getEndDate())).get();
        campaignRepository.save(updatedCampaign);
        return true;
    }

    @Override
    public boolean deleteCampaign(String id) {
        if (campaignRepository.existsById(id)) {
            campaignRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
