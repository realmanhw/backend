package com.yuepaijie.kit.file;

import java.io.File;
import java.util.UUID;
import org.springframework.util.StringUtils;

public class FileUtil {

  public static String generateUploadPath(String targetLocation, String name) {
    String fileExtension = getFileExtension(name);
    String fileName = UUID.randomUUID().toString() + "." + fileExtension;
    return targetLocation + File.separator + fileName;
  }

  private static String getFileExtension(String fileName) {
    if (StringUtils.isEmpty(fileName) || !fileName.contains(".")
        || fileName.lastIndexOf(".") == fileName.length() - 1) {
      return null;
    }
    return fileName.substring(fileName.lastIndexOf(".") + 1);
  }
}
