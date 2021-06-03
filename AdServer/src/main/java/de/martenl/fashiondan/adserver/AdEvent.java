package de.martenl.fashiondan.adserver;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class AdEvent {

    private final AdEventType type;
    private final String referer;
    private final String redirectedTo;
    private final LocalDateTime timeStamp;
    private final String slotId;
    private final String creativeId;
    private final String requestId;
    private final String ip;
    private final Map<String, String> additionalInfo;

    public AdEvent(final AdEventType type,
                   final String referer,
                   final String redirectedTo,
                   final LocalDateTime timeStamp,
                   final String slotId,
                   final String creativeId,
                   final String requestId,
                   final String ip) {
        this.type = type;
        this.referer = referer;
        this.redirectedTo = redirectedTo;
        this.timeStamp = timeStamp;
        this.slotId = slotId;
        this.creativeId = creativeId;
        this.requestId = requestId;
        this.ip = ip;
        additionalInfo = Map.of();
    }

    /*public static AdEvent of(final String requestId) {

        return new AdEvent(
                type,
                referer,
                destination,
                timeStamp,
                slotId,
                creativeId,
                requestId,
                ip);
    }*/

    public static AdEvent of(final RoutingContext routingContext,
                             final AdEventType type,
                             final LocalDateTime timeStamp,
                             final String destination) {
        final String campaignId = routingContext.request().getParam("campaignId");
        final String slotId = routingContext.request().getParam("slotId");
        final String creativeId = routingContext.request().getParam("creativeId");
        final String requestId = routingContext.request().getParam("requestId");
        final String redirectUrl = routingContext.request().getParam("redirectUrl");
        final String ip = routingContext.request().connection().remoteAddress().host();
        final String referer = routingContext.request().headers().get(HttpHeaders.REFERER);

        return new AdEvent(
                type,
                referer,
                destination,
                timeStamp,
                slotId,
                creativeId,
                requestId,
                ip);
    }

    public AdEventType getType() {
        return type;
    }

    public String getReferer() {
        return referer;
    }

    public String getRedirectedTo() {
        return redirectedTo;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getIp() {
        return ip;
    }

    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEvent adEvent = (AdEvent) o;
        return getType() == adEvent.getType() &&
                Objects.equals(getReferer(), adEvent.getReferer()) &&
                Objects.equals(getRedirectedTo(), adEvent.getRedirectedTo()) &&
                Objects.equals(getTimeStamp(), adEvent.getTimeStamp()) &&
                Objects.equals(getSlotId(), adEvent.getSlotId()) &&
                Objects.equals(getCreativeId(), adEvent.getCreativeId()) &&
                Objects.equals(getRequestId(), adEvent.getRequestId()) &&
                Objects.equals(getIp(), adEvent.getIp()) &&
                Objects.equals(getAdditionalInfo(), adEvent.getAdditionalInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getReferer(), getRedirectedTo(), getTimeStamp(), getSlotId(), getCreativeId(), getRequestId(), getIp(), getAdditionalInfo());
    }

    @Override
    public String toString() {
        return "AdEvent{" +
                "type=" + type +
                ", referer='" + referer + '\'' +
                ", redirectedTo='" + redirectedTo + '\'' +
                ", timeStamp=" + timeStamp +
                ", slotId='" + slotId + '\'' +
                ", creativeId='" + creativeId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", ip='" + ip + '\'' +
                ", additionalInfo=" + additionalInfo +
                '}';
    }

    public static class AdEventCodec implements MessageCodec<AdEvent, AdEvent> {

        @Override
        public void encodeToWire(Buffer buffer, AdEvent adEvent) {
            var encodedJson = JsonObject.mapFrom(adEvent).encode();
            int length = encodedJson.getBytes().length;
            buffer.appendInt(length);
            buffer.appendString(encodedJson);
        }

        @Override
        public AdEvent decodeFromWire(int position, Buffer buffer) {
            var length = buffer.getInt(position);
            var from = position + 4;
            var till = from + length;
            var jsonStr = buffer.getString(from, till);
            return new JsonObject(jsonStr).mapTo(AdEvent.class);
        }

        @Override
        public AdEvent transform(AdEvent adEvent) {
            return adEvent;
        }

        @Override
        public String name() {
            return this.getClass().getSimpleName();
        }

        @Override
        public byte systemCodecID() {
            return -1;
        }
    }
}
