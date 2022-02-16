package com.box.sdk;

import java.util.Arrays;

final class CleanupTools {
    private CleanupTools() {
        // hiding constructor
    }

    static void removeAllowedDomains(String... names) {
        BoxCollaborationAllowlist.getAll(BoxApiProvider.jwtApiForServiceAccount())
            .forEach(l -> {
                if (Arrays.asList(names).contains(l.getDomain())) {
                    l.getResource().delete();
                }
            });
    }

    static void deleteFile(BoxFile file) {
        if (file != null) {
            file.delete();
        }
    }

    static void deleteFolder(BoxFolder folder) {
        if (folder != null) {
            folder.delete(true);
        }
    }
}
