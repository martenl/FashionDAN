package de.martenl.fashiondan.adserver.redirection;

public class RedirectionRequest {

    private final String id;
    private final String redirectUrl;

    public RedirectionRequest(final String id, final String redirectUrl) {
        this.id = id;
        this.redirectUrl = redirectUrl;
    }

    public String getId() {
        return id;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
