Sign Requests
==================

Sign Requests are used to request e-signatures on documents from signers.  
A Sign Request can refer to one or more Box Files and can be sent to one or more Box Sign Request Signers.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Create Sign Request](#create-sign-request)
- [Get all Sign Requests](#get-all-sign-requests)
- [Get Sign Request by ID](#get-sign-request-by-id)
- [Cancel Sign Request](#cancel-sign-request)
- [Resend Sign Request](#resend-sign-request)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Create Sign Request
------------------------

The [`createSignRequest(BoxAPIConnection api, List<BoxSignRequestSigner> signers, List<BoxSignRequestFile> files, String parentFolderId)`][create-sign-request]
method will create a Sign Request.

You need to provide at least one file (from which the signing document will be created) and at least one signer to receive the Sign Request. You can use [`BoxSignRequestFile`][box-sign-request-file]
and [`BoxSignRequestSigner`][box-sign-request-signer] classes to provide the necessary data.

<!-- sample post_sign_requests -->
```java
List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
        BoxSignRequestFile file = new BoxSignRequestFile("12345");
        files.add(file);
        
// you can also use specific version of the file
BoxFile file = new BoxFile(api, "12345");
List<BoxFileVersion> versions = file.getVersions();
BoxFileVersion firstVersion = versions.get(0);
BoxSignRequestFile file = new BoxSignRequestFile(firstVersion.getFileID(), firstVersion.getVersionID());

List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
signers.add(newSigner);

String destinationParentFolderId = "55555";

BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, files,
        signers, destinationParentFolderId);
```

[`createSignRequest`][create-sign-request] allows you to specify optional parameters using the [`BoxSignRequestCreateParams`][sign-request-create-params]
object.

```java
List<BoxSignRequestFile> files = new ArrayList<BoxSignRequestFile>();
        BoxSignRequestFile file = new BoxSignRequestFile("12345");
        files.add(file);

List<BoxSignRequestSigner> signers = new ArrayList<BoxSignRequestSigner>();
BoxSignRequestSigner newSigner = new BoxSignRequestSigner("signer@mail.com");
signers.add(newSigner);

String destinationParentFolderId = "55555";

BoxSignRequestCreateParams createParams = new BoxSignRequestCreateParams()
        .setIsDocumentPreparationNeeded(true);

BoxSignRequest.Info signRequestInfo = BoxSignRequest.createSignRequest(api, files,
        signers, destinationParentFolderId, createParams);
```

If you set ```isDocumentPreparationNeeded``` flag to true, you need to visit ```prepareUrl``` before the Sign Request will be sent. 
For more information on ```isDocumentPreparationNeeded``` and the other ```BoxSignRequestCreateParams``` available, please refer to the [developer documentation](https://developer.box.com/guides/sign-request/).

[sign-request-create-params]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestCreateParams.html
[create-sign-request]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#createSignRequest-com.box.sdk.BoxAPIConnection-java.util.List-java.util.List-java.lang.String-
[box-sign-request-signer]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestSigner.html
[box-sign-request-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequestFile.html

Get All Sign Requests
------------------------

Calling the static [`getAll(BoxAPIConnection api, String... fields)`][get-all-sign-requests]
will return an iterable that will page through all the Sign Requests.

The static
[`getAll(int limit, BoxAPIConnection api, String... fields)`][get-all-sign-requests-with-fields]
method offers `limit` and `fields` parameters.  The `limit` parameter specifies the maximum number of items to be returned in a single response. The `fields` parameter is used to specify what additional properties should be returned on the `BoxSignRequest.Info` objects.  For more information on what `fields` are available, please refer to the [developer documentation](https://developer.box.com/guides/sign-request/).

<!-- sample get_sign_requests -->
```java
Iterable<BoxSignRequest.Info> signRequests = BoxSignRequest.getAll(api);
for (BoxSignRequest.Info signRequestInfo : signRequests) {
	// Do something with each `signRequestInfo`.
}
```

[get-all-sign-requests]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getAll-com.box.sdk.BoxAPIConnection-java.lang.String...-
[get-all-sign-requests-with-fields]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getAll-int-com.box.sdk.BoxAPIConnection-java.lang.String...-

Get Sign Request by ID
------------------------

Calling [`getInfo(String fields ...)`][get-sign-request-by-id] will return a [`BoxSignRequest.Info`][box-sign-request-info] object
containing information about the Sign Request.
The `fields` parameter is used to specify what additional properties should be returned on the `BoxSignRequest.Info` objects.

<!-- sample get_sign_requests_id -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

//using `fields` parameter
BoxSignRequest.Info signRequestInfoWithFields = signRequest.getInfo("status")

```

[get-sign-request-by-id]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#getInfo-java.lang.String...-
[box-sign-request-info]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.Info.html


Cancel Sign Request
------------------------

Calling [`cancel()`][cancel-sign-request] will cancel a created Sign Request.

<!-- sample post_sign_requests_cancel -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

signRequestInfo.cancel();
```

[cancel-sign-request]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#cancel--


Resend Sign Request
------------------------

Calling [`resend()`][resend-sign-request] will resend a Sign Request to all signers that have not signed it yet.
There is an about 10-minute cooling-off period between sending reminder emails. If this method is called during the
cooling-off period, a [`BoxAPIException`][box-api-exception] will be thrown.

<!-- sample post_sign_requests_resend -->
```java
BoxSignRequest signRequest = new BoxSignRequest(api, id);
BoxSignRequest.Info signRequestInfo = signRequest.getInfo();

signRequestInfo.resend();
```

[resend-sign-request]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxSignRequest.html#resend--
[box-api-exception]:http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIException.html
