SDK Resource Types
==================

All resources require a `BoxAPIConnection` in order to communicate with the Box
API. For more information on how to create an API connection, see the
[Authentication Guide](authentication.md).

* [Files](#files)

Files
-----

File objects represent individual files in Box.

* [Javadoc Documentation](https://gitenterprise.inside-box.net/pages/Box/box-java-sdk/javadoc/com/box/sdk/BoxFile.html)
* [REST API Documentation](https://developers.box.com/docs/#files)

### Get a File's Information

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();
```

#### Only Get Information for Specific Fields

```java
BoxFile file = new BoxFile(api, "id");
// Only get information about a few specific fields.
BoxFile.Info info = file.getInfo("size", "owned_by");
```

### Update a File's Information

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.new Info();
info.setName("New Name");
file.updateInfo(info);
```

### Download a File

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
file.download(stream);
stream.close();
```

#### Track the Progress of a Download

```java
BoxFile file = new BoxFile(api, "id");
BoxFile.Info info = file.getInfo();

FileOutputStream stream = new FileOutputStream(info.getName());
// Provide a ProgressListener to monitor the progress of the download.
file.download(stream, new ProgressListener() {
    public void onProgressChanged(long numBytes, long totalBytes) {
        double percentComplete = numBytes / totalBytes;
    }
});
stream.close();
```

### Upload a File

```java
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
FileInputStream stream = new FileInputStream("My File.txt");
rootFolder.uploadFile(stream, "My File.txt");
stream.close();
```

### Delete a File

```java
BoxFile file = new BoxFile(api, "id");
file.delete();
```
