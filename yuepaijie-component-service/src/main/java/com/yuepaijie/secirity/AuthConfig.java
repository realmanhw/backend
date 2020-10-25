package com.yuepaijie.secirity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "esop.auth")
public class AuthConfig {

  private int timeoutMinute;
}
