package de.martenl.fashiondan.adserver.redirection;

import io.vertx.core.Future;

public interface RedirectionService {

    Future<RedirectionAction> getRedirectionAction(RedirectionRequest redirectionRequest);
}
