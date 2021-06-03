package de.martenl.fashiondan.adserver;

import de.martenl.fashiondan.adserver.analytics.AnalyticsVerticle;
import de.martenl.fashiondan.adserver.api.ApiHandler;
import de.martenl.fashiondan.adserver.api.ApiVerticle;
import de.martenl.fashiondan.adserver.redirection.RedirectionHandler;
import de.martenl.fashiondan.adserver.upload.UploadVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;

public class AdServerVerticle extends AbstractVerticle {

    private RedirectionHandler redirectionHandler;
    private ApiHandler apiHandler;

    private Logger logger;

    static public void main(final String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new AdServerVerticle());
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start();

        logger = LoggerFactory.getLogger(this.getClass());
        final Clock clock = Clock.systemDefaultZone();

        final Vertx vertx = getVertx();
        final EventBus eventBus = vertx.eventBus();
        eventBus.registerDefaultCodec(AdEvent.class, new AdEvent.AdEventCodec());

        vertx.deployVerticle(new AnalyticsVerticle());

        DeploymentOptions uploadOptions = new DeploymentOptions()
                .setConfig(config());
        vertx.deployVerticle(new UploadVerticle(), uploadOptions);

        vertx.deployVerticle(new ApiVerticle(clock));

        /*final Redis redis = Redis.createClient(vertx, new RedisOptions().setConnectionString("redis://redis"));
        final CircuitBreaker redisCircuitBreaker = CircuitBreaker.create("redis-connection-circuit-breaker", vertx,
                new CircuitBreakerOptions()
                        .setMaxFailures(5)
                        .setTimeout(2000)
                        .setFallbackOnFailure(true)
                        .setResetTimeout(10000)
        ).<String>fallback(throwable -> {
            logger.info("couldn't connect to redis");
            return throwable.getMessage();
        });

        final Router router = Router.router(vertx);

        router.route().handler(LoggerHandler.create());

        router.route("/static/*").handler(StaticHandler.create());

        apiHandler = new ApiHandler(new AdRetrievalServiceImpl(redis), eventBus, clock);
        router.get("/ad/:campaignId").produces("application/json")
                .handler(CorsHandler.create("*").allowedMethod(HttpMethod.GET))
                .handler(apiHandler);

        redirectionHandler = new RedirectionHandler(new RedirectionServiceImpl(redis, redisCircuitBreaker), clock, eventBus);
        router.get("/clicks/:campaignId").handler(redirectionHandler);

        //healthcheck
        router.get("/hello").handler(this::sayHello);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080,
                        "0.0.0.0",
                        res -> {
                            if (res.succeeded()) {
                                startPromise.complete();
                            } else {
                                startPromise.fail(res.cause());
                            }
                        });*/
    }

    private void sayHello(RoutingContext routingContext) {
        JsonObject json = new JsonObject();
        json.put("hello", "is it me u're looking for?");
        logger.info("saying hello");
        routingContext
                .response()
                .putHeader("content-type", "application/json")
                .end(json.encode());
    }
}
