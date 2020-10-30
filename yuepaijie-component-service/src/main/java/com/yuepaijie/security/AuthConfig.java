package com.yuepaijie.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "yuepaijie.auth")
public class AuthConfig {

  private int timeoutMinute;
}
