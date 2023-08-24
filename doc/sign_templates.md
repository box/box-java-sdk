Sign Templates
==================

Box Sign enables you to create templates so you can automatically add the same fields and formatting to requests for signature.  With templates, you don't need to repetitively add the same fields to each request every time you send a new document for signature.

Making and testing a template takes a few minutes, but when done it makes working with Box Sign easier and faster.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Get all Sign Templates](#get-all-sign-templates)
- [Get Sign Template by ID](#get-sign-template-by-id)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get All Sign Templates
------------------------

Calling the static [`getAll(BoxAPIConnection api)`][get-all-sign-templates]
will return an iterable that will page through all the Sign Templates.

The static
[`getAll(BoxAPIConnection api, int limit)`][get-all-sign-templates-with-limit]
method offers `limit` parameters.  The `limit` parameter specifies the maximum number of items to be returned in a single response.

<!-- sample get_sign_templates -->
```java
Iterable<BoxSignTemplate.Info> signTemplates = BoxSignTemplate.getAll(api);
for (BoxSignTemplate.Info signTemplateInfo : signTemplates) {
	// Do something with each `signTemplateInfo`.
}
```

[get-all-sign-templates]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignTemplate.html#getAll-com.box.sdk.BoxAPIConnection-
[get-all-sign-templates-with-limit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignTemplate.html#getAll-int-

Get Sign Template by ID
------------------------

Calling [`getInfo()`][get-sign-template-info] will return a [`BoxSignTemplate.Info`][box-sign-template-info] object
containing information about the Sign Template.


<!-- sample get_sign_templates_id -->
```java
BoxSignTemplate signTemplate = new BoxSignTemplate(api, id);
BoxSignTemplate.Info signTemplateInfo = signTemplate.getInfo();
```

[get-sign-template-by-id]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignTemplate.html#getInfo-
[box-sign-template-info]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignTemplate.Info.html
