package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import sun.plugin.WJcovUtil;

/**
 * Represents a collaboration whitelist between a user and a Box Enterprise. Collaboration Whitelist enables a Box
 * Enterprise(only available if you have Box Governance) to manage a set of approved users that can collaborate
 * with an enterprise.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("collaboration_whitelist_exempt_target")
public class BoxCollaborationWhitelistExemptTarget {

}
