AI
==

AI allows to send an intelligence request to supported large language models and returns
an answer based on the provided prompt and items.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [AI](#ai)
  - [Send AI request](#send-ai-request)
  - [Send AI text generation request](#send-ai-text-generation-request)
  - [Get AI Agent default configuration](#get-ai-agent-default-configuration)
  - [Extract metadata freeform](#extract-metadata-freeform)
  - [Extract metadata structured](#extract-metadata-structured)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Send AI request
--------------------------

To send an AI request, call static
[`sendAIRequest(String prompt, List<BoxAIItem> items, Mode mode)`][send-ai-request] method.
In the request you have to provide a prompt, a list of items that your prompt refers to and a mode of the request.
There are two modes available: `SINGLE_ITEM_QA` and `MULTI_ITEM_QA`, which specifies if this request refers to
for a single or multiple items.

<!-- sample post_ai_ask -->
```java
BoxAIResponse response = BoxAI.sendAIRequest(
    api,
    "What is the content of the file?",
    Collections.singletonList("123456", BoxAIItem.Type.FILE),
    BoxAI.Mode.SINGLE_ITEM_QA
);
```

You can also provide a list of dialogue history entries to provide additional context to the LLM in generating the response, AI Agent configuration and flag to indicate whether citations should be returned.

NOTE: The AI endpoint may return a 412 status code if you use for your request a file which has just been updated to the box.
It usually takes a few seconds for the file to be indexed and available for the AI endpoint.

[send-ai-request]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#sendAIRequest-com.box.sdk.BoxAPIConnection-java.lang.String-java.util.List-com.box.sdk.BoxAI.Mode-

Send AI text generation request
--------------

To send an AI request specifically focused on the creation of new text, call static
[`sendAITextGenRequest(String prompt, List<BoxAIItem> items, List<BoxAIDialogueEntry> dialogueHistory)`][send-ai-text-gen-request] method.
In the request you have to provide a prompt, a list of items that your prompt refers to and optionally a dialogue history,
which provides additional context to the LLM in generating the response.

<!-- sample post_ai_text_gen -->
```java
List<BoxAIDialogueEntry> dialogueHistory = new ArrayList<>();
dialogueHistory.add(
        new BoxAIDialogueEntry(
            "Make my email about public APIs sound more professional",
            "Here is the first draft of your professional email about public APIs.",
            BoxDateFormat.parse("2013-05-16T15:26:57-07:00")
        )
    );
BoxAIResponse response = BoxAI.sendAITextGenRequest(
    api,
    "Write an email to a client about the importance of public APIs.",
    Collections.singletonList(new BoxAIItem("123456", BoxAIItem.Type.FILE)),
    dialogueHistory
);
```

You can also provide an AI Agent configuration to customize the behavior of the AI response generation.

[send-ai-text-gen-request]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#sendAITextGenRequest-com.box.sdk.BoxAPIConnection-java.lang.String-java.util.List-java.util.List-

Get AI Agent default configuration
--------------------------

To get the default configuration of the AI Agent, call static
[`getAiAgentDefaultConfig(BoxAPIConnection api, BoxAIAgent.Mode mode, String language, String model)`][get-ai-agent-default-config] method.
In the request you have to provide the mode of the AI Agent, the language and the model, with the model is required while the language and mode are optional.

<!-- sample get_ai_agent_default -->
```java
BoxAIAgentConfig config = BoxAI.getAiAgentDefaultConfig(
    api,
    BoxAIAgent.Mode.ASK,
    "en",
    "openai__gpt_3_5_turbo"
);
```

[get-ai-agent-default-config]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#getAiAgentDefaultConfig-com.box.sdk.BoxAPIConnection-com.box.sdk.ai.BoxAIAgent.Mode-java.lang.String-java.lang.String-

Extract metadata freeform
--------------------------

To send an AI request to supported Large Language Models (LLMs) and extract metadata in form of key-value pairs, call static
[` extractMetadataFreeform(BoxAPIConnection api, String prompt, List<BoxAIItem> items, BoxAIAgentExtract agent)`][extract-metadata-freeform] method.
In the request you have to provide a prompt, a list of items that your prompt refers to and an optional agent configuration.

<!-- sample post_ai_extract -->
```java
BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT, "en-US", null);
BoxAIAgentExtract agentExtract = (BoxAIAgentExtract) agent;

BoxAIResponse response = BoxAI.extractMetadataFreeform(
    api,
    "What is the content of the file?",
    Collections.singletonList(new BoxAIItem("123456", BoxAIItem.Type.FILE)),
    agentExtract
);
```

[extract-metadata-freeform]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#extractMetadataFreeform-com.box.sdk.BoxAPIConnection-java.lang.String-java.util.List-com.box.sdk.ai.BoxAIAgentExtract-

Extract metadata structured
--------------------------

Sends an AI request to supported Large Language Models (LLMs) and returns extracted metadata as a set of key-value pairs. For this request, you need to use an already defined metadata template or define a schema yourself. 
To send an AI request to extract metadata from files, call static
[`extractMetadataStructured extractMetadataStructured(BoxAPIConnection api, List<BoxAIItem> items, BoxAIExtractMetadataTemplate template, List<BoxAIExtractField> fields, BoxAIAgentExtractStructured agent)`][extract-metadata-structured] method.

<!-- sample post_ai_extract_structured -->
```java
BoxAIAgent agent = BoxAI.getAiAgentDefaultConfig(api, BoxAIAgent.Mode.EXTRACT_STRUCTURED, "en-US", null);
BoxAIAgentExtractStructured agentExtractStructured = (BoxAIAgentExtractStructured) agent;
BoxAIExtractMetadataTemplate template = new BoxAIExtractMetadataTemplate("templateKey", "enterprise");

BoxAIExtractStructuredResponse result = BoxAI.extractMetadataStructured(
    api,
    Collections.singletonList(new BoxAIItem("123456", BoxAIItem.Type.FILE)),
    template,
    null,
    agentExtractStructured
);
JsonObject sourceJson = result.getSourceJson();
```

[extract-metadata-structured]: https://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#extractMetadataStructured-com.box.sdk.BoxAPIConnection-java.util.List-com.box.sdk.ai.BoxAIExtractMetadataTemplate-java.util.List-com.box.sdk.ai.BoxAIAgentExtractStructured-