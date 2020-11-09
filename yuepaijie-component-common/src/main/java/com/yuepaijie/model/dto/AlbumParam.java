package com.yuepaijie.model.dto;

import lombok.Data;

@Data
public class AlbumParam {

  String title;
  String description;
  Boolean isPrivate;
  Boolean isEncryption;
  String password;
}
