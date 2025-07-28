# FileClassificationsManager


- [Get classification on file](#get-classification-on-file)
- [Add classification to file](#add-classification-to-file)
- [Update classification on file](#update-classification-on-file)
- [Remove classification from file](#remove-classification-from-file)

## Get classification on file

Retrieves the classification metadata instance that
has been applied to a file.

This API can also be called by including the enterprise ID in the
URL explicitly, for example
`/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.

This operation is performed by calling function `getClassificationOnFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-files-id-metadata-enterprise-securityClassification-6VMVochwUWo/).

<!-- sample get_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```
client.getFileClassifications().getClassificationOnFile(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- headers `GetClassificationOnFileHeaders`
  - Headers of getClassificationOnFile method


### Returns

This function returns a value of type `Classification`.

Returns an instance of the `securityClassification` metadata
template, which contains a `Box__Security__Classification__Key`
field that lists all the classifications available to this
enterprise.


## Add classification to file

Adds a classification to a file by specifying the label of the
classification to add.

This API can also be called by including the enterprise ID in the
URL explicitly, for example
`/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.

This operation is performed by calling function `addClassificationToFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-files-id-metadata-enterprise-securityClassification-6VMVochwUWo/).

<!-- sample post_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```
client.getFileClassifications().addClassificationToFile(file.getId(), new AddClassificationToFileRequestBody.Builder().boxSecurityClassificationKey(classification.getKey()).build())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- requestBody `AddClassificationToFileRequestBody`
  - Request body of addClassificationToFile method
- headers `AddClassificationToFileHeaders`
  - Headers of addClassificationToFile method


### Returns

This function returns a value of type `Classification`.

Returns the classification template instance
that was applied to the file.


## Update classification on file

Updates a classification on a file.

The classification can only be updated if a classification has already been
applied to the file before. When editing classifications, only values are
defined for the enterprise will be accepted.

This operation is performed by calling function `updateClassificationOnFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-files-id-metadata-enterprise-securityClassification-6VMVochwUWo/).

<!-- sample put_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```
client.getFileClassifications().updateClassificationOnFile(file.getId(), Arrays.asList(new UpdateClassificationOnFileRequestBody(secondClassification.getKey())))
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- requestBody `List<UpdateClassificationOnFileRequestBody>`
  - Request body of updateClassificationOnFile method
- headers `UpdateClassificationOnFileHeaders`
  - Headers of updateClassificationOnFile method


### Returns

This function returns a value of type `Classification`.

Returns the updated classification metadata template instance.


## Remove classification from file

Removes any classifications from a file.

This API can also be called by including the enterprise ID in the
URL explicitly, for example
`/files/:id//enterprise_12345/securityClassification-6VMVochwUWo`.

This operation is performed by calling function `deleteClassificationFromFile`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-files-id-metadata-enterprise-securityClassification-6VMVochwUWo/).

<!-- sample delete_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```
client.getFileClassifications().deleteClassificationFromFile(file.getId())
```

### Arguments

- fileId `String`
  - The unique identifier that represents a file.  The ID for any file can be determined by visiting a file in the web application and copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the `file_id` is `123`. Example: "12345"
- headers `DeleteClassificationFromFileHeaders`
  - Headers of deleteClassificationFromFile method


### Returns

This function returns a value of type `void`.

Returns an empty response when the classification is
successfully deleted.


