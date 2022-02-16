package com.box.sdk;

import java.util.Arrays;
import java.util.Optional;

final class CleanupTools {
    private CleanupTools() {
        // hiding constructor
    }

    static void removeAllowedDomains(String... names) {
        BoxCollaborationAllowlist.getAll(BoxApiProvider.jwtApiForServiceAccount())
            .forEach(l -> {
                Optional<String> isDomainMatching = Arrays.stream(names)
                    .filter(n -> l.getDomain().contains(n))
                    .findFirst();
                if (isDomainMatching.isPresent()) {
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
