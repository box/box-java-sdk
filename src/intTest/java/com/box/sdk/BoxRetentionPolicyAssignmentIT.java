package com.box.sdk;

import static com.box.sdk.BoxRetentionPolicy.BoxRetentionPolicyAction.PermanentlyDelete;
import static com.box.sdk.BoxRetentionPolicy.STATUS_ACTIVE;
import static com.box.sdk.BoxRetentionPolicyAssignment.createAssignmentToFolder;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxRetentionPolicyAssignment} related integration tests.
 */
public class BoxRetentionPolicyAssignmentIT {
    private static BoxRetentionPolicy.Info POLICY;

    @BeforeClass
    public static void beforeClass() throws Exception {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        Iterable<BoxRetentionPolicy.Info> policies = BoxRetentionPolicy.getAll(api, "policy_name", "status");
        String policyNamePrefix = BoxRetentionPolicyAssignmentIT.class.getSimpleName();
        POLICY = StreamSupport.stream(policies.spliterator(), false)
            .filter(p -> p.getPolicyName().startsWith(policyNamePrefix) && p.getStatus().equals(STATUS_ACTIVE))
            .findFirst()
            .orElseGet(() -> BoxRetentionPolicy.createFinitePolicy(
                api, randomizeName(policyNamePrefix), 365, PermanentlyDelete
            ));
        setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void attachPolicyToFileAndGetFilesUnderRetention() {
        //given
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder.Info folder = getUniqueFolder(api)
            .createFolder(randomizeName("attachPolicyToFileAndGetFilesUnderRetention"));
        BoxRetentionPolicyAssignment.Info assignment = createAssignmentToFolder(api, POLICY.getID(), folder.getID());
        BoxFile boxFile = uploadFileWithSomeContent("file_with_retention.txt", folder.getResource());

        //when
        Iterable<BoxFile.Info> filesUnderRetention =
            new BoxRetentionPolicyAssignment(api, assignment.getID()).getFilesUnderRetention(5);

        //then
        Optional<BoxFile.Info> matchingFileWithRetention =
            StreamSupport.stream(filesUnderRetention.spliterator(), false)
                .filter(f -> f.getID().equals(boxFile.getID()))
                .findFirst();
        assertTrue(matchingFileWithRetention.isPresent());

        //cleanup
        folder.getResource().delete(true);
    }

    @Test
    public void attachPolicyToFileAndGetFileVersionsUnderRetention() {
        //given
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxFolder folder = getUniqueFolder(api)
            .createFolder(randomizeName("attachPolicyToFileAndGetFileVersionsUnderRetention"))
            .getResource();
        BoxRetentionPolicyAssignment.Info assignment = createAssignmentToFolder(api, POLICY.getID(), folder.getID());
        BoxFile boxFile = uploadTwoFileVersionsToSpecifiedFolder(
            "file_with_retention.txt",
            "v1",
            "v2",
            folder,
            mock(ProgressListener.class)
        );

        //when
        Iterable<BoxFile.Info> fileUnderRetention =
            new BoxRetentionPolicyAssignment(api, assignment.getID()).getFileVersionsUnderRetention(5);

        //then
        List<BoxFile.Info> fileVersionsUnderRetention =
            StreamSupport.stream(fileUnderRetention.spliterator(), false)
                .filter(f -> f.getID().equals(boxFile.getID()))
                .collect(Collectors.toList());
        assertThat(fileVersionsUnderRetention, hasSize(1));

        //cleanup
        folder.delete(true);
    }

}