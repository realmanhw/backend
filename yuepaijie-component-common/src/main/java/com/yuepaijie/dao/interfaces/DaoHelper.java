package com.yuepaijie.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface DaoHelper {

  default <T> T firstOrNull(List<T> list) {
    if (list == null) {
      return null;
    }
    return list.size() > 0 ? list.get(0) : null;
  }

  default <T> Optional<T> firstOrEmpty(List<T> list) {
    if (list == null) {
      return Optional.empty();
    }
    return list.size() > 0 ? Optional.of(list.get(0)) : Optional.empty();
  }
}
