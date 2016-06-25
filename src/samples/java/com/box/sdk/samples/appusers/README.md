## Using Box with App Users Servlets Example

### Prerequisites

In order to run this example you will need to have Maven installed. On a Mac, you can install Maven with [brew](http://brew.sh/):

```sh
brew install maven
```

Check that your maven version is 3.0.x or above:
```sh
mvn -v
```
###  This is a sample Application and Not Production Code

In order to create the simplest possible code to demonstrate how to create and use App Users from a Java servlet
this sample COMPLETELY IGNORES App User Authentication.  It asks for passwords bu ignores them.


#####Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files
If you don't install this, you'll get an exception about key length. This is not a Box thing, this is a U.S. Government requirement concerning strong encryption. Please follow the instructions *exactly*.
> [Java 7 installer](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

> [Java 8 installer](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

###  Create a Application that supports App Users

You will need to create an application that supports app users and describe in in a configuration file like the following:
```sh
boxClientId=<YOUR_BOX_CLIENT_ID>
boxClientSecret=<YOUR_BOX_CLIENT_SECRET>
boxEnterpriseId=<YOUR_BOX_ENTERPRISE_ID>
boxPrivateKeyFile=<YOUR_JWT_PRIVATE_KEY_FILENAME>
boxPrivateKeyPassword=<YOUR_JWT_PRIVATE_KEY_PASSWORD>
boxPublicKeyId=<YOUR_JWT_PUBLIC_KEY_ID>
```
These steps are described heere
[https://docs.box.com/docs/configuring-box-platform](https://docs.box.com/docs/configuring-box-platform)

### Build and Run with Maven

In order to build and run the project you must execute:
```sh
mvn clean install org.mortbay.jetty:jetty-maven-plugin:run
```

### Or Build and Run with Gradle
```sh
gradle jettyRunWar
```

Then, go to [http://localhost:8080/login](http://localhost:8080/login).
