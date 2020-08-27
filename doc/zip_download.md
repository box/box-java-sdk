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

Calling [`BoxZip.create(String name, List<BoxZipItem> items)`][create-a-zip-file] will let you create a new zip file with the specified name and 
with the specified items and will return a `BoxZipInfo` object with the download link. This file does not show up in your Box account, but will be temporarily 
available for download.

<!-- sample post_zip_downloads -->
```java
ArrayList<BoxZipItem> items = new ArrayList<BoxZipItem>();
BoxZipItem file = new BoxZipItem("file", "12345");
BoxZipItem folder = new BoxZipItem("folder", "156472");
items.add(file);
items.add(folder);
BoxZip zip = new BoxZip(api);
BoxZipInfo zipInfo = zip.create("Awesome Zip File", items);
```

[create-a-zip-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxZip.html

Download a Zip File
------------

Calling [`BoxZip.download(String name, List<BoxZipItem> items, OutputStream output)`][download-a-zip-file] will let you create a new zip file 
with the specified name and with the specified items and download it to the stream that is passed in. The return object is `BoxZipDownloadStatus` 
object that containes information about the download, including whether it was successful. The created zip file does not show up in your Box account.

<!-- sample get_zip_downloads_id_content -->
```java
ArrayList<BoxZipItem> items = new ArrayList<BoxZipItem>();
BoxZipItem file = new BoxZipItem("file", "12345");
BoxZipItem folder = new BoxZipItem("folder", "156472");
items.add(file);
items.add(folder);
BoxZip zip = new BoxZip(api);
FileOutputStream stream = new FileOutputStream();
BoxZipDownloadStatus zipDownloadStatus = new BoxZip(api).download("Another Awesome Zip File", items, stream);
stream.close();
if (zipDownloadStatus.getState() == BoxZipDownloadStatus.State.SUCCEEDED) {
    System.out.println("Zip downloaded successfully");
}
```

[download-a-zip-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxZip.html
