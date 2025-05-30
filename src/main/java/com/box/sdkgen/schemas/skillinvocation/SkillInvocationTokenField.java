package com.box.sdkgen.schemas.skillinvocation;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class SkillInvocationTokenField extends SerializableObject {

  protected SkillInvocationTokenReadField read;

  protected SkillInvocationTokenWriteField write;

  public SkillInvocationTokenField() {
    super();
  }

  protected SkillInvocationTokenField(SkillInvocationTokenFieldBuilder builder) {
    super();
    this.read = builder.read;
    this.write = builder.write;
  }

  public SkillInvocationTokenReadField getRead() {
    return read;
  }

  public SkillInvocationTokenWriteField getWrite() {
    return write;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillInvocationTokenField casted = (SkillInvocationTokenField) o;
    return Objects.equals(read, casted.read) && Objects.equals(write, casted.write);
  }

  @Override
  public int hashCode() {
    return Objects.hash(read, write);
  }

  @Override
  public String toString() {
    return "SkillInvocationTokenField{"
        + "read='"
        + read
        + '\''
        + ", "
        + "write='"
        + write
        + '\''
        + "}";
  }

  public static class SkillInvocationTokenFieldBuilder {

    protected SkillInvocationTokenReadField read;

    protected SkillInvocationTokenWriteField write;

    public SkillInvocationTokenFieldBuilder read(SkillInvocationTokenReadField read) {
      this.read = read;
      return this;
    }

    public SkillInvocationTokenFieldBuilder write(SkillInvocationTokenWriteField write) {
      this.write = write;
      return this;
    }

    public SkillInvocationTokenField build() {
      return new SkillInvocationTokenField(this);
    }
  }
}
