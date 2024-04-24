AI
==

AI allows to send an intelligence request to supported large language models and returns
an answer based on the provided prompt and items.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Send AI request](#send-ai-request)
- [Send AI text generation request](#send-ai-text-generation-request)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Send AI request
--------------------------

To send an AI request to get an answer call static
[`sendAIRequest(String prompt, List<BoxAIItem> items, Mode mode)`][send-ai-request] method.
In the request you have to provide a prompt, a list of items that your prompt refers to and a mode of the request.
There are two modes available: `SINGLE_ITEM_QA` and `MULTI_ITEM_QA`, which specifies if this request refers to
for a single or multiple items.

<!-- sample get_enterprises_id_device_pinners -->
```java
BoxAIResponse response = BoxAI.sendAIRequest(
    api,
    "What is the content of the file?",
    Collections.singletonList("123456", BoxAIItem.Type.FILE)),
    BoxAI.Mode.SINGLE_ITEM_QA
);
```

NOTE: The AI endpoint may return a 412 status code if you use for your request a file which has just been updated to the box.
It usually takes a few seconds for the file to be indexed and available for the AI endpoint.

[send-ai-request]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#sendAIRequest-com.box.sdk.BoxAPIConnection-java.lang.String-

Send AI text generation request
--------------

To send an AI request to get an answer specifically focused on the creation of new text call static
[`sendAITextGenRequest(String prompt, List<BoxAIItem> items, List<BoxAIDialogueEntry> dialogueHistory)`][send-ai-text-gen-request] method.
In the request you have to provide a prompt, a list of items that your prompt refers to and a dialogue history,
which provides additional context to the LLM in generating the response.

<!-- sample get_device_pinners_id -->
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

[send-ai-text-gen-request]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAI.html#sendAITextGenRequest-com.box.sdk.BoxAPIConnection-java.lang.String-