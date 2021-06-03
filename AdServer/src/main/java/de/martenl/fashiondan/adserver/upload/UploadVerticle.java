package de.martenl.fashiondan.adserver.upload;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOGGER.info("Starting Upload verticle");
        // RedisClient redisClient = RedisClient.create(vertx, new RedisOptions(config()));
        // redis://[username:password@][host][:port][/[database]
        RedisOptions redisOptions = new RedisOptions(config().getJsonObject("redis"));
        final Redis redis = Redis.createClient(vertx, redisOptions);
        final RedisAPI redisClient = RedisAPI.api(redis);

        UploadService uploadService = new UploadService(redisClient);

        VertxServer rpcServer = VertxServerBuilder
                .forAddress(vertx, "0.0.0.0", 9090)
                .addService(new MyService())
                .build();
        rpcServer.start();
    }

    class MyService extends AdUploaderGrpc.AdUploaderVertxImplBase {

        @Override
        public void uploadAd(AdUploadRequest request, Promise<AdUploadReply> response) {
            super.uploadAd(request, response);
            LOGGER.info(request.getName());
            response.complete(AdUploadReply.newBuilder().setMessage("nice ad, bro").build());
        }
    }
}
