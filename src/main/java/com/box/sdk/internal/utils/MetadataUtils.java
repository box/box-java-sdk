package com.box.sdk.internal.utils;

import java.util.HashMap;
import java.util.Map;

import com.box.sdk.Metadata;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 *
 */
public class MetadataUtils {
    public static Map<String, Map<String, Metadata>> parseAndPopulateMetadataMap(JsonObject jsonObject) {
        Map<String, Map<String, Metadata>> metadataMap = new HashMap<String, Map<String, Metadata>>();
        //Parse all templates
        for (JsonObject.Member templateMember : jsonObject) {
            if (templateMember.getValue().isNull()) {
                continue;
            } else {
                String templateName = templateMember.getName();
                Map<String, Metadata> scopeMap = metadataMap.get(templateName);
                //If templateName doesn't yet exist then create an entry with empty scope map
                if (scopeMap == null) {
                    scopeMap = new HashMap<String, Metadata>();
                    metadataMap.put(templateName, scopeMap);
                }
                //Parse all scopes in a template
                for (JsonObject.Member scopeMember : templateMember.getValue().asObject()) {
                    String scope = scopeMember.getName();
                    Metadata metadataObject = new Metadata(scopeMember.getValue().asObject());
                    scopeMap.put(scope, metadataObject);
                }
            }

        }
        return metadataMap;
    }
}
