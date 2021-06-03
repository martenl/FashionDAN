package de.martenl.fashiondan.admin.service;

import de.martenl.fashiondan.adserver.upload.AdUploadReply;
import de.martenl.fashiondan.adserver.upload.AdUploadRequest;
import de.martenl.fashiondan.adserver.upload.AdUploaderGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdUploadService {

    private final Logger logger = LoggerFactory.getLogger(AdUploadService.class);

    public void uploadAd() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("ad-server", 9090)
                .usePlaintext()
                .build();
        AdUploaderGrpc.AdUploaderBlockingStub stub = AdUploaderGrpc.newBlockingStub(channel);

        AdUploadReply adUploadReply = stub.uploadAd(
                AdUploadRequest.newBuilder()
                        .setName("my cool ad")
                        .build());
        logger.info(adUploadReply.getMessage());
        channel.shutdown();
    }
}
