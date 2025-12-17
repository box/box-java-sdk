# AiManager


- [Ask question](#ask-question)
- [Generate text](#generate-text)
- [Get AI agent default configuration](#get-ai-agent-default-configuration)
- [Extract metadata (freeform)](#extract-metadata-freeform)
- [Extract metadata (structured)](#extract-metadata-structured)

## Ask question

Sends an AI request to supported LLMs and returns an answer specifically focused on the user's question given the provided context.

This operation is performed by calling function `createAiAsk`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-ai-ask/).

<!-- sample post_ai_ask -->
```
client.getAi().createAiAsk(new AiAsk.Builder(AiAskModeField.SINGLE_ITEM_QA, "Which direction does the Sun rise?", Arrays.asList(new AiItemAsk.Builder(fileToAsk.getId(), AiItemAskTypeField.FILE).content("The Sun rises in the east").build())).aiAgent(aiAskAgentBasicTextConfig).build())
```

### Arguments

- requestBody `AiAsk`
  - Request body of createAiAsk method
- headers `CreateAiAskHeaders`
  - Headers of createAiAsk method


### Returns

This function returns a value of type `AiResponseFull`.

A successful response including the answer from the LLM.No content is available to answer the question. This is returned when the request item is a hub, but content in the hubs is not indexed. To ensure content in the hub is indexed, make sure Box AI for Hubs in the Admin Console was enabled before hub creation.


## Generate text

Sends an AI request to supported Large Language Models (LLMs) and returns generated text based on the provided prompt.

This operation is performed by calling function `createAiTextGen`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-ai-text-gen/).

<!-- sample post_ai_text_gen -->
```
client.getAi().createAiTextGen(new AiTextGen.Builder("Paraphrase the documents", Arrays.asList(new AiTextGenItemsField.Builder(fileToAsk.getId()).type(AiTextGenItemsTypeField.FILE).content("The Earth goes around the Sun. The Sun rises in the east in the morning.").build())).dialogueHistory(Arrays.asList(new AiDialogueHistory.Builder().prompt("What does the earth go around?").answer("The Sun").createdAt(dateTimeFromString("2021-01-01T00:00:00Z")).build(), new AiDialogueHistory.Builder().prompt("On Earth, where does the Sun rise?").answer("east").createdAt(dateTimeFromString("2021-01-01T00:00:00Z")).build())).build())
```

### Arguments

- requestBody `AiTextGen`
  - Request body of createAiTextGen method
- headers `CreateAiTextGenHeaders`
  - Headers of createAiTextGen method


### Returns

This function returns a value of type `AiResponse`.

A successful response including the answer from the LLM.


## Get AI agent default configuration

Get the AI agent default config.

This operation is performed by calling function `getAiAgentDefaultConfig`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-ai-agent-default/).

<!-- sample get_ai_agent_default -->
```
client.getAi().getAiAgentDefaultConfig(new GetAiAgentDefaultConfigQueryParams.Builder(GetAiAgentDefaultConfigQueryParamsModeField.ASK).language("en-US").build())
```

### Arguments

- queryParams `GetAiAgentDefaultConfigQueryParams`
  - Query parameters of getAiAgentDefaultConfig method
- headers `GetAiAgentDefaultConfigHeaders`
  - Headers of getAiAgentDefaultConfig method


### Returns

This function returns a value of type `AiAgent`.

A successful response including the default agent configuration.
This response can be one of the following four objects:
* AI agent for questions
* AI agent for text generation
* AI agent for freeform metadata extraction
* AI agent for structured metadata extraction.
The response depends on the agent configuration requested in this endpoint.


## Extract metadata (freeform)

Sends an AI request to supported Large Language Models (LLMs) and extracts metadata in form of key-value pairs.
In this request, both the prompt and the output can be freeform.
Metadata template setup before sending the request is not required.

This operation is performed by calling function `createAiExtract`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-ai-extract/).

<!-- sample post_ai_extract -->
```
client.getAi().createAiExtract(new AiExtract.Builder("firstName, lastName, location, yearOfBirth, company", Arrays.asList(new AiItemBase(file.getId()))).aiAgent(aiExtractAgentBasicTextConfig).build())
```

### Arguments

- requestBody `AiExtract`
  - Request body of createAiExtract method
- headers `CreateAiExtractHeaders`
  - Headers of createAiExtract method


### Returns

This function returns a value of type `AiResponse`.

A response including the answer from the LLM.


## Extract metadata (structured)

Sends an AI request to supported Large Language Models (LLMs) and returns extracted metadata as a set of key-value pairs.

To define the extraction structure, provide either a metadata template or a list of fields. To learn more about creating templates, see [Creating metadata templates in the Admin Console](https://support.box.com/hc/en-us/articles/360044194033-Customizing-Metadata-Templates)
or use the [metadata template API](https://developer.box.com/guides/metadata/templates/create).

This endpoint also supports [Enhanced Extract Agent](https://developer.box.com/guides/box-ai/ai-tutorials/extract-metadata-structured#enhanced-extract-agent).

For information about supported file formats and languages, see the [Extract metadata from file (structured)](https://developer.box.com/guides/box-ai/ai-tutorials/extract-metadata-structured) API guide.

This operation is performed by calling function `createAiExtractStructured`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-ai-extract-structured/).

<!-- sample post_ai_extract_structured -->
```
client.getAi().createAiExtractStructured(new AiExtractStructured.Builder(Arrays.asList(new AiItemBase(file.getId()))).fields(Arrays.asList(new AiExtractStructuredFieldsField.Builder("firstName").description("Person first name").displayName("First name").prompt("What is the your first name?").type("string").build(), new AiExtractStructuredFieldsField.Builder("lastName").description("Person last name").displayName("Last name").prompt("What is the your last name?").type("string").build(), new AiExtractStructuredFieldsField.Builder("dateOfBirth").description("Person date of birth").displayName("Birth date").prompt("What is the date of your birth?").type("date").build(), new AiExtractStructuredFieldsField.Builder("age").description("Person age").displayName("Age").prompt("How old are you?").type("float").build(), new AiExtractStructuredFieldsField.Builder("hobby").description("Person hobby").displayName("Hobby").prompt("What is your hobby?").type("multiSelect").options(Arrays.asList(new AiExtractStructuredFieldsOptionsField("guitar"), new AiExtractStructuredFieldsOptionsField("books"))).build())).aiAgent(aiExtractStructuredAgentBasicTextConfig).build())
```

### Arguments

- requestBody `AiExtractStructured`
  - Request body of createAiExtractStructured method
- headers `CreateAiExtractStructuredHeaders`
  - Headers of createAiExtractStructured method


### Returns

This function returns a value of type `AiExtractStructuredResponse`.

A successful response including the answer from the LLM.


