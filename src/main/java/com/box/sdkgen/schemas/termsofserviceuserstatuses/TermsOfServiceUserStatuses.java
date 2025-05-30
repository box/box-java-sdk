package com.box.sdkgen.schemas.termsofserviceuserstatuses;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.termsofserviceuserstatus.TermsOfServiceUserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class TermsOfServiceUserStatuses extends SerializableObject {

  @JsonProperty("total_count")
  protected Long totalCount;

  protected List<TermsOfServiceUserStatus> entries;

  public TermsOfServiceUserStatuses() {
    super();
  }

  protected TermsOfServiceUserStatuses(TermsOfServiceUserStatusesBuilder builder) {
    super();
    this.totalCount = builder.totalCount;
    this.entries = builder.entries;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public List<TermsOfServiceUserStatus> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermsOfServiceUserStatuses casted = (TermsOfServiceUserStatuses) o;
    return Objects.equals(totalCount, casted.totalCount) && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalCount, entries);
  }

  @Override
  public String toString() {
    return "TermsOfServiceUserStatuses{"
        + "totalCount='"
        + totalCount
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class TermsOfServiceUserStatusesBuilder {

    protected Long totalCount;

    protected List<TermsOfServiceUserStatus> entries;

    public TermsOfServiceUserStatusesBuilder totalCount(Long totalCount) {
      this.totalCount = totalCount;
      return this;
    }

    public TermsOfServiceUserStatusesBuilder entries(List<TermsOfServiceUserStatus> entries) {
      this.entries = entries;
      return this;
    }

    public TermsOfServiceUserStatuses build() {
      return new TermsOfServiceUserStatuses(this);
    }
  }
}
