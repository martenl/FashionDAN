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
        value = "by gRPC proto compiler (version 1.20.0)",
        comments = "Source: upload.proto")
public final class AdUploaderGrpc {

  public static final String SERVICE_NAME = "adupload.AdUploader";
  private static final int METHODID_UPLOAD_AD = 0;
  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<de.martenl.fashiondan.adserver.upload.AdUploadRequest,
          de.martenl.fashiondan.adserver.upload.AdUploadReply> getUploadAdMethod;
  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  private AdUploaderGrpc() {
  }

  private static <T> io.grpc.stub.StreamObserver<T> toObserver(final io.vertx.core.Handler<io.vertx.core.AsyncResult<T>> handler) {
    return new io.grpc.stub.StreamObserver<T>() {
      private volatile boolean resolved = false;

      @Override
      public void onNext(T value) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture(value));
        }
      }

      @Override
      public void onError(Throwable t) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.failedFuture(t));
        }
      }

      @Override
      public void onCompleted() {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture());
        }
      }
    };
  }

  public static io.grpc.MethodDescriptor<de.martenl.fashiondan.adserver.upload.AdUploadRequest,
          de.martenl.fashiondan.adserver.upload.AdUploadReply> getUploadAdMethod() {
    io.grpc.MethodDescriptor<de.martenl.fashiondan.adserver.upload.AdUploadRequest, de.martenl.fashiondan.adserver.upload.AdUploadReply> getUploadAdMethod;
    if ((getUploadAdMethod = AdUploaderGrpc.getUploadAdMethod) == null) {
      synchronized (AdUploaderGrpc.class) {
        if ((getUploadAdMethod = AdUploaderGrpc.getUploadAdMethod) == null) {
          AdUploaderGrpc.getUploadAdMethod = getUploadAdMethod =
                  io.grpc.MethodDescriptor.<de.martenl.fashiondan.adserver.upload.AdUploadRequest, de.martenl.fashiondan.adserver.upload.AdUploadReply>newBuilder()
                          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                          .setFullMethodName(generateFullMethodName(
                                  "adupload.AdUploader", "UploadAd"))
                          .setSampledToLocalTracing(true)
                          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  de.martenl.fashiondan.adserver.upload.AdUploadRequest.getDefaultInstance()))
                          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                  de.martenl.fashiondan.adserver.upload.AdUploadReply.getDefaultInstance()))
                          .setSchemaDescriptor(new AdUploaderMethodDescriptorSupplier("UploadAd"))
                          .build();
        }
      }
    }
    return getUploadAdMethod;
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

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static AdUploaderVertxStub newVertxStub(io.grpc.Channel channel) {
    return new AdUploaderVertxStub(channel);
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdUploaderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                  .setSchemaDescriptor(new AdUploaderFileDescriptorSupplier())
                  .addMethod(getUploadAdMethod())
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
      asyncUnimplementedUnaryCall(getUploadAdMethod(), responseObserver);
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      getUploadAdMethod(),
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
    public AdUploaderStub(io.grpc.Channel channel) {
      super(channel);
    }

    public AdUploaderStub(io.grpc.Channel channel,
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
              getChannel().newCall(getUploadAdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   *
   */
  public static final class AdUploaderBlockingStub extends io.grpc.stub.AbstractStub<AdUploaderBlockingStub> {
    public AdUploaderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    public AdUploaderBlockingStub(io.grpc.Channel channel,
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
              getChannel(), getUploadAdMethod(), getCallOptions(), request);
    }
  }

  /**
   *
   */
  public static final class AdUploaderFutureStub extends io.grpc.stub.AbstractStub<AdUploaderFutureStub> {
    public AdUploaderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    public AdUploaderFutureStub(io.grpc.Channel channel,
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
              getChannel().newCall(getUploadAdMethod(), getCallOptions()), request);
    }
  }

  /**
   *
   */
  public static abstract class AdUploaderVertxImplBase implements io.grpc.BindableService {

    /**
     *
     */
    public void uploadAd(de.martenl.fashiondan.adserver.upload.AdUploadRequest request,
                         io.vertx.core.Promise<de.martenl.fashiondan.adserver.upload.AdUploadReply> response) {
      asyncUnimplementedUnaryCall(getUploadAdMethod(), AdUploaderGrpc.toObserver(response));
    }

    @java.lang.Override
    public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
              .addMethod(
                      getUploadAdMethod(),
                      asyncUnaryCall(
                              new VertxMethodHandlers<
                                      de.martenl.fashiondan.adserver.upload.AdUploadRequest,
                                      de.martenl.fashiondan.adserver.upload.AdUploadReply>(
                                      this, METHODID_UPLOAD_AD)))
              .build();
    }
  }

  /**
   *
   */
  public static final class AdUploaderVertxStub extends io.grpc.stub.AbstractStub<AdUploaderVertxStub> {
    public AdUploaderVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    public AdUploaderVertxStub(io.grpc.Channel channel,
                               io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdUploaderVertxStub build(io.grpc.Channel channel,
                                        io.grpc.CallOptions callOptions) {
      return new AdUploaderVertxStub(channel, callOptions);
    }

    /**
     *
     */
    public void uploadAd(de.martenl.fashiondan.adserver.upload.AdUploadRequest request,
                         io.vertx.core.Handler<io.vertx.core.AsyncResult<de.martenl.fashiondan.adserver.upload.AdUploadReply>> response) {
      asyncUnaryCall(
              getChannel().newCall(getUploadAdMethod(), getCallOptions()), request, AdUploaderGrpc.toObserver(response));
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

  private static final class VertxMethodHandlers<Req, Resp> implements
          io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
          io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdUploaderVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(AdUploaderVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_AD:
          serviceImpl.uploadAd((de.martenl.fashiondan.adserver.upload.AdUploadRequest) request,
                  (io.vertx.core.Promise<de.martenl.fashiondan.adserver.upload.AdUploadReply>) io.vertx.core.Promise.<de.martenl.fashiondan.adserver.upload.AdUploadReply>promise().future().setHandler(ar -> {
                    if (ar.succeeded()) {
                      ((io.grpc.stub.StreamObserver<de.martenl.fashiondan.adserver.upload.AdUploadReply>) responseObserver).onNext(ar.result());
                      responseObserver.onCompleted();
                    } else {
                      responseObserver.onError(ar.cause());
                    }
                  }));
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

  private static abstract class AdUploaderBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdUploaderBaseDescriptorSupplier() {
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return de.martenl.fashiondan.adserver.upload.AdUploadProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AdUploader");
    }
  }

  private static final class AdUploaderFileDescriptorSupplier
          extends AdUploaderBaseDescriptorSupplier {
    AdUploaderFileDescriptorSupplier() {
    }
  }

  private static final class AdUploaderMethodDescriptorSupplier
          extends AdUploaderBaseDescriptorSupplier
          implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdUploaderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }
}
