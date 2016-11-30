Watermarking
======

The ability to watermark files and folders is represented as a sub-resource on the Files and Folders resources, respectively. You can think of the sub-resource as a "label" marking whether the file or folder is watermarked or not. If you apply a watermark label to a folder, then all files inside of it will be protected by the watermark (e.g. previews will be watermarked). However, those files' watermark sub-resource is independent from the folder that got watermarked. This allows you to watermark files and folders independently.

* [Get Watermark on File](#get-watermark-on-file)
* [Apply Watermark on File](#apply-watermark-on-file)
* [Remove Watermark on File](#remove-watermark-on-file)
* [Get Watermark on Folder](#get-watermark-on-folder)
* [Apply Watermark on Folder](#apply-watermark-on-folder)
* [Remove Watermark on Folder](#remove-watermark-on-folder)

Get Watermark on File
--------------

Calling [`getWatermark(String...)`][get-watermark-on-file] will return a BoxWatermark object containing information about the watermark associated for this file. If the file does not have a watermark applied on it, a 404 Not Found will be returned.

```java
BoxFile file = new BoxFile(api, id);
BoxWatermark watermark = file.getWatermark();
```

[get-watermark-on-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getWatermark(java.lang.String...)

Apply Watermark on File
--------------

To apply watermark on file, call [`applyWatermark()`][apply-watermark-on-file] method. While the endpoint accepts a JSON body describing the watermark to apply, custom watermarks are not supported yet.
The method will return a BoxWatermark object containing information about the watermark applied on this file.

```java
BoxFile file = new BoxFile(api, id);
file.applyWatermark();
```

[apply-watermark-on-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#applyWatermark()

Remove Watermark on File
--------------

A watermark can be removed by calling the [`removeWatermark()`][remove-watermark-on-file] method.
If the file did not have a watermark applied on it, a 404 Not Found will be returned by API.

```java
BoxFile file = new BoxFile(api, id);
file.removeWatermark();
```

[remove-watermark-on-file]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#removeWatermark()

Get Watermark on Folder
--------------

Calling [`getWatermark(String...)`][get-watermark-on-folder] will return a BoxWatermark object containing information about the watermark associated for this folder. If the folder does not have a watermark applied on it, a 404 Not Found will be returned.

```java
BoxFolder folder = new BoxFolder(api, id);
BoxWatermark watermark = folder.getWatermark();
```

[get-watermark-on-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#getWatermark(java.lang.String...)

Apply Watermark on Folder
--------------

To apply watermark on folder, call [`applyWatermark()`][apply-watermark-on-folder] method. While the endpoint accepts a JSON body describing the watermark to apply, custom watermarks are not supported yet.
The method will return a BoxWatermark object containing information about the watermark applied on this folder.

```java
BoxFolder folder = new BoxFolder(api, id);
fodler.applyWatermark();
```

[apply-watermark-on-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#applyWatermark()

Remove Watermark on Folder
--------------

A watermark can be removed by calling the [`removeWatermark()`][remove-watermark-on-folder] method.
If the folder did not have a watermark applied on it, a 404 Not Found will be returned by API.

```java
BoxFolder folder = new BoxFolder(api, id);
folder.removeWatermark();
```

[remove-watermark-on-folder]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#removeWatermark()
