package com.yuepaijie;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.yuepaijie.kit.file.aliyunoss.AliyunOssFileClient;
import com.yuepaijie.kit.redis.RedisKit;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AliyunOssTest {

  @Autowired RedisKit redisKit;

  @Autowired AliyunOssFileClient aliyunOssFileClient;

  @Test
  public void testoss(){
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "<yourAccessKeyId>";
    String accessKeySecret = "<yourAccessKeySecret>";

    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 创建PutObjectRequest对象。
    PutObjectRequest putObjectRequest = new PutObjectRequest("<yourBucketName>", "<yourObjectName>", new File("<yourLocalFile>"));

    // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
    // ObjectMetadata metadata = new ObjectMetadata();
    // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
    // metadata.setObjectAcl(CannedAccessControlList.Private);
    // putObjectRequest.setMetadata(metadata);

    // 上传文件。
    ossClient.putObject(putObjectRequest);

    // 关闭OSSClient。
    ossClient.shutdown();
  }

  @Test
  public void testoss1() throws IOException {
    aliyunOssFileClient.deletePublic("3212/s300 (1).png");
    aliyunOssFileClient.deletePublic("3212/");
    System.out.println("ennnddddd666666");
  }
}
