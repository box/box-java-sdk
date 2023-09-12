package com.box.sdk;

/**
 * Box's ready-sign link feature enables you to create a link to a signature request that you've created from a template.
 * Use this link when you want to post a signature request on a public form — such as an email,
 * social media post, or web page — without knowing who the signers will be.
 * Note: The ready-sign link feature is limited to Enterprise Plus customers and not available to Box Verified Enterprises.
 */
public class BoxSignTemplateReadySignLink {
    private final String folderID;
    private final String instructions;
    private final boolean isActive;
    private final boolean isNofiticationDisabled;
    private final String name;
    private final String url;

    /**
     * Constructs a BoxSignTemplateReadySignLink object with the provided information.
     *
     * @param folderID               the folder ID.
     * @param instructions           the instructions.
     * @param isActive               whether the link is active or not.
     * @param isNofiticationDisabled whether the notification is disabled or not.
     * @param name                   the name.
     * @param url                    the URL.
     */
    public BoxSignTemplateReadySignLink(String folderID, String instructions, boolean isActive,
                                        boolean isNofiticationDisabled, String name, String url) {
        this.folderID = folderID;
        this.instructions = instructions;
        this.isActive = isActive;
        this.isNofiticationDisabled = isNofiticationDisabled;
        this.name = name;
        this.url = url;
    }

    /**
     * Gets the folder ID.
     *
     * @return the folder ID.
     */
    public String getFolderID() {
        return this.folderID;
    }

    /**
     * Gets the instructions.
     *
     * @return the instructions.
     */
    public String getInstructions() {
        return this.instructions;
    }

    /**
     * Gets whether the link is active or not.
     *
     * @return true if the link is active; otherwise false.
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * Gets whether the notification is disabled or not.
     *
     * @return true if the notification is disabled; otherwise false.
     */
    public boolean getIsNofiticationDisabled() {
        return this.isNofiticationDisabled;
    }

    /**
     * Gets the name of the ready-sign link.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the URL of the ready-sign link.
     *
     * @return the URL.
     */
    public String getUrl() {
        return this.url;
    }
}
