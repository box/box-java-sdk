package com.box.sdk.samples;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;

public final class Main {
    private static final String DEVELOPER_TOKEN = "";
    private static final int MAX_DEPTH = 1;

    private Main() { }

    public static void main(String[] args) {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.OFF);

        BoxAPIConnection api = new BoxAPIConnection(DEVELOPER_TOKEN);

        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        listFolder(rootFolder, 0);
    }

    private static void listFolder(BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }

            System.out.println(indent + itemInfo.getName());
            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                if (depth < MAX_DEPTH) {
                    listFolder(childFolder, depth + 1);
                }
            }
        }
    }
}
