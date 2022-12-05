# Android

The Java SDK should be compatible with modern Android applications written in both Java and Kotlin.

To use the Java SDK in an android application add it to the project's gradle file in the `dependencies` block.

For Groovy

```groovy
// build.gradle

dependencies {
    implementation "com.box:box-java-sdk:3.8.0"
}
```

For Kotlin

```kotlin
// build.gradle.kts

dependencies {
    implementation("com.box:box-java-sdk:3.8.0")
}
```

## Kotlin

The Java SDK can also be used in Kotlin Android applications through interoperability thanks to the Kotlin design. 
You can read more about Kotlin and Java interoperability [here](https://kotlinlang.org/docs/java-interop.html)

The following example creates an API connection with a developer token:

```kotlin
val api = BoxAPIConnection("myToken")
```

The following example shows how to get current user 

```kotlin
val userID = "33333"
val api = BoxAPIConnection("myToken")
val user = BoxUser(api, userID)
val userInfo = user.getInfo()
```

If you are using an IntelliJ-based IDE, you can copy our samples located in the [docs](/doc/Readme.md) directory 
and paste them into your file. The IDE should ask you to convert the pasted Java sample to Kotlin. Most samples still work after conversion using this approach.

Note that the current Java SDK does not support Kotlin coroutines. By default, you cannot run network calls on the main thread 
in an Android application. There are various ways to overcome this. For example, if you are in a viewModel context, you can run the SDK method as a 
courutine using viewModelScope 

```kotlin
viewModelScope.launch {
    val result = withContext(Dispatchers.IO) {
        /*
            SDK code goes here
        */
    }
    // here you can access the result and load it to the viewModel
}
```

The following example shows how to get the current items in the root folder, sorted by name in ascending order with additional
"created_by" and "name" fields returned from the API. The items are then loaded to the custom data class defined earlier.

```kotlin
// data class definition used in viewModel
data class Item(
    val isFolder: Boolean,
    val name: String,
    val createdBy: String
)

// viewModel init code
viewModelScope.launch {
    val result = withContext(Dispatchers.IO) {
        val res = BoxFolder(BoxAPIConnection("myToken"), "0")
        val iterator: Iterator<BoxItem.Info> =
            res.getChildren("name", BoxFolder.SortDirection.ASC, "created_by", "name")
                .iterator() 
        val items = mutableListOf<Item>()

        when (val itemInfo = iterator.next()) {
            is BoxFile.Info -> items.add(Item(false, "File " + itemInfo.name, itemInfo.createdBy.name))
            is BoxFolder.Info -> items.add(Item(true, "Folder " + itemInfo.name, itemInfo.createdBy.name)) 
        }
        items
    }          
}      
```

If you are familiar with Kotlin syntax, you might have noticed that we could have used the `.map` function (or a similar function) to map the API result to a list of items. Due to current limitations, using `.map` and similar operations on collections is not always possible and may 
lead to unexpected results. The preferred way is to use an explicit iterator to iterate over the collections returned by the SDK.

If you find any problem related to the Java SDK in Kotlin-based app feel free to open an issue.