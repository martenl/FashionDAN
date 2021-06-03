package de.martenl.fashiondan.adserver.api;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toMap;

public class AdRetrievalServiceImpl implements AdRetrievalService {

    private final static Logger logger = LoggerFactory.getLogger(AdRetrievalServiceImpl.class.getName());

    private final RedisAPI redisClient;

    public AdRetrievalServiceImpl(Redis redis) {
        this.redisClient = RedisAPI.api(redis);
    }

    @Override
    public Future<JsonObject> retrieveAd(String supplierId) {
        final Promise<JsonObject> promise = Promise.promise();
        redisClient.hgetall(supplierId, getAsyncResultHandler(promise));
        return promise.future();
    }

    private Handler<AsyncResult<Response>> getAsyncResultHandler(Promise<JsonObject> promise) {
        return ar -> {
            if (ar.succeeded()) {
                Response response = ar.result();
                logger.info("Redis result: " + response.toString());
                if (response != null) {
                    var result = new JsonObject(response.getKeys().stream().collect(toMap(key -> key, key -> response.get(key).toString())));
                    promise.complete(result);
                } else {
                    logger.info("Nothing found");
                    promise.fail("Nothing found");
                }
            } else {
                logger.error("Failed to call Redis");
                promise.fail(ar.cause());
            }
        };
    }
}
