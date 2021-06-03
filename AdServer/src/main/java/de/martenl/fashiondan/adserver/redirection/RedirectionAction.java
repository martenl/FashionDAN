package de.martenl.fashiondan.adserver.redirection;

public class RedirectionAction {

    private final String destination;
    private final String campignId;

    public RedirectionAction(final String destination, final String campignId) {
        this.destination = destination;
        this.campignId = campignId;
    }

    public String getDestination() {
        return destination;
    }

    public String getCampignId() {
        return campignId;
    }
}
