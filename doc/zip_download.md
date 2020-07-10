Zip Download
======

Allows you to create a temporary zip file of Box files and folders and download them.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Create a Zip File](#create-a-zip-file)
- [Download a Zip File](#download-a-zip-file)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Create a Zip File
---------------

Calling [`BoxZip.create(String name, List<JsonObject> items)`][create-a-zip-file] will let you create a new zip file with the specified name and 
with the specified items and will return a `BoxZipInfo` object with the download link. This file does not show up in your Box account, but will be temporarily 
available for download.

```java
ArrayList<JsonObject> items = new ArrayList<JsonObject>();
JsonObject file = new JsonObject()
                    .add("id", "12345")
                    .add("type", "file");
JsonObject folder = new JsonObject()
                    .add("id", "156472")
                    .add("type", "folder");
items.add(file);
items.add(folder);
BoxZip zip = new BoxZip(this.api);
BoxZipInfo zipInfo = zip.create("Awesome Zip File", items);
```

[create-a-zip-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxZip.html

Download a Zip File
------------

Calling [`BoxZip.download(String name, List<JsonObject> items, OutputStream output)`][download-a-zip-file] will let you create a new zip file 
with the specified name and with the specified items and download it to the stream that is passed in. The return object is `BoxZipDownloadStatus` 
object that containes information about the download, including whether it was successful. The created zip file does not show up in your Box account.

```java
ArrayList<JsonObject> items = new ArrayList<JsonObject>();
JsonObject file = new JsonObject()
                    .add("id", "12345")
                    .add("type", "file");
JsonObject folder = new JsonObject()
                    .add("id", "156472")
                    .add("type", "folder");
items.add(file);
items.add(folder);
BoxZip zip = new BoxZip(this.api);
FileOutputStream stream = new FileOutputStream();
BoxZipDownloadStatus zipDownloadStatus = new BoxZip(api).download("Another Awesome Zip File", items, stream);
stream.close();
if (zipDownloadStatus.getState() == BoxZipDownloadStatus.State.SUCCEEDED) {
    System.out.println("Zip downloaded successfully");
}
```

[download-a-zip-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxZip.html
