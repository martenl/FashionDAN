package de.martenl.fashiondan.adserver.api;

import de.martenl.fashiondan.adserver.redirection.RedirectionHandler;
import de.martenl.fashiondan.adserver.redirection.RedirectionServiceImpl;
import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;

public class ApiVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiVerticle.class);
    private final Clock clock;
    private RedirectionHandler redirectionHandler;
    private ApiHandler apiHandler;

    public ApiVerticle(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start(startPromise);

        final Redis redis = Redis.createClient(vertx, new RedisOptions().setConnectionString("redis://redis"));
        LOGGER.info(new RedisOptions().setConnectionString("redis://redis").toJson().encodePrettily());
        final CircuitBreaker redisCircuitBreaker = CircuitBreaker.create("redis-connection-circuit-breaker", vertx,
                new CircuitBreakerOptions()
                        .setMaxFailures(5)
                        .setTimeout(2000)
                        .setFallbackOnFailure(true)
                        .setResetTimeout(10000)
        ).fallback(throwable -> {
            LOGGER.info("couldn't connect to redis");
            return throwable.getMessage();
        });

        final Router router = Router.router(vertx);

        router.route().handler(LoggerHandler.create());
        router.route("/static/*").handler(StaticHandler.create());

        apiHandler = new ApiHandler(new AdRetrievalServiceImpl(redis), vertx.eventBus(), clock);
        router.get("/ad/:campaignId").produces("application/json")
                .handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET))
                .handler(apiHandler);

        redirectionHandler = new RedirectionHandler(new RedirectionServiceImpl(redis, redisCircuitBreaker), clock, vertx.eventBus());
        router.get("/clicks/:campaignId").handler(redirectionHandler);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080,
                        "0.0.0.0",
                        res -> {
                            if (res.succeeded()) {
                            } else {
                            }
                        });
    }
}
