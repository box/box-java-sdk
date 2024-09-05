package com.box.sdk;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import java.net.URL;
import java.util.List;


public final class BoxAI {

    /**
     * Ask AI url.
     */
    public static final URLTemplate SEND_AI_REQUEST_URL = new URLTemplate("ai/ask");
    /**
     * Text gen AI url.
     */
    public static final URLTemplate SEND_AI_TEXT_GEN_REQUEST_URL = new URLTemplate("ai/text_gen");
    /**
     * AI agent default config url.
     */
    public static final URLTemplate AI_AGENT_DEFAULT_CONFIG_URL = new URLTemplate("ai_agent_default");

    private BoxAI() {
    }

    /**
     * Sends an AI request to supported LLMs and returns an answer specifically focused
     * on the user's question given the provided items.
     *
     * @param api    the API connection to be used by the created user.
     * @param prompt The prompt provided by the client to be answered by the LLM.
     * @param items  The items to be processed by the LLM, currently only files are supported.
     * @param mode   The mode specifies if this request is for a single or multiple items.
     * @return The response from the AI.
     */
    public static BoxAIResponse sendAIRequest(BoxAPIConnection api, String prompt, List<BoxAIItem> items, Mode mode) {
        return sendAIRequest(api, prompt, items, mode, null, null, null);
    }

    public static BoxAIResponse sendAIRequest(BoxAPIConnection api, String prompt, List<BoxAIItem> items, Mode mode,
                                              List<BoxAIDialogueEntry> dialogueHistory, BoxAIAgentAsk agent,
                                              Boolean includeCitations) {
        URL url = SEND_AI_REQUEST_URL.build(api.getBaseURL());
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("mode", mode.toString());
        requestJSON.add("prompt", prompt);

        JsonArray itemsJSON = new JsonArray();
        for (BoxAIItem item : items) {
            itemsJSON.add(item.getJSONObject());
        }
        requestJSON.add("items", itemsJSON);

        if (dialogueHistory != null) {
            JsonArray dialogueHistoryJSON = new JsonArray();
            for (BoxAIDialogueEntry dialogueEntry : dialogueHistory) {
                dialogueHistoryJSON.add(dialogueEntry.getJSONObject());
            }
            requestJSON.add("dialogue_history", dialogueHistoryJSON);
        }
        if (agent != null) {
            requestJSON.add("ai_agent", agent.getJSONObject());
        }
        if (includeCitations != null) {
            requestJSON.add("include_citations", includeCitations);
        }

        BoxJSONRequest req = new BoxJSONRequest(api, url, HttpMethod.POST);
        req.setBody(requestJSON.toString());

        try (BoxJSONResponse response = req.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return new BoxAIResponse(responseJSON);
        }
    }

    /**
     * Sends an AI request to supported LLMs and returns an answer specifically focused on the creation of new text.
     *
     * @param api    the API connection to be used by the created user.
     * @param prompt The prompt provided by the client to be answered by the LLM.
     * @param items  The items to be processed by the LLM, currently only files are supported.
     * @return The response from the AI.
     */
    public static BoxAIResponse sendAITextGenRequest(BoxAPIConnection api, String prompt, List<BoxAIItem> items) {
        return sendAITextGenRequest(api, prompt, items, null);
    }

    /**
     * Sends an AI request to supported LLMs and returns an answer specifically focused on the creation of new text.
     *
     * @param api             the API connection to be used by the created user.
     * @param prompt          The prompt provided by the client to be answered by the LLM.
     * @param items           The items to be processed by the LLM, currently only files are supported.
     * @param dialogueHistory The history of prompts and answers previously passed to the LLM.
     *                        This provides additional context to the LLM in generating the response.
     * @return The response from the AI.
     */
    public static BoxAIResponse sendAITextGenRequest(BoxAPIConnection api, String prompt, List<BoxAIItem> items,
                                                     List<BoxAIDialogueEntry> dialogueHistory) {
        return sendAITextGenRequest(api, prompt, items, dialogueHistory, null);
    }

    public static BoxAIResponse sendAITextGenRequest(BoxAPIConnection api, String prompt, List<BoxAIItem> items,
                                                     List<BoxAIDialogueEntry> dialogueHistory,
                                                     BoxAIAgentTextGen agent) {
        URL url = SEND_AI_TEXT_GEN_REQUEST_URL.build(api.getBaseURL());
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("prompt", prompt);

        JsonArray itemsJSON = new JsonArray();
        for (BoxAIItem item : items) {
            itemsJSON.add(item.getJSONObject());
        }
        requestJSON.add("items", itemsJSON);

        if (dialogueHistory != null) {
            JsonArray dialogueHistoryJSON = new JsonArray();
            for (BoxAIDialogueEntry dialogueEntry : dialogueHistory) {
                dialogueHistoryJSON.add(dialogueEntry.getJSONObject());
            }
            requestJSON.add("dialogue_history", dialogueHistoryJSON);
        }

        if (agent != null) {
            requestJSON.add("ai_agent", agent.getJSONObject());
        }

        BoxJSONRequest req = new BoxJSONRequest(api, url, HttpMethod.POST);
        req.setBody(requestJSON.toString());

        try (BoxJSONResponse response = req.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return new BoxAIResponse(responseJSON);
        }
    }

    /**
     * Get the default AI Agent use for the given mode.
     *
     * @param api  The API connection to be used by the created user.
     * @param mode The mode to filter the agent config to return.
     * @return A successful response including the default agent configuration.
     */
    public static BoxAIAgent getAiAgentDefaultConfig(BoxAPIConnection api, BoxAIAgent.Mode mode) {
        return getAiAgentDefaultConfig(api, mode, null, null);
    }

    /**
     * Get the default AI Agent use for the given mode.
     *
     * @param api      The API connection to be used by the created user.
     * @param mode     The mode to filter the agent config to return.
     * @param language The language to filter the agent config to return.
     * @param model    The model to filter the agent config to return.
     * @return A successful response including the default agent configuration.
     */
    public static BoxAIAgent getAiAgentDefaultConfig(BoxAPIConnection api,
                                                     BoxAIAgent.Mode mode,
                                                     String language,
                                                     String model) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("mode", mode.toString());
        if (language != null) {
            builder.appendParam("language", language);
        }
        if (model != null) {
            builder.appendParam("model", model);
        }
        URL url = AI_AGENT_DEFAULT_CONFIG_URL.buildWithQuery(api.getBaseURL(), builder.toString());
        BoxAPIRequest req = new BoxAPIRequest(api, url, HttpMethod.GET);
        try (BoxJSONResponse response = (BoxJSONResponse) req.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return BoxAIAgent.parse(responseJSON);
        }
    }

    public enum Mode {
        /**
         * Multiple items
         */
        MULTIPLE_ITEM_QA("multiple_item_qa"),

        /**
         * Single item
         */
        SINGLE_ITEM_QA("single_item_qa");

        private final String mode;

        Mode(String mode) {
            this.mode = mode;
        }

        static BoxAI.Mode fromJSONValue(String jsonValue) {
            if (jsonValue.equals("multiple_item_qa")) {
                return BoxAI.Mode.MULTIPLE_ITEM_QA;
            } else if (jsonValue.equals("single_item_qa")) {
                return BoxAI.Mode.SINGLE_ITEM_QA;
            } else {
                System.out.print("Invalid AI mode.");
                return null;
            }
        }

        String toJSONValue() {
            return this.mode;
        }

        public String toString() {
            return this.mode;
        }
    }
}
