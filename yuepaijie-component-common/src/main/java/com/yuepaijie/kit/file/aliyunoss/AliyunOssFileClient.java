package com.yuepaijie.kit.file.aliyunoss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.yuepaijie.constants.constvals.OssKeys;
import com.yuepaijie.kit.file.FileUploadResp;
import com.yuepaijie.kit.file.FileUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

  public FileUploadResp uploadPublic(MultipartFile file, String targetLocation, String title) throws IOException {
    FileUploadResp resp = upload(file.getInputStream(), ossClientConfig.getBucketNamePublic(), targetLocation, title);
    resp.setUrl(OssKeys.YUEPAIJIE_HONKONG_PUBLIC+resp.getUri());
    return resp;
  }

  public FileUploadResp upload(InputStream inputStream, String bucketName, String targetLocation, String title) {
    String uri = FileUtil.generateUploadPath(targetLocation, title);
    try {
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uri, inputStream);
      ossClient.putObject(putObjectRequest);
    } catch (OSSException | ClientException e) {
      log.error("upload failed:{}", e.getMessage(), e);
      throw e;
    }
    return new FileUploadResp(title, uri);
  }

  public byte[] downloadPublic(String uri) throws IOException {
    return download(ossClientConfig.getBucketNamePublic(), uri);
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

  public void deletePublic(String uri){
    ossClient.deleteObject(ossClientConfig.getBucketNamePublic(),uri);
  }

  public List<String> deleteBatchPublic(List<String> uriList){
    DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossClientConfig.getBucketNamePublic()).withKeys(uriList));
    List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
    return deletedObjects;
  }
}
