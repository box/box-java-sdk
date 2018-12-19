Webhooks
======

Webhooks enable you to attach event triggers to Box files and folders. Event triggers monitor events on Box objects and notify your application when they occur. A webhook notifies your application by sending HTTP requests to a URL of your choosing.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get a Webhook](#get-a-webhook)
- [Get All Webhooks](#get-all-webhooks)
- [Create a Webhook](#create-a-webhook)
- [Delete a Webhook](#delete-a-webhook)
- [Update a Webhook](#update-a-webhook)
- [Verify a Webhook Message](#verify-a-webhook-message)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get a Webhook
-------------

A webhook infocan be retrieved by calling the [`getInfo(String... fields)`][get-info] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
BoxWebHook.Info info = webhook.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#getInfo-java.lang.String...-

Get All Webhooks
----------------

Calling the static [`all(BoxAPIConnection api, String... fields)`][all] will
return an iterable that will page through all defined webhooks for the
requesting application and user.

```java
Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(api);
for (BoxWebHook.Info webhookInfo: webhooks) {
    // Do something with the webhook.
}
```

[all]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#all-com.box.sdk.BoxAPIConnection-java.lang.String...-

Create a Webhook
----------------

The static [`create(BoxResource targetItem, URL callbackURL, BoxWebHook.Trigger... triggerEvents)`][create-webhook]
method will let you create a new webhook for a specified target object.

```java
// Listen for file upload events in the specified folder
BoxFolder folder = new BoxFolder(api, id);
BoxWebHook.Info webhookInfo = BoxWebHook.create(folder, url, BoxWebHook.Trigger.FILE_UPLOADED);
```

[create-webhook]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#create-com.box.sdk.BoxResource-java.net.URL-com.box.sdk.BoxWebHook.Trigger...-

Delete a Webhook
----------------

A webhook can be deleted by calling the [`delete()`][delete] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
webhook.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#delete--

Update a Webhook
----------------

A webhook can be updated by calling the [`update(BoxWebHook.Info fieldsToUpdate)`][update] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
BoxWebHook.Info info = webhook.getInfo();
info.addPendingChange("address", url);
webhook.update(info);
```

[update]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#updateInfo-com.box.sdk.BoxWebHook.Info-

Verify a Webhook Message
------------------------

When you receive a webhook message from Box, you should validate that it actually came from Box by calling
[`BoxWebHookSignatureVerifier#verify(String sigVersion, String sigAlgorithm, String primarySignature, String secondarySignature, String payload, String deliveryTimestamp)`][verify]

> __Note:__ It is recommended to ensure that your application and verifier use both a primary and secondary key
> to ensure that these keys can be safely rotated.

```java
// Webhook message contents are shown for demonstration purposes
// Normally these would come from your HTTP handler

// Webhook message HTTP body
String messagePayload = "{"
    + "\"type\":\"webhook_event","
    + "\"webhook\":{"
    +   "\"id\":\"1234567890\""
    + "},"
    + "\"trigger\":\"FILE.UPLOADED\","
    + "\"source\":{"
    +   "\"id\":\"1234567890\","
    +   "\"type\":\"file\","
    +   "\"name\":\"Test.txt\""
    + "}}";

// Webhook message HTTP headers
Map<String, String> messageHeaders = new HashMap<String, String>();
headers.put("BOX-DELIVERY-ID", "f96bb54b-ee16-4fc5-aa65-8c2d9e5b546f");
headers.put("BOX-DELIVERY-TIMESTAMP", "2020-01-01T00:00:00-07:00");
headers.put("BOX-SIGNATURE-ALGORITHM", "HmacSHA256");
headers.put("BOX-SIGNATURE-PRIMARY", "6TfeAW3A1PASkgboxxA5yqHNKOwFyMWuEXny/FPD5hI=");
headers.put("BOX-SIGNATURE-SECONDARY", "v+1CD1Jdo3muIcbpv5lxxgPglOqMfsNHPV899xWYydo=");
headers.put("BOX-SIGNATURE-VERSION", "1");

// Your application's webhook keys, obtained from the Box Developer Console
String primaryKey = "4py2I9eSFb0ezXH5iPeQRcFK1LRLCdip";
String secondaryKey = "Aq5EEEjAu4ssbz8n9UMu7EerI0LKj2TL";

BoxWebHookSignatureVerifier verifier = new BoxWebHookSignatureVerifier(primaryKey, secondaryKey);
boolean isValidMessage = verifier.verify(
    headers.get("BOX-SIGNATURE-VERSION"),
    headers.get("BOX-SIGNATURE-ALGORITHM"),
    headers.get("BOX-SIGNATURE-PRIMARY"),
    headers.get("BOX-SIGNATURE-SECONDARY"),
    messagePayload,
    headers.get("BOX-DELIVERY-TIMESTAMP")
);

if (isValidMessage) {
    // Message is valid, handle it
} else {
    // Message is invalid, reject it
}
```

[verify]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHookSignatureVerifier.html#verify-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-
