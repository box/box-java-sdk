package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxRetentionPolicyAssignment.createAssignmentToFolder;
import static com.box.sdk.CleanupTools.deleteFolder;
import static com.box.sdk.UniqueTestFolder.getUniqueFolder;
import static com.box.sdk.UniqueTestFolder.randomizeName;
import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileWithSomeContent;
import static com.box.sdk.UniqueTestFolder.uploadTwoFileVersionsToSpecifiedFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/** {@link BoxRetentionPolicyAssignment} related integration tests. */
public class BoxRetentionPolicyAssignmentIT {
  private static BoxRetentionPolicy policy;

  @BeforeClass
  public static void beforeClass() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    policy = RetentionPolicyUtils.getOneDayRetentionPolicy(api);
    setupUniqeFolder();
  }

  @AfterClass
  public static void tearDown() {
    removeUniqueFolder();
  }

  @Test
  public void attachPolicyToFileAndGetFilesUnderRetentionAndDeleteAttachment()
      throws InterruptedException {
    // given
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder.Info folder =
        getUniqueFolder(api)
            .createFolder(randomizeName("attachPolicyToFileAndGetFilesUnderRetention"));
    try {
      BoxRetentionPolicyAssignment.Info assignmentInfo =
          createAssignmentToFolder(api, policy.getID(), folder.getID());
      BoxFile boxFile = uploadFileWithSomeContent("file_with_retention.txt", folder.getResource());

      // when
      BoxRetentionPolicyAssignment assignment =
          new BoxRetentionPolicyAssignment(api, assignmentInfo.getID());
      Iterable<BoxFile.Info> filesUnderRetention =
          new BoxRetentionPolicyAssignment(api, assignmentInfo.getID()).getFilesUnderRetention(5);

      // then
      List<BoxFile.Info> matchingFileWithRetention1 =
          StreamSupport.stream(filesUnderRetention.spliterator(), false)
              .filter(f -> f.getID().equals(boxFile.getID()))
              .collect(Collectors.toList());
      assertThat(matchingFileWithRetention1, hasSize(1));

      // when
      assignment.delete();
      Retry.retry(
          () -> {
            Iterable<BoxFile.Info> filesUnderRetention2 =
                new BoxRetentionPolicyAssignment(api, assignmentInfo.getID())
                    .getFilesUnderRetention(50);

            // then
            List<BoxFile.Info> matchingFileWithRetention2 =
                StreamSupport.stream(filesUnderRetention2.spliterator(), false)
                    .filter(f -> f.getID().equals(boxFile.getID()))
                    .collect(Collectors.toList());
            assertTrue(matchingFileWithRetention2.isEmpty());
          },
          10,
          3000);
    } finally {
      // cleanup
      deleteFolder(folder.getResource());
    }
  }

  @Test
  public void attachPolicyToFileAndGetFileVersionsUnderRetentionAndDeleteAttachment()
      throws InterruptedException {
    // given
    BoxAPIConnection api = jwtApiForServiceAccount();
    BoxFolder folder =
        getUniqueFolder(api)
            .createFolder(randomizeName("attachPolicyToFileAndGetFileVersionsUnderRetention"))
            .getResource();
    try {
      BoxRetentionPolicyAssignment.Info assignmentInfo =
          createAssignmentToFolder(api, policy.getID(), folder.getID());
      BoxFile boxFile =
          uploadTwoFileVersionsToSpecifiedFolder(
              "file_with_retention.txt", "v1", "v2", folder, mock(ProgressListener.class));

      // when
      BoxRetentionPolicyAssignment assignment =
          new BoxRetentionPolicyAssignment(api, assignmentInfo.getID());
      Iterable<BoxFile.Info> filesVersionsUnderRetention1 =
          assignment.getFileVersionsUnderRetention(5);

      // then
      List<BoxFile.Info> matchingFileWithRetention1 =
          StreamSupport.stream(filesVersionsUnderRetention1.spliterator(), false)
              .filter(f -> f.getID().equals(boxFile.getID()))
              .collect(Collectors.toList());
      assertThat(matchingFileWithRetention1, hasSize(1));

      // when
      assignment.delete();
      Retry.retry(
          () -> {
            Iterable<BoxFile.Info> filesVersionsUnderRetention2 =
                assignment.getFileVersionsUnderRetention(50);

            // then
            List<BoxFile.Info> matchingFileWithRetention2 =
                StreamSupport.stream(filesVersionsUnderRetention2.spliterator(), false)
                    .filter(f -> f.getID().equals(boxFile.getID()))
                    .collect(Collectors.toList());
            assertTrue(matchingFileWithRetention2.isEmpty());
          },
          10,
          3000);
    } finally {
      // cleanup
      deleteFolder(folder);
    }
  }
}
