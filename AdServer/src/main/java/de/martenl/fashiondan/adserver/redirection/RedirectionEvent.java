package de.martenl.fashiondan.adserver.redirection;

public class RedirectionEvent {

    private final String referer;
    private final String redirectedTo;
    private final long timeStamp;
    private final String slotId;
    private final String creativeId;
    private final String requestId;
    private final String ip;

    public RedirectionEvent(
            final String referer,
            final String redirectedTo,
            final long timeStamp,
            final String slotId,
            final String creativeId,
            final String requestId,
            final String ip) {
        this.referer = referer;
        this.redirectedTo = redirectedTo;
        this.timeStamp = timeStamp;
        this.slotId = slotId;
        this.creativeId = creativeId;
        this.requestId = requestId;
        this.ip = ip;
    }

    public String getReferer() {
        return referer;
    }

    public String getRedirectedTo() {
        return redirectedTo;
    }

    public long getTimeStamp() {
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
}
