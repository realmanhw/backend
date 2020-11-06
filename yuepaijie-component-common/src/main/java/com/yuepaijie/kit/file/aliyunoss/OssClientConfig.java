package com.yuepaijie.kit.file.aliyunoss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssClientConfig {

  private String endpoint;
  private String accessKeyId;
  private String accessKeySecret;
  private String bucketNamePublic;
  private String bucketNamePrivate;
}
