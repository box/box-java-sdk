package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import java.util.Date;


/**
 * Represents an entry of the history of prompts and answers previously passed to the LLM.
 * This provides additional context to the LLM in generating the response.
 */
public class BoxAIDialogueEntry extends BoxJSONObject {
    private String prompt;
    private String answer;
    private Date createdAt;

    /**
     *
     * @param prompt The prompt previously provided by the client and answered by the LLM.
     * @param answer The answer previously provided by the LLM.
     * @param createdAt The ISO date formatted timestamp of when the previous answer to the prompt was created.
     */
    public BoxAIDialogueEntry(String prompt, String answer, Date createdAt) {
        super();
        this.prompt = prompt;
        this.answer = answer;
        this.createdAt = createdAt;
    }

    /**
     *
     * @param prompt The prompt previously provided by the client and answered by the LLM.
     * @param answer The answer previously provided by the LLM.
     */
    public BoxAIDialogueEntry(String prompt, String answer) {
        super();
        this.prompt = prompt;
        this.answer = answer;
    }

    /**
     * Get the answer previously provided by the LLM.
     * @return the answer previously provided by the LLM.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer previously provided by the LLM.
     * @param answer the answer previously provided by the LLM.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Get the prompt previously provided by the client and answered by the LLM.
     *
     * @return the prompt previously provided by the client and answered by the LLM.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Set the prompt previously provided by the client and answered by the LLM.
     *
     * @param prompt the prompt previously provided by the client and answered by the LLM.
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Get The ISO date formatted timestamp of when the previous answer to the prompt was created.
     * @return The ISO date formatted timestamp of when the previous answer to the prompt was created.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set The ISO date formatted timestamp of when the previous answer to the prompt was created.
     * @param createdAt The ISO date formatted timestamp of when the previous answer to the prompt was created.
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets a JSON object representing this class.
     *
     * @return the JSON object representing this class.
     */
    public JsonObject getJSONObject() {
        JsonObject itemJSON = new JsonObject()
                .add("id", this.prompt)
                .add("type", this.answer);

        if (this.createdAt != null) {
            itemJSON.add("content", this.createdAt.toString());
        }

        return itemJSON;
    }
}
