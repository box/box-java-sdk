package com.box.sdk;

import static com.box.sdk.BoxRetentionPolicy.BoxRetentionPolicyAction.PermanentlyDelete;
import static com.box.sdk.BoxRetentionPolicy.STATUS_ACTIVE;
import static com.box.sdk.UniqueTestFolder.randomizeName;

import java.util.function.Supplier;
import java.util.stream.StreamSupport;

final class RetentionPolicyUtils {
    private RetentionPolicyUtils() {
        // to hide default constructor
    }

    static BoxRetentionPolicy findOrCreate(
        BoxAPIConnection api, String policyNamePrefix, Supplier<BoxRetentionPolicy.Info> createPolicy
    ) {
        Iterable<BoxRetentionPolicy.Info> policies = BoxRetentionPolicy.getAll(api, "policy_name", "status");
        BoxRetentionPolicy.Info policy = StreamSupport.stream(policies.spliterator(), false)
            .filter(p -> p.getPolicyName().startsWith(policyNamePrefix) && p.getStatus().equals(STATUS_ACTIVE))
            .findFirst()
            .orElseGet(createPolicy);
        return (BoxRetentionPolicy) policy.getResource();
    }

    static BoxRetentionPolicy getOneDayRetentionPolicy(BoxAPIConnection api) {
        return findOrCreate(
            api,
            "One day",
            () -> BoxRetentionPolicy.createFinitePolicy(api, randomizeName("One day"), 1, PermanentlyDelete)
        );
    }
}
