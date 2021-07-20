Sign requests
==================

Sign requests are used to prepare document to signing and send it to signers.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Create Sign Request](#create-sign-request)
- [Get all Sign Requests](#get-all-sign-requests)
- [Get Sign request by ID](#get-sign-request-by-id)
- [Cancel Sign Request](#cancel-sign-request)
- [Resend Sign Request](#resend-sign-request)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Create Sign Request
------------------------

The [`createSignRequest(BoxAPIConnection api, List<BoxSignRequestSigner> signers, List<BoxSignRequestFile> files, String parentFolderId)`][create-sign-request]
method will create sign request.

You can provide signers that need to sign the request and files from which sign document will be created. You can use [`BoxSignRequestSigner`][box-sign-request-signer] 
and [`BoxSignRequestFile`][box-sign-request-file] classes to fill the necessary data.

<!-- sample create_sign_request -->
```java
List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
signers.add(newSigner);

List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
BoxSignRequestFile file = new BoxSignRequestFile("12345");
files.add(file);
// you can also use specific version of the file
BoxSignRequestFile file = new BoxSignRequestFile("12345", "");

String parentFolderId = "55555";

BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers,
        files, parentFolderId);
```

Create sign request allow you to specify optional parameters using the [`BoxSignRequestCreateParams`][sign-request-create-params]
object.

```java
List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
signers.add(newSigner);

List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
BoxSignRequestFile file = new BoxSignRequestFile("12345");
files.add(file);

String parentFolderId = "55555";

BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
        .setIsDocumentPreparationNeeded(true);

BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, signers,
        files, parentFolderId, createParams);
```

If you set ```isDocumentPreparationNeeded``` flag to true, you need to visit ```prepareUrl``` before the sign request will be sent.

[sign-request-create-params]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestCreateParams.html
[create-sign-request]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/.html#createSignRequest-com.box.sdk.BoxAPIConnection-java.util.List-java.util.List-java.lang.String-
[box-sign-request-signer]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestSigner.html
[box-sign-request-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestFile.html

Get all Sign Requests
------------------------

Calling the static [`getAll(BoxAPIConnection api, String... fields)`][get-all-sign-requests]
will return an iterable that will page through all the sign requests.
It is possible to specify limit of items per single
response and fields to retrieve by calling the static
[`getAll(int limit, BoxAPIConnection api, String... fields)`][get-all-sign-requests-with-fields]
method.

<!-- sample get_all_sign_requests -->
```java
Iterable<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(api);
for (BoxSignRequest.Info signRequestInfo : signRequests) {
	// Do something with the signRequest.
}
```

[get-all-sign-requests]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[get-all-sign-requests-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getAll-int-com.box.sdk.BoxAPIConnection-java.lang.String...-

Get Sign request by ID
------------------------

Calling [`getInfo(String fields ...)`][get-sign-request-by-id] will return [`BoxSignRequest.Info`][box-sign-request-info] object
containing information about the sign request. If necessary to retrieve
limited set of fields. It is possible to specify them using param.

<!-- sample get_sign_request_by_id -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();
```

[get-sign-request-by-id]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getInfo-java.lang.String...-
[box-sign-request-info]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.Info.html


Cancel Sign request
------------------------

Calling [`cancel()`][cancel-sign-request] will cancel ongoing sign request.

<!-- sample cancel_sign_request -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

signRequestInfo.cancel();
```

[cancel-sign-request]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#cancel--


Resend Sign request
------------------------

Calling [`resend()`][resend-sign-request] will resend a sign request to all signers that have not signed it yet.
There is an about 10-minute cooling-off period between sending reminder emails. If this method is called during the
cooling-off period, [`BoxAPIException`][box-api-exception] will be thrown.

<!-- sample resend_sign_request -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

signRequestInfo.resend();
```

[resend-sign-request]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#resend--
[box-api-exception]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIException.html
