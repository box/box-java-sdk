package com.box.sdkgen.internal.utils;

import com.box.sdkgen.box.errors.BoxSDKError;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.codec.binary.Hex;

public class Hash {

  private final HashName algorithm;
  private final MessageDigest digest;

  public Hash(HashName algorithm) {
    this.algorithm = algorithm;
    try {
      this.digest = MessageDigest.getInstance(getAlgorithmName(algorithm));
    } catch (NoSuchAlgorithmException ae) {
      throw new BoxSDKError("Digest algorithm not found", ae);
    }
  }

  public void updateHash(byte[] data) {
    digest.update(data);
  }

  public String digestHash(String encoding) {
    byte[] digestBytes = digest.digest();
    if ("hex".equals(encoding)) {
      return Hex.encodeHexString(digestBytes);
    }
    return Base64.getEncoder().encodeToString(digestBytes);
  }

  public HashName getAlgorithm() {
    return algorithm;
  }

  private static String getAlgorithmName(HashName algorithm) {
    switch (algorithm) {
      case SHA1:
        return "SHA-1";
      case SHA512:
        return "SHA-512";
      default:
        throw new BoxSDKError("Digest algorithm not supported");
    }
  }
}
