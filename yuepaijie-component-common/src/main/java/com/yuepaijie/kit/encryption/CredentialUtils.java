package com.yuepaijie.kit.encryption;

import com.yuepaijie.kit.thirdparty.encrypt.AESUtils;
import com.yuepaijie.kit.thirdparty.encrypt.DESUtils;

public class CredentialUtils {

  public static String credentialEncode(String cre) {
    return DESUtils.encrypt(cre, AESUtils.KEY);
  }

  public static String credentialDecode(String cre) {
    return DESUtils.decrypt(cre, AESUtils.KEY);
  }
}
