package com.box.sdk.internal.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.box.sdk.Metadata;
import com.box.sdk.Representation;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Utility class for constructing metadata map from json object.
 */
public class Parsers {

    /**
     * Only static members.
     */
    protected Parsers() {
    }

    /**
     * Creates a map of metadata from json.
     * @param jsonObject metadata json object for metadata field in get /files?fileds=,etadata.scope.template response
     * @return Map of String as key a value another Map with a String key and Metadata value
     */
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

    /**
     * Parse representations from a file object response.
     * @param jsonObject representations json object in get response for /files/file-id?fields=representations
     * @return list of representations
     */
    public static List<Representation> parseRepresentations(JsonObject jsonObject) {
        List<Representation> representations = new ArrayList<Representation>();
        for (JsonValue representationJson : jsonObject.get("entries").asArray()) {
            Representation representation = new Representation(representationJson.asObject());
            representations.add(representation);
        }
        return representations;
    }
}
