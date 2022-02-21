package com.box.sdk;

import static java.lang.String.format;

/**
 * Utility class to retry API actions
 */
public final class Retry {
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();

    private Retry() {
        // hide constructor in utility class
    }

    /**
     * Retries execution
     * @param toExecute Runnable to retry
     * @param retries how many times we want to retry
     * @param sleep how much miliseconds to sleep between retries
     * @throws InterruptedException when sleep was interuppted
     */
    public static void retry(Runnable toExecute, int retries, int sleep) throws InterruptedException {
        int retriesExecuted = 0;
        while (retriesExecuted < retries) {
            try {
                toExecute.run();
                break;
            } catch (BoxAPIResponseException e) {
                retriesExecuted++;
                LOGGER.debug(
                    format("Retrying [%d/%d] becasue of API Error '%s'", retriesExecuted, retries, e.getMessage())
                );
                Thread.sleep(sleep);
            }
        }
    }
}
