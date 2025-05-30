package com.box.sdkgen.schemas.outcome;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.collaboratorvariable.CollaboratorVariable;
import com.box.sdkgen.schemas.completionrulevariable.CompletionRuleVariable;
import com.box.sdkgen.schemas.rolevariable.RoleVariable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Outcome extends SerializableObject {

  protected final String id;

  protected CollaboratorVariable collaborators;

  @JsonProperty("completion_rule")
  protected CompletionRuleVariable completionRule;

  @JsonProperty("file_collaborator_role")
  protected RoleVariable fileCollaboratorRole;

  @JsonProperty("task_collaborators")
  protected CollaboratorVariable taskCollaborators;

  protected RoleVariable role;

  public Outcome(@JsonProperty("id") String id) {
    super();
    this.id = id;
  }

  protected Outcome(OutcomeBuilder builder) {
    super();
    this.id = builder.id;
    this.collaborators = builder.collaborators;
    this.completionRule = builder.completionRule;
    this.fileCollaboratorRole = builder.fileCollaboratorRole;
    this.taskCollaborators = builder.taskCollaborators;
    this.role = builder.role;
  }

  public String getId() {
    return id;
  }

  public CollaboratorVariable getCollaborators() {
    return collaborators;
  }

  public CompletionRuleVariable getCompletionRule() {
    return completionRule;
  }

  public RoleVariable getFileCollaboratorRole() {
    return fileCollaboratorRole;
  }

  public CollaboratorVariable getTaskCollaborators() {
    return taskCollaborators;
  }

  public RoleVariable getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Outcome casted = (Outcome) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(collaborators, casted.collaborators)
        && Objects.equals(completionRule, casted.completionRule)
        && Objects.equals(fileCollaboratorRole, casted.fileCollaboratorRole)
        && Objects.equals(taskCollaborators, casted.taskCollaborators)
        && Objects.equals(role, casted.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, collaborators, completionRule, fileCollaboratorRole, taskCollaborators, role);
  }

  @Override
  public String toString() {
    return "Outcome{"
        + "id='"
        + id
        + '\''
        + ", "
        + "collaborators='"
        + collaborators
        + '\''
        + ", "
        + "completionRule='"
        + completionRule
        + '\''
        + ", "
        + "fileCollaboratorRole='"
        + fileCollaboratorRole
        + '\''
        + ", "
        + "taskCollaborators='"
        + taskCollaborators
        + '\''
        + ", "
        + "role='"
        + role
        + '\''
        + "}";
  }

  public static class OutcomeBuilder {

    protected final String id;

    protected CollaboratorVariable collaborators;

    protected CompletionRuleVariable completionRule;

    protected RoleVariable fileCollaboratorRole;

    protected CollaboratorVariable taskCollaborators;

    protected RoleVariable role;

    public OutcomeBuilder(String id) {
      this.id = id;
    }

    public OutcomeBuilder collaborators(CollaboratorVariable collaborators) {
      this.collaborators = collaborators;
      return this;
    }

    public OutcomeBuilder completionRule(CompletionRuleVariable completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public OutcomeBuilder fileCollaboratorRole(RoleVariable fileCollaboratorRole) {
      this.fileCollaboratorRole = fileCollaboratorRole;
      return this;
    }

    public OutcomeBuilder taskCollaborators(CollaboratorVariable taskCollaborators) {
      this.taskCollaborators = taskCollaborators;
      return this;
    }

    public OutcomeBuilder role(RoleVariable role) {
      this.role = role;
      return this;
    }

    public Outcome build() {
      return new Outcome(this);
    }
  }
}
