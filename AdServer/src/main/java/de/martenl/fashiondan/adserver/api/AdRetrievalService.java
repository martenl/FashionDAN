package de.martenl.fashiondan.adserver.api;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;

public interface AdRetrievalService {

    Future<JsonObject> retrieveAd(String supplierId);

}
