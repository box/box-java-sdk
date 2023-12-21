package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;

import java.util.Arrays;
import java.util.Optional;

final class CleanupTools {
    private CleanupTools() {
        // hiding constructor
    }

    static void removeAllowedDomains(String... names) {
        BoxCollaborationAllowlist.getAll(jwtApiForServiceAccount())
            .forEach(l -> {
                Optional<String> isDomainMatching = Arrays.stream(names)
                    .filter(n -> l.getDomain().contains(n))
                    .findFirst();
                if (isDomainMatching.isPresent()) {
                    l.getResource().delete();
                }
            });
    }

    static void removeAllGroups() {
        for (BoxGroup.Info group : BoxGroup.getAllGroups(jwtApiForServiceAccount())) {
            group.getResource().delete();
        }
    }

    static void deleteFile(BoxFile file) {
        Optional.ofNullable(file).ifPresent(BoxFile::delete);
    }

    static void deleteFolder(BoxFolder folder) {
        Optional.ofNullable(folder).ifPresent(f -> f.delete(true));
    }

    static void deleteGroup(BoxGroup group) {
        if (group != null) {
            group.delete();
        }
    }

    static void deleteUser(BoxUser user) {
        if (user != null) {
            user.delete(false, false);
        }
    }
}
