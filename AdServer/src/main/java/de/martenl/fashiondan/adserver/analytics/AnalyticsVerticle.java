package de.martenl.fashiondan.adserver.analytics;

import de.martenl.fashiondan.adserver.AdEvent;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AnalyticsVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyticsVerticle.class);
    private KafkaProducer<String, String> producer;

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOGGER.info("Starting Analytics verticle");
        super.start(startPromise);

        Vertx myVertx = getVertx();
        EventBus eventBus = myVertx.eventBus();
        eventBus.consumer("ad-event", this::handleAdEvent);

        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "kafka:9093");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");

        // use producer for interacting with Apache Kafka
        producer = KafkaProducer.create(vertx, config);
    }

    private void handleAdEvent(Message<AdEvent> message) {
        switch (message.body().getType()) {
            case SERVED:
                LOGGER.info("An ad was served");
                producer.send(KafkaProducerRecord.create("ad-served", message.body().getRequestId()));
                break;
            case IMPRESSION:
                LOGGER.info("An ad was seen");
                break;
            case CLICK:
                LOGGER.info("An ad was clicked");
                break;
        }
    }
}
