package de.martenl.fashiondan.adserver.upload;

import io.vertx.redis.client.RedisAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UploadService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    final RedisAPI redisClient;

    public UploadService(RedisAPI redisClient) {
        this.redisClient = redisClient;
    }

    public void saveAd(final String supplyId, final String adJson) {
        redisClient.rpush(List.of(supplyId, adJson), ar -> {
            if (ar.failed()) {
                LOGGER.error("Couldn't write ad to redis." + ar.cause().getMessage());
            } else {
                LOGGER.info("Successfully written to Redis");
            }
        });
    }
}
