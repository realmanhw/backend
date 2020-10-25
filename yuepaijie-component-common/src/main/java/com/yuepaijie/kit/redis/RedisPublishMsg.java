package com.yuepaijie.kit.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RedisPublishMsg {

  private String msgId;

  private String msgContent;
}
