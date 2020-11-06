package com.yuepaijie.kit.file.aliyunoss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.yuepaijie.kit.file.FileUploadResp;
import com.yuepaijie.kit.file.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class AliyunOssFileClient {

  @Resource
  OssClientConfig ossClientConfig;

  private OSS ossClient;

  @PostConstruct
  public void init() {
    try {
      ossClient = new OSSClientBuilder()
          .build(ossClientConfig.getEndpoint(), ossClientConfig.getAccessKeyId(), ossClientConfig.getAccessKeySecret());
    } catch (Exception e) {
      log.error("create OSSClient error", e);
      throw e;
    }
  }

  public FileUploadResp uploadPublic(MultipartFile file, String targetLocation, String targetName) throws IOException {
    return upload(file.getInputStream(), ossClientConfig.getBucketNamePublic(), targetLocation, targetName);
  }

  public byte[] downloadPublic(String uri) throws IOException {
    return download(ossClientConfig.getBucketNamePublic(), uri);
  }

  public FileUploadResp uploadPrivate(MultipartFile file, String targetLocation, String targetName) throws IOException {
    return upload(file.getInputStream(), ossClientConfig.getBucketNamePrivate(), targetLocation, targetName);
  }

  public byte[] downloadPrivate(String uri) throws IOException {
    return download(ossClientConfig.getBucketNamePrivate(), uri);
  }

  public FileUploadResp upload(InputStream inputStream, String bucketName, String targetLocation, String targetName) {
    String uri = FileUtil.generateUploadPath(targetLocation, targetName);
    try {
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uri, inputStream);
      ossClient.putObject(putObjectRequest);
    } catch (OSSException | ClientException e) {
      log.error("upload failed:{}", e.getMessage(), e);
      throw e;
    }
    return new FileUploadResp(targetName, uri);
  }

  public byte[] download(String bucketName, String uri) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    OSSObject ossObject = ossClient.getObject(bucketName, uri);
    InputStream objectContent = ossObject.getObjectContent();
    byte[] buffer = new byte[1024];
    int read;
    while ((read = objectContent.read(buffer)) > 0) {
      outputStream.write(buffer, 0, read);
    }
    objectContent.close();
    return outputStream.toByteArray();
  }
}
