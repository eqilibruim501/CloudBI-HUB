package com.owncloud.android;

import com.owncloud.android.datamodel.OCFile;
import com.owncloud.android.db.OCUpload;
import com.owncloud.android.operations.RemoveFileOperation;
import com.owncloud.android.utils.FileStorageUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Tests related to file uploads
 */

@RunWith(AndroidJUnit4.class)
public class UploadIT extends AbstractIT {

    @Test
    public void testEmptyUpload() {
        OCUpload ocUpload = new OCUpload(FileStorageUtils.getSavePath(account.name) + "/empty.txt",
                                         "/testUpload/empty.txt",
                                         account.name);

        uploadOCUpload(ocUpload);

        // cleanup
        new RemoveFileOperation(new OCFile("/testUpload/"),
                                false,
                                account,
                                false,
                                targetContext)
            .execute(client, getStorageManager());
    }

    @Test
    public void testNonEmptyUpload() {
        OCUpload ocUpload = new OCUpload(FileStorageUtils.getSavePath(account.name) + "/nonEmpty.txt",
                                         "/testUpload/nonEmpty.txt",
                                         account.name);

        uploadOCUpload(ocUpload);

        // cleanup
        new RemoveFileOperation(new OCFile("/testUpload/"),
                                false,
                                account,
                                false,
                                targetContext)
            .execute(client, getStorageManager());
    }

    @Test
    public void testChunkedUpload() {
        OCUpload ocUpload = new OCUpload(FileStorageUtils.getSavePath(account.name) + "/chunkedFile.txt",
                                         "/testUpload/chunkedFile.txt", account.name);

        uploadOCUpload(ocUpload);

        // cleanup
        new RemoveFileOperation(new OCFile("/testUpload/"),
                                false,
                                account,
                                false,
                                targetContext)
            .execute(client, getStorageManager());
    }

    @Test
    public void testUploadInNonExistingFolder() {
        OCUpload ocUpload = new OCUpload(FileStorageUtils.getSavePath(account.name) + "/empty.txt",
                                         "/testUpload/2/3/4/1.txt", account.name);

        uploadOCUpload(ocUpload);

        // cleanup
        new RemoveFileOperation(new OCFile("/testUpload/"),
                                false,
                                account,
                                false,
                                targetContext)
            .execute(client, getStorageManager());
    }
}
