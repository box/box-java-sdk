# SessionTerminationManager


- [Create jobs to terminate users session](#create-jobs-to-terminate-users-session)
- [Create jobs to terminate user group session](#create-jobs-to-terminate-user-group-session)

## Create jobs to terminate users session

Validates the roles and permissions of the user,
and creates asynchronous jobs
to terminate the user's sessions.
Returns the status for the POST request.

This operation is performed by calling function `terminateUsersSessions`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-users-terminate-sessions/).

<!-- sample post_users_terminate_sessions -->
```
client.getSessionTermination().terminateUsersSessions(new TerminateUsersSessionsRequestBody(Arrays.asList(getEnvVar("USER_ID")), Arrays.asList(user.getLogin())))
```

### Arguments

- requestBody `TerminateUsersSessionsRequestBody`
  - Request body of terminateUsersSessions method
- headers `TerminateUsersSessionsHeaders`
  - Headers of terminateUsersSessions method


### Returns

This function returns a value of type `SessionTerminationMessage`.

Returns a message about the request status.


## Create jobs to terminate user group session

Validates the roles and permissions of the group,
and creates asynchronous jobs
to terminate the group's sessions.
Returns the status for the POST request.

This operation is performed by calling function `terminateGroupsSessions`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-groups-terminate-sessions/).

<!-- sample post_groups_terminate_sessions -->
```
client.getSessionTermination().terminateGroupsSessions(new TerminateGroupsSessionsRequestBody(Arrays.asList(group.getId())))
```

### Arguments

- requestBody `TerminateGroupsSessionsRequestBody`
  - Request body of terminateGroupsSessions method
- headers `TerminateGroupsSessionsHeaders`
  - Headers of terminateGroupsSessions method


### Returns

This function returns a value of type `SessionTerminationMessage`.

Returns a message about the request status.


