// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: upload.proto

package de.martenl.fashiondan.adserver.upload;

public final class AdUploadProto {
  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_adupload_AdUploadRequest_descriptor;
  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_adupload_AdUploadRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_adupload_AdUploadReply_descriptor;
  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_adupload_AdUploadReply_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.FileDescriptor
          descriptor;

  static {
    java.lang.String[] descriptorData = {
            "\n\014upload.proto\022\010adupload\"\037\n\017AdUploadRequ" +
                    "est\022\014\n\004name\030\001 \001(\t\" \n\rAdUploadReply\022\017\n\007me" +
                    "ssage\030\001 \001(\t2N\n\nAdUploader\022@\n\010UploadAd\022\031." +
                    "adupload.AdUploadRequest\032\027.adupload.AdUp" +
                    "loadReply\"\000B8\n%de.martenl.fashiondan.ads" +
                    "erver.uploadB\rAdUploadProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
            new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
              public com.google.protobuf.ExtensionRegistry assignDescriptors(
                      com.google.protobuf.Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
              }
            };
    com.google.protobuf.Descriptors.FileDescriptor
            .internalBuildGeneratedFileFrom(descriptorData,
                    new com.google.protobuf.Descriptors.FileDescriptor[]{
                    }, assigner);
    internal_static_adupload_AdUploadRequest_descriptor =
            getDescriptor().getMessageTypes().get(0);
    internal_static_adupload_AdUploadRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_adupload_AdUploadRequest_descriptor,
            new java.lang.String[]{"Name",});
    internal_static_adupload_AdUploadReply_descriptor =
            getDescriptor().getMessageTypes().get(1);
    internal_static_adupload_AdUploadReply_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_adupload_AdUploadReply_descriptor,
            new java.lang.String[]{"Message",});
  }

  private AdUploadProto() {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
            (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
  getDescriptor() {
    return descriptor;
  }

  // @@protoc_insertion_point(outer_class_scope)
}
