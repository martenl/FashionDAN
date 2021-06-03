package de.martenl.fashiondan.adserver.redirection;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.core.Future;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedirectionServiceImpl implements RedirectionService {

    private final Logger logger;
    private final RedisAPI redisClient;
    private final CircuitBreaker circuitBreaker;

    public RedirectionServiceImpl(final Redis redis, CircuitBreaker circuitBreaker) {
        this.redisClient = RedisAPI.api(redis);
        this.circuitBreaker = circuitBreaker;
        this.logger = LoggerFactory.getLogger(this.getClass().getName());
    }

    @Override
    public Future<RedirectionAction> getRedirectionAction(RedirectionRequest redirectionRequest) {
        return circuitBreaker.execute(promise -> {
            redisClient.get(redirectionRequest.getId(), ar -> {
                if (ar.succeeded()) {
                    Response response = ar.result();
                    if (response != null) {
                        promise.complete(new RedirectionAction("http://www.amazon.de", redirectionRequest.getId()));
                    } else {
                        logger.info("Nothing found");
                        promise.fail("Nothing found");
                    }
                } else {
                    promise.fail(ar.cause());
                }
            });
        });
    }
}
