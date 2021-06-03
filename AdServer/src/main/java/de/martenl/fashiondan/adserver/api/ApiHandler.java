package de.martenl.fashiondan.adserver.api;

import de.martenl.fashiondan.adserver.AdEvent;
import de.martenl.fashiondan.adserver.AdEventType;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class ApiHandler implements Handler<RoutingContext> {

    private static final JsonObject fallBackAd = new JsonObject(
            Map.of(
                    "url", "http://www.amazon.de",
                    "creative", "https://mosaic03.ztat.net/crt/creative-content/342092d1-1efd-4b46-80f2-a80193f5e61c.jpg",
                    "cta", "Buy now"
            ));

    private final AdRetrievalService adRetrievalService;
    private final EventBus eventBus;
    private final Clock clock;

    public ApiHandler(final AdRetrievalService adRetrievalService, final EventBus eventBus, final Clock clock) {
        this.adRetrievalService = adRetrievalService;
        this.eventBus = eventBus;
        this.clock = clock;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        final String campaignId = routingContext.request().getParam("campaignId");
        adRetrievalService.retrieveAd(campaignId)
                .onSuccess(ad -> sendAd(routingContext, ad))
                .onFailure(throwable -> {
                    sendAd(routingContext, fallBackAd);
                });
    }

    private void sendAd(final RoutingContext routingContext, final JsonObject ad) {
        final String requestId = UUID.randomUUID().toString();
        final var adEvent = AdEvent.of(routingContext, AdEventType.SERVED, LocalDateTime.now(clock), ad.encode());
        sendToAnalytics(adEvent);
        routingContext.response().end(ad.toBuffer());
    }

    private void sendToAnalytics(final AdEvent adEvent) {
        eventBus.publish("ad-event", adEvent);
    }
}
