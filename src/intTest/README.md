# Integration Tests

## Running integration tests locally

### Create Custom Application
To run integration tests locally you will need a `Custom App` created at https://cloud.app.box.com/developers/console 
with `Server Authentication (with JWT)` selected as authentication method.
Once created you can edit properties of the application. 

In section `App Access Level` select `App + Enterprise Access`. 

You can enable all `Application Scopes`.

In section `Advanced Features` enable `Make API calls using the as-user header` and `Generate user access tokens`.

Now select `Authorization` and submit application to be reviewed by account admin.

Next part is to obtain application settings. Select `Configuration` tab and in the bottom in the section `App Settings` 
download them as Json.

### Export configuration

You will need to create environment variables in your system or IDE:
 - JAVA_COLLABORATOR_ID - you can find this in `General Settings` tab as `Collaborators` 
 - JAVA_COLLABORATOR - you can find this in `General Settings` tab in field `User ID`
 - JAVA_ENTERPRISE_ID - you can find this in `General Settings` tab in field `Enterprise ID`
 - JAVA_JWT_CONFIG - contains Base64 of your configuration file. You can generate this in command line. On Linux/Mac OS you can use `base64 -i path_to_json_file`.

### Running Tests

You can run tests from command line:
```bash
./gradlew integrationTest
```
just remember that you needx environment variables exported in previous step to be accessible. 

## Useful help classes

### BoxApiProvider
To obtain new API connection configured with provided JWT configuration use `BoxApiProvider`

```java
BoxAPIConnection api = BoxApiProvider.jwtApiForServiceAccount();
```

### UniqueTestFolder
`UniqueTestFolder` contains method that can help setup and remove unique folder for tests and upload files.

To use new unique folder:
```java
UniqueTestFolder.setupUniqeFolder(); // this will create a new test folder that can be used in tests
UniqueTestFolder.getUniqueFolder(api); // this will retrieve unique folder
UniqueTestFolder.removeUniqueFolder(); // this will remove unique folder with all subfolders and files
```

To upload files to root of the unique folder:
```java
// to upload file with specified content
BoxFile fileWithSpecifiedContent = UniqueTestFolder.uploadFileToUniqueFolder(api, "File Name", "Your content");

// to upload a file with some content
BoxFile fileWithSomeContent = UniqueTestFolder.uploadFileToUniqueFolderWithSomeContent(api, "File Name");
```

You can also upload file to some specified folder:
```java
BoxFolder destinationFolder = UniqueTestFolder.getUniqueFolder(api); // obtain folder to upload file to
BoxFile fileWithSomeContent = UniqueTestFolder.uploadFileWithSomeContent("File Name", destinationFolder);
```

### Retry
Sometimes API request can take some time to be processed and you might want to retry some logic when error occurs:

```java
Retry.retry(
    () -> {
        BoxSignRequest signRequestGetByID = new BoxSignRequest(api, "signRequestId");
        // do an API call, assert response - if call fails it will be repated
        BoxSignRequest.Info cancelationIfo = signRequestGetByID.cancel();
    }, 
    5, // how many times retry the call
    1000 // how long to wait between calls in miliseconds
);
```

## Putting this all together

Here is a sample test that uses some of the utility classes shown above:

```java
public class BoxFolderIT {

    @BeforeClass
    public static void setup() {
        // create unique folder for all tests
        UniqueTestFolder.setupUniqeFolder();
    }

    @AfterClass
    public static void tearDown() {
        // remove unique folder after all tests run
        UniqueTestFolder.removeUniqueFolder();
    }

    @Test
    public void creatingAndDeletingFolderSucceeds() {
        // given
        BoxAPIConnection api = BoxApiProvider.jwtApiForServiceAccount();
        BoxFolder rootFolder = UniqueTestFolder.getUniqueFolder(api);
        
        // expect new folder can be found
        // create subfolder in unique test folder
        BoxFolder childFolder = rootFolder.createFolder("My Folder").getResource();
        assertThat(rootFolder,
            hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID()))));

        // expect folder cannot be found after deletion
        childFolder.delete(false);
        assertThat(rootFolder,
            not(hasItem(Matchers.<BoxItem.Info>hasProperty("ID", equalTo(childFolder.getID())))));
    }
}
```