Authentication
==============

The Box API uses OAuth2 for authentication, which can be difficult to implement.
The SDK makes it easier by providing classes that handle obtaining tokens and
automatically refreshing them.

Ways to Authenticate
--------------------

### Developer Tokens

The fastest way to get started using the API is with developer tokens. A
developer token is simply a short-lived access token that cannot be refreshed
and can only be used with your own account. Therefore, they're only useful for
testing an app and aren't suitable for production. You can obtain a developer
token from your application's [developer
console](https://cloud.app.box.com/developers/services).

The following example creates an API connection with a developer token:

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
```

### Normal Authentication

Using an auth code is the most common way of authenticating with the Box API.
Your application must provide a way for the user to login to Box (usually with a
browser or web view) in order to obtain an auth code.

After a user logs in and grants your application access to their Box account,
they will be redirected to your application's `redirect_uri` which will contain
an auth code. This auth code can then be used along with your client ID and
client secret to establish an API connection.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-AUTH-CODE");
```

### Manual Authentication

In certain advanced scenarios, you may want to obtain an access and refresh
token yourself through manual calls to the API. In this case, you can create an
API connection with the tokens directly.

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
```

Auto-Refresh
------------

By default, a `BoxAPIConnection` will automatically refresh the access token if
it has expired. To disable auto-refresh, set the connection's auto-refresh
setting to false with [`setAutoRefresh(false)`][auto-refresh]. Keep in mind that
you will have to manually refresh the access token yourself.

```java
// This connection won't auto-refresh.
BoxAPIConnection api = new BoxAPIConnection("YOUR-CLIENT-ID",
    "YOUR-CLIENT-SECRET", "YOUR-ACCESS-TOKEN", "YOUR-REFRESH-TOKEN");
api.setAutoRefresh(false);

// If the access token expires, you will have to manually refresh it.
api.refresh();
```

[auto-refresh]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setAutoRefresh(boolean)
