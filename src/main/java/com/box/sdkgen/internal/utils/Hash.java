package com.box.sdkgen.internal.utils;

import com.box.sdkgen.box.errors.BoxSDKError;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {

  private final HashName algorithm;
  private final MessageDigest digest;

  public Hash(HashName algorithm) {
    this.algorithm = algorithm;
    try {
      this.digest = MessageDigest.getInstance(algorithm.getValue());
    } catch (NoSuchAlgorithmException ae) {
      throw new BoxSDKError("Digest algorithm not found", ae);
    }
  }

  public void updateHash(byte[] data) {
    digest.update(data);
  }

  public String digestHash(String encoding) {
    byte[] digestBytes = digest.digest();
    return Base64.getEncoder().encodeToString(digestBytes);
  }

  public HashName getAlgorithm() {
    return algorithm;
  }
}
