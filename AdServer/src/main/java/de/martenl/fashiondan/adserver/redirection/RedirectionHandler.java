package de.martenl.fashiondan.adserver.redirection;

import de.martenl.fashiondan.adserver.AdEvent;
import de.martenl.fashiondan.adserver.AdEventType;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;

import java.time.Clock;
import java.time.LocalDateTime;

public class RedirectionHandler implements Handler<RoutingContext> {

    private final RedirectionService redirectionService;
    private final Clock clock;
    private final EventBus eventBus;

    public RedirectionHandler(final RedirectionService redirectionService, final Clock clock, final EventBus eventBus) {
        this.redirectionService = redirectionService;
        this.clock = clock;
        this.eventBus = eventBus;
    }

    public void handle(RoutingContext routingContext) {
        final String campaignId = routingContext.request().getParam("campaignId");
        final String redirectUrl = routingContext.request().getParam("redirectUrl");

        redirectionService.getRedirectionAction(new RedirectionRequest(campaignId, redirectUrl))
                .onSuccess(
                        redirectionAction -> sendRedirection(routingContext, redirectionAction)
                ).onFailure(
                redirectionAction -> routingContext.response()
                        .setStatusCode(400)
                        .end()
        );

    }

    private void sendRedirection(RoutingContext routingContext, RedirectionAction redirectionAction) {
        final var adEvent = AdEvent.of(routingContext, AdEventType.CLICK, LocalDateTime.now(clock), redirectionAction.getDestination());
        eventBus.publish("ad-event", adEvent);
        routingContext.response()
                .putHeader("location", redirectionAction.getDestination())
                .setStatusCode(307)
                .end();
    }
}
