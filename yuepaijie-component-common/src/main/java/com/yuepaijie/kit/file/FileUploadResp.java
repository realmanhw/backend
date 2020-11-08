package com.yuepaijie.kit.file;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lvwenqiang 2020/8/10
 */
@Data
@AllArgsConstructor
public class FileUploadResp {

  private String title;
  private String uri;
  private String url;

  public FileUploadResp(String targetName, String uri){
    this.title = targetName;
    this.uri = uri;
  }
}
