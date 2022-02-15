package com.box.sdk;

import java.util.Arrays;

final class CleanupTools {
    private CleanupTools() {
        // hiding constructor
    }

    static void removeAllowedDomains(String... names) {
        BoxCollaborationAllowlist.getAll(BoxApiProvider.ccgApiForServiceAccount())
            .forEach(l -> {
                if (Arrays.asList(names).contains(l.getDomain())) {
                    l.getResource().delete();
                }
            });
    }
}
