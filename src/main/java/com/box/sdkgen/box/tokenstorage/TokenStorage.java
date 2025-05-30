package com.box.sdkgen.box.tokenstorage;

import com.box.sdkgen.schemas.accesstoken.AccessToken;

public interface TokenStorage {
  void store(AccessToken token);

  AccessToken get();

  void clear();
}
