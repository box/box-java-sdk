Webhooks
======

Webhooks enable you to attach event triggers to Box files and folders. Event triggers monitor events on Box objects and notify your application when they occur. A webhook notifies your application by sending HTTP requests to a URL of your choosing.

* [Get a Webhook](#get-a-webhook)
* [Get All Webhooks](#get-all-webhooks)
* [Create a Webhook](#create-a-webhook)
* [Delete a Webhook](#delete-a-webhook)
* [Update a Webhook](#update-a-webhook)

Get a Webhook
---------------------------

A webhook infocan be retrieved by calling the [`getInfo(String...)`][get-info] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
BoxWebHook.Info info = weghook.getInfo();
```

[get-info]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#getInfo(java.lang.String...)

Get All Webhooks
--------------

Calling the static [`all(BoxAPIConnection, String...)`][all] will return an iterable that will page through all defined webhooks for the requesting application and user.

```java
Iterable<BoxWebHook.Info> webhooks = BoxWebHook.all(BoxAPIConnection api);
for (BoxWebHook.Info webhookInfo: webhooks) {
    // Do something with the webhook.
}
```

[all]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#all(com.box.sdk.BoxAPIConnection,%20java.lang.String...)

Create a Webhook
--------------

The static [`create(BoxResource, URL, BoxWebHook.Trigger...)`][create-webhook] method will
let you create a new webhook for a specified target object.

```java
BoxFolder folder = new BoxFolder(api, id);
BoxWebHook.Info webhookInfo = BoxWebHook.create(folder, url, BoxWebHook.Trigger.FILE_UPLOADED);
```

[create-webhook]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#create(com.box.sdk.BoxResource,%20java.net.URL,%20com.box.sdk.BoxWebHook.Trigger...)

Delete a Webhook
--------------

A webhook can be deleted by calling the [`delete()`][delete] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
webhook.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#delete()

Update a Webhook
--------------

A webhook can be updated by calling the [`update(BoxWebHook.Info)`][update] method.

```java
BoxWebHook webhook = new BoxWebHook(api, id);
BoxWebHook.Info info = webhook.getInfo();
info.addPendingChange("address", url);
webhook.update(info);
```

[update]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxWebHook.html#update(com.box.sdk.BoxWebHook.Info)