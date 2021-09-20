package com.box.sdk;

import static com.box.sdk.UniqueTestFolder.removeUniqueFolder;
import static com.box.sdk.UniqueTestFolder.setupUniqeFolder;
import static com.box.sdk.UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * {@link BoxTask} related integration tests.
 */
public class BoxTaskIT {

    @BeforeClass
    public static void beforeClass() {
        setupUniqeFolder();
    }

    @After
    public void tearDown() {
        removeUniqueFolder();
    }

    @Test
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String fileName = "[updateInfoSucceeds] Test File.txt";
        String originalMessage = "Original message";
        String changedMessage = "Changed message";
        BoxFile uploadedFile = null;

        try {
            uploadedFile = uploadFileToUniqueFolderWithSomeContent(api, fileName);
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.DATE, 1);
            Date dueAt = calendar.getTime();

            BoxTask.Info taskInfo = uploadedFile.addTask(BoxTask.Action.REVIEW, originalMessage, dueAt);

            BoxTask task = taskInfo.getResource();
            taskInfo.setMessage(changedMessage);
            taskInfo.setDueAt(dueAt);
            task.updateInfo(taskInfo);

            assertThat(taskInfo.getMessage(), is(equalTo(changedMessage)));
            assertThat(taskInfo.getDueAt(), is(equalTo(dueAt)));
        } finally {
            if (uploadedFile != null) {
                uploadedFile.delete();
            }
        }
    }
}
