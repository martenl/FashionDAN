package de.martenl.fashiondan.admin.service;

import de.martenl.fashiondan.admin.domain.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    List<Campaign> getCampaigns(int page);

    Optional<Campaign> getCampaign(String id);

    Campaign createCampaign(String name, String startDate, String endDate);

    boolean updateCampaign(String id, String name, String startDate, String endDate);

    boolean deleteCampaign(String id);
/*
Hallo idealo Team,
hiermit möchte ich mich für die Stelle des Software Engineer für Inventory Analytics bewerben.

Ich wäre ein guter Kandidat für die Stelle, da ich über mehrere Jahre Erfahrung mit Java und Spring verfüge,
mich privat zu den Themen Machine Learning/Deep Learning weitergebildet habe und aus meiner früheren Position
bei brands4friends sowohl mit Inventory Systemen als auch mit der Automatisierung
von manuellen Arbeitsschritten vertraut bin.

Über eine Rückmeldung würde ich mich sehr freuen.

 Mit freundlichem Gruß,
 Marten Losansky
 */

//docker run -it tensorflow/tensorflow bash
}
