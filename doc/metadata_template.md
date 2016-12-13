Metadata Templates
==================

Metadata that belongs to a file is grouped by templates. Templates allow the metadata service to provide a multitude of services, such as pre-defining sets of key:value pairs or schema enforcement on specific fields. 

* [Get Metadata Template](#get-metadata-template)
* [Get Enterprise Metadata Templates](#get-enterprise-metadata-templates)


Get Metadata Template
--------------------

The [`getMetadataTemplate(BoxAPIConnection)`][get-metadata-template-1] method will return information about default metadata schema.
Also [`getMetadataTemplate(BoxAPIConnection, String)`][get-metadata-template-2] and [`getMetadataTemplate(BoxAPIConnection, String, String, String...)`][get-metadata-template-3] can be used to set metadata template name, metadata scope and fields to retrieve.

```java
MetadataTemplate template = MetadataTemplate.getMetadataTemplate(api, "templateName");
```

[get-metadata-template-1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(com.box.sdk.BoxAPIConnection)
[get-metadata-template-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(com.box.sdk.BoxAPIConnection,%20java.lang.String)
[get-metadata-template-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String,%20java.lang.String...)


Get Enterprise Metadata Templates
---------------------------------

Calling the static [`getEnterpriseMetadataTemplates(BoxAPIConnection, String...)`][get-enterprise-metadata-1] will
return an iterable that will page through all metadata templates within a user's enterprise.
Also [`getEnterpriseMetadataTemplates(String, BoxAPIConnection, String...)`][get-enterprise-metadata-2] and [`getEnterpriseMetadataTemplates(String, int, BoxAPIConnection, String...)`][get-enterprise-metadata-3] can be used to set metadata scope, limit of items per single response.

```java
Iterable<MetadataTemplate> templates = MetadataTemplate.getEnterpriseMetadataTemplates(BoxAPIConnection api);
for (MetadataTemplate templateInfo : templates) {
    // Do something with the metadata template.
}
```

[get-enterprise-metadata-1]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(com.box.sdk.BoxAPIConnection,%20java.lang.String...)
[get-enterprise-metadata-2]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(java.lang.String,%20com.box.sdk.BoxAPIConnection,%20java.lang.String...)
[get-enterprise-metadata-3]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/MetadataTemplate.html#getEnterpriseMetadataTemplates(java.lang.String,%20int,%20com.box.sdk.BoxAPIConnection,%20java.lang.String...)