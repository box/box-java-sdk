# IntegrationMappingsManager


- [List Slack integration mappings](#list-slack-integration-mappings)
- [Create Slack integration mapping](#create-slack-integration-mapping)
- [Update Slack integration mapping](#update-slack-integration-mapping)
- [Delete Slack integration mapping](#delete-slack-integration-mapping)
- [List Teams integration mappings](#list-teams-integration-mappings)
- [Create Teams integration mapping](#create-teams-integration-mapping)
- [Update Teams integration mapping](#update-teams-integration-mapping)
- [Delete Teams integration mapping](#delete-teams-integration-mapping)

## List Slack integration mappings

Lists [Slack integration mappings](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack) in a users' enterprise.

You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `getSlackIntegrationMapping`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-integration-mappings-slack/).

<!-- sample get_integration_mappings_slack -->
```
userClient.getIntegrationMappings().getSlackIntegrationMapping()
```

### Arguments

- queryParams `GetSlackIntegrationMappingQueryParams`
  - Query parameters of getSlackIntegrationMapping method
- headers `GetSlackIntegrationMappingHeaders`
  - Headers of getSlackIntegrationMapping method


### Returns

This function returns a value of type `IntegrationMappings`.

Returns a collection of integration mappings.


## Create Slack integration mapping

Creates a [Slack integration mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack)
by mapping a Slack channel to a Box item.

You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `createSlackIntegrationMapping`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-integration-mappings-slack/).

<!-- sample post_integration_mappings_slack -->
```
userClient.getIntegrationMappings().createSlackIntegrationMapping(new IntegrationMappingSlackCreateRequest(new IntegrationMappingPartnerItemSlack.Builder(slackPartnerItemId).slackOrgId(slackOrgId).build(), new IntegrationMappingBoxItemSlack(folder.getId())))
```

### Arguments

- requestBody `IntegrationMappingSlackCreateRequest`
  - Request body of createSlackIntegrationMapping method
- headers `CreateSlackIntegrationMappingHeaders`
  - Headers of createSlackIntegrationMapping method


### Returns

This function returns a value of type `IntegrationMapping`.

Returns the created integration mapping.


## Update Slack integration mapping

Updates a [Slack integration mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).
Supports updating the Box folder ID and options.

You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `updateSlackIntegrationMappingById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-integration-mappings-slack-id/).

<!-- sample put_integration_mappings_slack_id -->
```
userClient.getIntegrationMappings().updateSlackIntegrationMappingById(slackIntegrationMapping.getId(), new UpdateSlackIntegrationMappingByIdRequestBody.Builder().boxItem(new IntegrationMappingBoxItemSlack(folder.getId())).build())
```

### Arguments

- integrationMappingId `String`
  - An ID of an integration mapping. Example: "11235432"
- requestBody `UpdateSlackIntegrationMappingByIdRequestBody`
  - Request body of updateSlackIntegrationMappingById method
- headers `UpdateSlackIntegrationMappingByIdHeaders`
  - Headers of updateSlackIntegrationMappingById method


### Returns

This function returns a value of type `IntegrationMapping`.

Returns the updated integration mapping object.


## Delete Slack integration mapping

Deletes a [Slack integration mapping](https://support.box.com/hc/en-us/articles/4415585987859-Box-as-the-Content-Layer-for-Slack).


You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `deleteSlackIntegrationMappingById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-integration-mappings-slack-id/).

<!-- sample delete_integration_mappings_slack_id -->
```
userClient.getIntegrationMappings().deleteSlackIntegrationMappingById(slackIntegrationMapping.getId())
```

### Arguments

- integrationMappingId `String`
  - An ID of an integration mapping. Example: "11235432"
- headers `DeleteSlackIntegrationMappingByIdHeaders`
  - Headers of deleteSlackIntegrationMappingById method


### Returns

This function returns a value of type `void`.

Empty body in response.


## List Teams integration mappings

Lists [Teams integration mappings](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams) in a users' enterprise.
You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `getTeamsIntegrationMapping`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-integration-mappings-teams/).

<!-- sample get_integration_mappings_teams -->
```
userClient.getIntegrationMappings().getTeamsIntegrationMapping()
```

### Arguments

- queryParams `GetTeamsIntegrationMappingQueryParams`
  - Query parameters of getTeamsIntegrationMapping method
- headers `GetTeamsIntegrationMappingHeaders`
  - Headers of getTeamsIntegrationMapping method


### Returns

This function returns a value of type `IntegrationMappingsTeams`.

Returns a collection of integration mappings.


## Create Teams integration mapping

Creates a [Teams integration mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams)
by mapping a Teams channel to a Box item.
You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `createTeamsIntegrationMapping`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-integration-mappings-teams/).

<!-- sample post_integration_mappings_teams -->
```
userClient.getIntegrationMappings().createTeamsIntegrationMapping(new IntegrationMappingTeamsCreateRequest(new IntegrationMappingPartnerItemTeamsCreateRequest(IntegrationMappingPartnerItemTeamsCreateRequestTypeField.CHANNEL, partnerItemId, tenantId, teamId), new FolderReference(folder.getId())))
```

### Arguments

- requestBody `IntegrationMappingTeamsCreateRequest`
  - Request body of createTeamsIntegrationMapping method
- headers `CreateTeamsIntegrationMappingHeaders`
  - Headers of createTeamsIntegrationMapping method


### Returns

This function returns a value of type `IntegrationMappingTeams`.

Returns the created integration mapping.


## Update Teams integration mapping

Updates a [Teams integration mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams).
Supports updating the Box folder ID and options.
You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `updateTeamsIntegrationMappingById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-integration-mappings-teams-id/).

<!-- sample put_integration_mappings_teams_id -->
```
userClient.getIntegrationMappings().updateTeamsIntegrationMappingById(integrationMappingId, new UpdateTeamsIntegrationMappingByIdRequestBody.Builder().boxItem(new FolderReference("1234567")).build())
```

### Arguments

- integrationMappingId `String`
  - An ID of an integration mapping. Example: "11235432"
- requestBody `UpdateTeamsIntegrationMappingByIdRequestBody`
  - Request body of updateTeamsIntegrationMappingById method
- headers `UpdateTeamsIntegrationMappingByIdHeaders`
  - Headers of updateTeamsIntegrationMappingById method


### Returns

This function returns a value of type `IntegrationMappingTeams`.

Returns the updated integration mapping object.


## Delete Teams integration mapping

Deletes a [Teams integration mapping](https://support.box.com/hc/en-us/articles/360044681474-Using-Box-for-Teams).
You need Admin or Co-Admin role to
use this endpoint.

This operation is performed by calling function `deleteTeamsIntegrationMappingById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-integration-mappings-teams-id/).

<!-- sample delete_integration_mappings_teams_id -->
```
userClient.getIntegrationMappings().deleteTeamsIntegrationMappingById(integrationMappingId)
```

### Arguments

- integrationMappingId `String`
  - An ID of an integration mapping. Example: "11235432"
- headers `DeleteTeamsIntegrationMappingByIdHeaders`
  - Headers of deleteTeamsIntegrationMappingById method


### Returns

This function returns a value of type `void`.

Empty body in response.


