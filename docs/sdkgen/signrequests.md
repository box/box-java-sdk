# SignRequestsManager


- [Cancel Box Sign request](#cancel-box-sign-request)
- [Resend Box Sign request](#resend-box-sign-request)
- [Get Box Sign request by ID](#get-box-sign-request-by-id)
- [List Box Sign requests](#list-box-sign-requests)
- [Create Box Sign request](#create-box-sign-request)

## Cancel Box Sign request

Cancels a sign request.

This operation is performed by calling function `cancelSignRequest`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-sign-requests-id-cancel/).

<!-- sample post_sign_requests_id_cancel -->
```
client.getSignRequests().cancelSignRequest(createdSignRequest.getId())
```

### Arguments

- signRequestId `String`
  - The ID of the signature request. Example: "33243242"
- headers `CancelSignRequestHeaders`
  - Headers of cancelSignRequest method


### Returns

This function returns a value of type `SignRequest`.

Returns a Sign Request object.


## Resend Box Sign request

Resends a signature request email to all outstanding signers.

This operation is performed by calling function `resendSignRequest`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-sign-requests-id-resend/).

*Currently we don't have an example for calling `resendSignRequest` in integration tests*

### Arguments

- signRequestId `String`
  - The ID of the signature request. Example: "33243242"
- headers `ResendSignRequestHeaders`
  - Headers of resendSignRequest method


### Returns

This function returns a value of type `void`.

Returns an empty response when the API call was successful.
The email notifications will be sent asynchronously.


## Get Box Sign request by ID

Gets a sign request by ID.

This operation is performed by calling function `getSignRequestById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-sign-requests-id/).

<!-- sample get_sign_requests_id -->
```
client.getSignRequests().getSignRequestById(createdSignRequest.getId())
```

### Arguments

- signRequestId `String`
  - The ID of the signature request. Example: "33243242"
- headers `GetSignRequestByIdHeaders`
  - Headers of getSignRequestById method


### Returns

This function returns a value of type `SignRequest`.

Returns a signature request.


## List Box Sign requests

Gets signature requests created by a user. If the `sign_files` and/or
`parent_folder` are deleted, the signature request will not return in the list.

This operation is performed by calling function `getSignRequests`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-sign-requests/).

<!-- sample get_sign_requests -->
```
client.getSignRequests().getSignRequests()
```

### Arguments

- queryParams `GetSignRequestsQueryParams`
  - Query parameters of getSignRequests method
- headers `GetSignRequestsHeaders`
  - Headers of getSignRequests method


### Returns

This function returns a value of type `SignRequests`.

Returns a collection of sign requests.


## Create Box Sign request

Creates a signature request. This involves preparing a document for signing and
sending the signature request to signers.

This operation is performed by calling function `createSignRequest`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-sign-requests/).

<!-- sample post_sign_requests -->
```
client.getSignRequests().createSignRequest(new SignRequestCreateRequest.Builder(Arrays.asList(new SignRequestCreateSigner.Builder().email(signerEmail).role(SignRequestCreateSignerRoleField.SIGNER).isInPerson(false).embedUrlExternalUserId("123").declinedRedirectUrl("https://www.box.com").loginRequired(false).password("password").suppressNotifications(true).build())).sourceFiles(Arrays.asList(new FileBase(fileToSign.getId()))).parentFolder(new FolderMini(destinationFolder.getId())).isDocumentPreparationNeeded(false).redirectUrl("https://www.box.com").declinedRedirectUrl("https://www.box.com").areTextSignaturesEnabled(true).emailSubject("Sign this document").emailMessage("Please sign this document").areRemindersEnabled(true).name("Sign Request").prefillTags(Arrays.asList(new SignRequestPrefillTag.Builder().documentTagId("0").dateValue(dateFromString("2035-01-01")).build())).daysValid(30L).externalId("123").externalSystemName("BoxSignIntegration").build())
```

### Arguments

- requestBody `SignRequestCreateRequest`
  - Request body of createSignRequest method
- headers `CreateSignRequestHeaders`
  - Headers of createSignRequest method


### Returns

This function returns a value of type `SignRequest`.

Returns a Box Sign request object.


