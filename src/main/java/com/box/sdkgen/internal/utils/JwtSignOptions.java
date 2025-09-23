package com.box.sdkgen.internal.utils;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class JwtSignOptions {
  protected JwtAlgorithm algorithm;
  protected String audience;
  protected String issuer;
  protected String subject;
  protected String jwtid;
  protected String keyid;
  protected PrivateKeyDecryptor privateKeyDecryptor;

  public JwtSignOptions(
      JwtAlgorithm algorithm,
      String audience,
      String issuer,
      String subject,
      String jwtid,
      String keyid) {
    this(algorithm, audience, issuer, subject, jwtid, keyid, new DefaultPrivateKeyDecryptor());
  }

  public JwtSignOptions(
      EnumWrapper<JwtAlgorithm> algorithm,
      String audience,
      String issuer,
      String subject,
      String jwtid,
      String keyid) {
    this(algorithm, audience, issuer, subject, jwtid, keyid, new DefaultPrivateKeyDecryptor());
  }

  public JwtSignOptions(
      JwtAlgorithm algorithm,
      String audience,
      String issuer,
      String subject,
      String jwtid,
      String keyid,
      PrivateKeyDecryptor privateKeyDecryptor) {
    this.algorithm = algorithm;
    this.audience = audience;
    this.issuer = issuer;
    this.subject = subject;
    this.jwtid = jwtid;
    this.keyid = keyid;
    this.privateKeyDecryptor = privateKeyDecryptor;
  }

  public JwtSignOptions(
      EnumWrapper<JwtAlgorithm> algorithm,
      String audience,
      String issuer,
      String subject,
      String jwtid,
      String keyid,
      PrivateKeyDecryptor privateKeyDecryptor) {
    this.algorithm = algorithm.getEnumValue();
    this.audience = audience;
    this.issuer = issuer;
    this.subject = subject;
    this.jwtid = jwtid;
    this.keyid = keyid;
    this.privateKeyDecryptor = privateKeyDecryptor;
  }

  public JwtAlgorithm getAlgorithm() {
    return algorithm;
  }

  public String getAudience() {
    return audience;
  }

  public String getIssuer() {
    return issuer;
  }

  public String getSubject() {
    return subject;
  }

  public String getJwtid() {
    return jwtid;
  }

  public String getKeyid() {
    return keyid;
  }

  public PrivateKeyDecryptor getPrivateKeyDecryptor() {
    return privateKeyDecryptor;
  }
}
