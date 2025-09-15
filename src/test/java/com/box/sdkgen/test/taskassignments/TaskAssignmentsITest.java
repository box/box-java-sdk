package com.box.sdkgen.test.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.test.commons.CommonsManager.uploadNewFile;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.taskassignments.CreateTaskAssignmentRequestBody;
import com.box.sdkgen.managers.taskassignments.CreateTaskAssignmentRequestBodyAssignToField;
import com.box.sdkgen.managers.taskassignments.CreateTaskAssignmentRequestBodyTaskField;
import com.box.sdkgen.managers.taskassignments.CreateTaskAssignmentRequestBodyTaskTypeField;
import com.box.sdkgen.managers.taskassignments.UpdateTaskAssignmentByIdRequestBody;
import com.box.sdkgen.managers.taskassignments.UpdateTaskAssignmentByIdRequestBodyResolutionStateField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBody;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyActionField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyCompletionRuleField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyItemField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyItemTypeField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.task.Task;
import com.box.sdkgen.schemas.taskassignment.TaskAssignment;
import com.box.sdkgen.schemas.taskassignments.TaskAssignments;
import com.box.sdkgen.schemas.userfull.UserFull;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class TaskAssignmentsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateUpdateGetDeleteTaskAssignment() {
    FileFull file = uploadNewFile();
    OffsetDateTime date = dateTimeFromString("2035-01-01T00:00:00Z");
    Task task =
        client
            .getTasks()
            .createTask(
                new CreateTaskRequestBody.Builder(
                        new CreateTaskRequestBodyItemField.Builder()
                            .id(file.getId())
                            .type(CreateTaskRequestBodyItemTypeField.FILE)
                            .build())
                    .action(CreateTaskRequestBodyActionField.REVIEW)
                    .message("test message")
                    .dueAt(date)
                    .completionRule(CreateTaskRequestBodyCompletionRuleField.ALL_ASSIGNEES)
                    .build());
    assert task.getMessage().equals("test message");
    assert task.getItem().getId().equals(file.getId());
    UserFull currentUser = client.getUsers().getUserMe();
    TaskAssignment taskAssignment =
        client
            .getTaskAssignments()
            .createTaskAssignment(
                new CreateTaskAssignmentRequestBody(
                    new CreateTaskAssignmentRequestBodyTaskField.Builder(task.getId())
                        .type(CreateTaskAssignmentRequestBodyTaskTypeField.TASK)
                        .build(),
                    new CreateTaskAssignmentRequestBodyAssignToField.Builder()
                        .id(currentUser.getId())
                        .build()));
    assert taskAssignment.getItem().getId().equals(file.getId());
    assert taskAssignment.getAssignedTo().getId().equals(currentUser.getId());
    TaskAssignment taskAssignmentById =
        client.getTaskAssignments().getTaskAssignmentById(taskAssignment.getId());
    assert taskAssignmentById.getId().equals(taskAssignment.getId());
    TaskAssignments taskAssignmentsOnTask =
        client.getTaskAssignments().getTaskAssignments(task.getId());
    assert taskAssignmentsOnTask.getTotalCount() == 1;
    TaskAssignment updatedTaskAssignment =
        client
            .getTaskAssignments()
            .updateTaskAssignmentById(
                taskAssignment.getId(),
                new UpdateTaskAssignmentByIdRequestBody.Builder()
                    .message("updated message")
                    .resolutionState(
                        UpdateTaskAssignmentByIdRequestBodyResolutionStateField.APPROVED)
                    .build());
    assert updatedTaskAssignment.getMessage().equals("updated message");
    assert convertToString(updatedTaskAssignment.getResolutionState()).equals("approved");
    assertThrows(
        RuntimeException.class,
        () -> client.getTaskAssignments().deleteTaskAssignmentById(taskAssignment.getId()));
    client.getFiles().deleteFileById(file.getId());
  }
}
