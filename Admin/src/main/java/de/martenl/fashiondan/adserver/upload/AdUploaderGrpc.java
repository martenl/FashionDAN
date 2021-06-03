package de.martenl.fashiondan.adserver.upload;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 *
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.4.0)",
        comments = "Source: upload.proto")
public final class AdUploaderGrpc {

  public static final String SERVICE_NAME = "adupload.AdUploader";
  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<de.martenl.fashiondan.adserver.upload.AdUploadRequest,
          de.martenl.fashiondan.adserver.upload.AdUploadReply> METHOD_UPLOAD_AD =
          io.grpc.MethodDescriptor.<de.martenl.fashiondan.adserver.upload.AdUploadRequest, de.martenl.fashiondan.adserver.upload.AdUploadReply>newBuilder()
                  .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                  .setFullMethodName(generateFullMethodName(
                          "adupload.AdUploader", "UploadAd"))
                  .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                          de.martenl.fashiondan.adserver.upload.AdUploadRequest.getDefaultInstance()))
                  .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                          de.martenl.fashiondan.adserver.upload.AdUploadReply.getDefaultInstance()))
                  .build();
  private static final int METHODID_UPLOAD_AD = 0;
  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  private AdUploaderGrpc() {
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdUploaderStub newStub(io.grpc.Channel channel) {
    return new AdUploaderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdUploaderBlockingStub newBlockingStub(
          io.grpc.Channel channel) {
    return new AdUploaderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdUploaderFutureStub newFutureStub(
          io.grpc.Channel channel) {
    return new AdUploaderFutureStub(channel);
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdUploaderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                  .setSchemaDescriptor(new AdUploaderDescriptorSupplier())
                  .addMethod(METHOD_UPLOAD_AD)
                  .build();
        }
      }
    }
    return result;
  }

  /**
   *
   */
  public static abstract class AdUploaderImplBase implements io.grpc.BindableService {

    /**
     *
     */
    public void uploadAd(de.martenl.fashiondan.adserver.upload.AdUploadRequest request,
                         io.grpc.stub.StreamObserver<de.martenl.fashiondan.adserver.upload.AdUploadReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPLOAD_AD, responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      METHOD_UPLOAD_AD,
                      asyncUnaryCall(
                              new MethodHandlers<
                                      de.martenl.fashiondan.adserver.upload.AdUploadRequest,
                                      de.martenl.fashiondan.adserver.upload.AdUploadReply>(
                                      this, METHODID_UPLOAD_AD)))
              .build();
    }
  }

  /**
   *
   */
  public static final class AdUploaderStub extends io.grpc.stub.AbstractStub<AdUploaderStub> {
    private AdUploaderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdUploaderStub(io.grpc.Channel channel,
                           io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdUploaderStub build(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
      return new AdUploaderStub(channel, callOptions);
    }

    /**
     *
     */
    public void uploadAd(de.martenl.fashiondan.adserver.upload.AdUploadRequest request,
                         io.grpc.stub.StreamObserver<de.martenl.fashiondan.adserver.upload.AdUploadReply> responseObserver) {
      asyncUnaryCall(
              getChannel().newCall(METHOD_UPLOAD_AD, getCallOptions()), request, responseObserver);
    }
  }

  /**
   *
   */
  public static final class AdUploaderBlockingStub extends io.grpc.stub.AbstractStub<AdUploaderBlockingStub> {
    private AdUploaderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdUploaderBlockingStub(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdUploaderBlockingStub build(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
      return new AdUploaderBlockingStub(channel, callOptions);
    }

    /**
     *
     */
    public de.martenl.fashiondan.adserver.upload.AdUploadReply uploadAd(de.martenl.fashiondan.adserver.upload.AdUploadRequest request) {
      return blockingUnaryCall(
              getChannel(), METHOD_UPLOAD_AD, getCallOptions(), request);
    }
  }

  /**
   *
   */
  public static final class AdUploaderFutureStub extends io.grpc.stub.AbstractStub<AdUploaderFutureStub> {
    private AdUploaderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdUploaderFutureStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdUploaderFutureStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
      return new AdUploaderFutureStub(channel, callOptions);
    }

    /**
     *
     */
    public com.google.common.util.concurrent.ListenableFuture<de.martenl.fashiondan.adserver.upload.AdUploadReply> uploadAd(
            de.martenl.fashiondan.adserver.upload.AdUploadRequest request) {
      return futureUnaryCall(
              getChannel().newCall(METHOD_UPLOAD_AD, getCallOptions()), request);
    }
  }

  private static final class MethodHandlers<Req, Resp> implements
          io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdUploaderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdUploaderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_AD:
          serviceImpl.uploadAd((de.martenl.fashiondan.adserver.upload.AdUploadRequest) request,
                  (io.grpc.stub.StreamObserver<de.martenl.fashiondan.adserver.upload.AdUploadReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
            io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AdUploaderDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return de.martenl.fashiondan.adserver.upload.AdUploadProto.getDescriptor();
    }
  }
}
