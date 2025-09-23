# EmailAliasesManager


- [List user's email aliases](#list-users-email-aliases)
- [Create email alias](#create-email-alias)
- [Remove email alias](#remove-email-alias)

## List user's email aliases

Retrieves all email aliases for a user. The collection
does not include the primary login for the user.

This operation is performed by calling function `getUserEmailAliases`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-users-id-email-aliases/).

<!-- sample get_users_id_email_aliases -->
```
client.getEmailAliases().getUserEmailAliases(newUser.getId())
```

### Arguments

- userId `String`
  - The ID of the user. Example: "12345"
- headers `GetUserEmailAliasesHeaders`
  - Headers of getUserEmailAliases method


### Returns

This function returns a value of type `EmailAliases`.

Returns a collection of email aliases.


## Create email alias

Adds a new email alias to a user account..

This operation is performed by calling function `createUserEmailAlias`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-users-id-email-aliases/).

<!-- sample post_users_id_email_aliases -->
```
client.getEmailAliases().createUserEmailAlias(newUser.getId(), new CreateUserEmailAliasRequestBody(newAliasEmail))
```

### Arguments

- userId `String`
  - The ID of the user. Example: "12345"
- requestBody `CreateUserEmailAliasRequestBody`
  - Request body of createUserEmailAlias method
- headers `CreateUserEmailAliasHeaders`
  - Headers of createUserEmailAlias method


### Returns

This function returns a value of type `EmailAlias`.

Returns the newly created email alias object.


## Remove email alias

Removes an email alias from a user.

This operation is performed by calling function `deleteUserEmailAliasById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-users-id-email-aliases-id/).

<!-- sample delete_users_id_email_aliases_id -->
```
client.getEmailAliases().deleteUserEmailAliasById(newUser.getId(), newAlias.getId())
```

### Arguments

- userId `String`
  - The ID of the user. Example: "12345"
- emailAliasId `String`
  - The ID of the email alias. Example: "23432"
- headers `DeleteUserEmailAliasByIdHeaders`
  - Headers of deleteUserEmailAliasById method


### Returns

This function returns a value of type `void`.

Removes the alias and returns an empty response.


