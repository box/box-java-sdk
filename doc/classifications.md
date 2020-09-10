Classifications
===============

Classfications are a type of metadata that allows users and applications 
to define and assign a content classification to files and folders.

Classifications use the metadata APIs to add and remove classifications, and
assign them to files. For more details on metadata templates please see the
[metadata documentation](./metadata.md).
<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Classifications](#classifications)
  - [Add initial classifications](#add-initial-classifications)
  - [List all classifications](#list-all-classifications)
  - [Add another classification](#add-another-classification)
  - [Update a classification](#update-a-classification)
  - [Delete a classification](#delete-a-classification)
  - [Delete all classifications](#delete-all-classifications)
  - [Add classification to file](#add-classification-to-file)
  - [Update classification on file](#update-classification-on-file)
  - [Get classification on file](#get-classification-on-file)
  - [Remove classification from file](#remove-classification-from-file)
  - [Add classification to folder](#add-classification-to-folder)
  - [Update classification on folder](#update-classification-on-folder)
  - [Get classification on folder](#get-classification-on-folder)
  - [Remove classification from folder](#remove-classification-from-folder)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Add initial classifications
---------------------------

If an enterprise does not already have a classification defined, the first classification(s)
can be added with the [`createMetadataTemplate`][create-metadata-template] method.

<!-- sample post_metadata_templates_schema classifications -->
```java
MetadataTemplate.Field classification = new MetadataTemplate.Field();
classification.setType("enum");
classification.setKey("Box__Security__Classification__Key");
classification.setDisplayName("Classification");
classification.setHidden("false");

List<String> options = new ArrayList<String>();
options.add("Top Secret");
classification.setOptions(topSecret)

List<MetadataTemplate.Field> fields = new ArrayList<MetadataTemplate.Field>();
fields.add(classification);

MetadataTemplate template = MetadataTemplate.createMetadataTemplate(api, "enterprise", "securityClassification-6VMVochwUWo", "Classification", false, fields);
```

[create-metadata-template]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#createMetadataTemplate-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-java.lang.String-boolean-java.util.List-


List all classifications
------------------------

To retrieve a list of all the classifications in an enterprise call the
[`getMetadataTemplate`][get-metadata-template]
method to get the classifciations template, which will contain a list of all the 
classifications

<!-- sample get_metadata_templates_enterprise_securityClassification-6VMVochwUWo_schema -->
```java
MetadataTemplate template = MetadataTemplate.getMetadataTemplate(api, "securityClassification-6VMVochwUWo");
```

[get-metadata-template]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getMetadataTemplate-com.box.sdk.BoxAPIConnection-

Add another classification
--------------------------

To add another classification, call the [`updateMetadataTemplate`][update-metadata-template]
method with the an operation to add a new classification to the template. 

<!-- sample put_metadata_templates_enterprise_securityClassification-6VMVochwUWo_schema add -->
```java
List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();

String update = "{\n  op: \"addEnumOption\",\n  fieldKey: \"Box__Security__Classification__Key\",\n  data: {\n    key: \"Sensitive\"\n }\n}";

updates.add(new MetadataTemplate.FieldOperation(addCategoryFieldJSON));

MetadataTemplate.updateMetadataTemplate(api, "enterprise", "securityClassification-6VMVochwUWo", updates);
```

[update-metadata-template]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#updateMetadataTemplate-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-java.util.List-


Update a classification
-----------------------

To update an existing classification, call the
[`updateMetadataTemplate`][update-metadata-template]
method with the an operation to update the existing classification already present on the template. 

<!-- sample put_metadata_templates_enterprise_securityClassification-6VMVochwUWo_schema update -->
```java
List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();

String update = "{\n  op: \"editEnumOption\",\n  fieldKey: \"Box__Security__Classification__Key\",\n  enumOptionKey: \"Sensitive\",\n  data: {\n    key: \"Very Sensitive\"\n }\n}";

updates.add(new MetadataTemplate.FieldOperation(addCategoryFieldJSON));

MetadataTemplate.updateMetadataTemplate(api, "enterprise", "securityClassification-6VMVochwUWo", updates);
```

Delete a classification
-----------------------

To remove an existing classification, call the
[`updateMetadataTemplate`][update-metadata-template]
method with the an operation to remove the existing classification from the metadata template. 

<!-- sample put_metadata_templates_enterprise_securityClassification-6VMVochwUWo_schema delete -->
```java
List<MetadataTemplate.FieldOperation> updates = new ArrayList<MetadataTemplate.FieldOperation>();

String update = "{\n  op: \"removeEnumOption\",\n  fieldKey: \"Box__Security__Classification__Key\",\n  enumOptionKey: \"Sensitive\"\n}";

updates.add(new MetadataTemplate.FieldOperation(addCategoryFieldJSON));

MetadataTemplate.updateMetadataTemplate(api, "enterprise", "securityClassification-6VMVochwUWo", updates);
```

Delete all classifications
--------------------------

To remove all classifications in an enterprise, call the
[`deleteMetadataTemplate`][delete-metadata-template]
method with the an name of the classification metadata template. 

<!-- sample delete_metadata_templates_enterprise_securityClassification-6VMVochwUWo_schema -->
```java
MetadataTemplate.deleteMetadataTemplate(api, "enterprise", "securityClassification-6VMVochwUWo");
```

[delete-metadata-template]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#deleteMetadataTemplate-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String-

Add classification to file
--------------------------

To add a classification to a file, call [`setMetadata(String templateKey, String templateScope, Metadata properties)`][set-metadata]
with the name of the classification template, as well as the details of the classification
to add to the file.

<!-- sample post_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFile file = new BoxFile(api, "id");
Metadata metadata = new Metadata()
metadata.add("Box__Security__Classification__Key", "Sensitive")
file.setMetadata('securityClassification-6VMVochwUWo', "enterprise", metadata);
```

[set-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#setMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-

Update classification on file
-----------------------------

To update a classification on a file, call 
[`setMetadata(String templateKey, String templateScope, Metadata properties)`][set-metadata]
with the name of the classification template, as well as the details of the classification
to add to the file.

<!-- sample put_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFile file = new BoxFile(api, "id");
Metadata metadata = new Metadata()
metadata.add("Box__Security__Classification__Key", "Sensitive")
file.setMetadata('securityClassification-6VMVochwUWo', "enterprise", metadata);
```

Get classification on file
--------------------------

Retrieve the classification on a file by calling
[`getMetadata(String templateKey)`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getMetadata-java.lang.String-)
with the ID of the file.

<!-- sample get_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFile file = new BoxFile(api, "id");
Metadata metadata = file.getMetadata("securityClassification-6VMVochwUWo");
```

Remove classification from file
-------------------------------

A classification can be removed from a file by calling
[`deleteMetadata`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#deleteMetadata--).

<!-- sample delete_files_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFile file = new BoxFile(api, "id");
file.deleteMetadata("securityClassification-6VMVochwUWo");
```

Add classification to folder
----------------------------

To add a classification to a folder, call [`setMetadata(String templateKey, String templateScope, Metadata properties)`][set-metadata]
with the name of the classification template, as well as the details of the classification
to add to the folder.

<!-- sample post_folders_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFolder folder = new BoxFolder(api, "id");
Metadata metadata = new Metadata()
metadata.add("Box__Security__Classification__Key", "Sensitive")
folder.setMetadata('securityClassification-6VMVochwUWo', "enterprise", metadata);
```

[set-metadata]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#setMetadata-java.lang.String-java.lang.String-com.box.sdk.Metadata-

Update classification on folder
-------------------------------

To update a classification on a folder, call 
[`setMetadata(String templateKey, String templateScope, Metadata properties)`][set-metadata]
with the name of the classification template, as well as the details of the classification
to add to the folder.

<!-- sample put_folders_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFolder folder = new BoxFolder(api, "id");
Metadata metadata = new Metadata()
metadata.add("Box__Security__Classification__Key", "Sensitive")
folder.setMetadata('securityClassification-6VMVochwUWo', "enterprise", metadata);
```

Get classification on folder
----------------------------

Retrieve the classification on a folder by calling
[`getMetadata(String templateKey)`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getMetadata-java.lang.String-)
with the ID of the folder.

<!-- sample get_folders_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFolder folder = new BoxFolder(api, "id");
Metadata metadata = folder.getMetadata("securityClassification-6VMVochwUWo");
```

Remove classification from folder
---------------------------------

A classification can be removed from a folder by calling
[`deleteMetadata`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#deleteMetadata--).

<!-- sample delete_folders_id_metadata_enterprise_securityClassification-6VMVochwUWo -->
```java
BoxFolder folder = new BoxFolder(api, "id");
folder.deleteMetadata("securityClassification-6VMVochwUWo");
```