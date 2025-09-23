package com.box.sdkgen.comments;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.comments.CreateCommentRequestBody;
import com.box.sdkgen.managers.comments.CreateCommentRequestBodyItemField;
import com.box.sdkgen.managers.comments.CreateCommentRequestBodyItemTypeField;
import com.box.sdkgen.managers.comments.UpdateCommentByIdRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.commentfull.CommentFull;
import com.box.sdkgen.schemas.comments.Comments;
import com.box.sdkgen.schemas.files.Files;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

public class CommentsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testComments() {
    int fileSize = 256;
    String fileName = getUuid();
    InputStream fileByteStream = generateByteStream(fileSize);
    String parentId = "0";
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        fileName, new UploadFileRequestBodyAttributesParentField(parentId)),
                    fileByteStream));
    String fileId = uploadedFiles.getEntries().get(0).getId();
    Comments comments = client.getComments().getFileComments(fileId);
    assert comments.getTotalCount() == 0;
    String message = "Hello there!";
    CommentFull newComment =
        client
            .getComments()
            .createComment(
                new CreateCommentRequestBody(
                    message,
                    new CreateCommentRequestBodyItemField(
                        fileId, CreateCommentRequestBodyItemTypeField.FILE)));
    assert newComment.getMessage().equals(message);
    assert newComment.getIsReplyComment() == false;
    assert newComment.getItem().getId().equals(fileId);
    CommentFull newReplyComment =
        client
            .getComments()
            .createComment(
                new CreateCommentRequestBody(
                    message,
                    new CreateCommentRequestBodyItemField(
                        newComment.getId(), CreateCommentRequestBodyItemTypeField.COMMENT)));
    assert newReplyComment.getMessage().equals(message);
    assert newReplyComment.getIsReplyComment() == true;
    String newMessage = "Hi!";
    client
        .getComments()
        .updateCommentById(
            newReplyComment.getId(),
            new UpdateCommentByIdRequestBody.Builder().message(newMessage).build());
    Comments newComments = client.getComments().getFileComments(fileId);
    assert newComments.getTotalCount() == 2;
    assert newComments.getEntries().get(1).getMessage().equals(newMessage);
    CommentFull receivedComment = client.getComments().getCommentById(newComment.getId());
    assert receivedComment.getMessage().equals(newComment.getMessage());
    client.getComments().deleteCommentById(newComment.getId());
    assertThrows(
        RuntimeException.class, () -> client.getComments().getCommentById(newComment.getId()));
    client.getFiles().deleteFileById(fileId);
  }
}
