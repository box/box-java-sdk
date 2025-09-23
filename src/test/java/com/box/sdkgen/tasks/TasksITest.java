package com.box.sdkgen.tasks;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeFromString;
import static com.box.sdkgen.internal.utils.UtilsManager.dateTimeToString;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBody;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyActionField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyCompletionRuleField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyItemField;
import com.box.sdkgen.managers.tasks.CreateTaskRequestBodyItemTypeField;
import com.box.sdkgen.managers.tasks.UpdateTaskByIdRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.task.Task;
import com.box.sdkgen.schemas.tasks.Tasks;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class TasksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateUpdateGetDeleteTask() {
    Files files =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        getUuid(), new UploadFileRequestBodyAttributesParentField("0")),
                    generateByteStream(10)));
    FileFull file = files.getEntries().get(0);
    OffsetDateTime dateTime = dateTimeFromString("2035-01-01T00:00:00Z");
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
                    .dueAt(dateTime)
                    .completionRule(CreateTaskRequestBodyCompletionRuleField.ALL_ASSIGNEES)
                    .build());
    assert task.getMessage().equals("test message");
    assert task.getItem().getId().equals(file.getId());
    assert dateTimeToString(task.getDueAt()).equals(dateTimeToString(dateTime));
    Task taskById = client.getTasks().getTaskById(task.getId());
    assert taskById.getId().equals(task.getId());
    Tasks taskOnFile = client.getTasks().getFileTasks(file.getId());
    assert taskOnFile.getTotalCount() == 1;
    Task updatedTask =
        client
            .getTasks()
            .updateTaskById(
                task.getId(),
                new UpdateTaskByIdRequestBody.Builder().message("updated message").build());
    assert updatedTask.getMessage().equals("updated message");
    client.getTasks().deleteTaskById(task.getId());
    client.getFiles().deleteFileById(file.getId());
  }
}
