package de.martenl.fashiondan.adserver.api;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.junit5.VertxExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
class ApiHandlerTest {

    Vertx vertx;
    ApiHandler apiHandler;

    @BeforeEach
    void setUp(TestContext testContext) {
        vertx = Vertx.vertx();

        vertx.deployVerticle(ApiHandler.class.getName(),
                testContext.asyncAssertSuccess());
    }

    @AfterEach
    void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    void handle() {
    }
}