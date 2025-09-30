# WorkflowsManager


- [List workflows](#list-workflows)
- [Starts workflow based on request body](#starts-workflow-based-on-request-body)

## List workflows

Returns list of workflows that act on a given `folder ID`, and
have a flow with a trigger type of `WORKFLOW_MANUAL_START`.

You application must be authorized to use the `Manage Box Relay` application
scope within the developer console in to use this endpoint.

This operation is performed by calling function `getWorkflows`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-workflows/).

<!-- sample get_workflows -->
```
adminClient.getWorkflows().getWorkflows(new GetWorkflowsQueryParams(workflowFolderId))
```

### Arguments

- queryParams `GetWorkflowsQueryParams`
  - Query parameters of getWorkflows method
- headers `GetWorkflowsHeaders`
  - Headers of getWorkflows method


### Returns

This function returns a value of type `Workflows`.

Returns the workflow.


## Starts workflow based on request body

Initiates a flow with a trigger type of `WORKFLOW_MANUAL_START`.

You application must be authorized to use the `Manage Box Relay` application
scope within the developer console.

This operation is performed by calling function `startWorkflow`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-workflows-id-start/).

<!-- sample post_workflows_id_start -->
```
adminClient.getWorkflows().startWorkflow(workflowToRun.getId(), new StartWorkflowRequestBody.Builder(new StartWorkflowRequestBodyFlowField.Builder().type("flow").id(workflowToRun.getFlows().get(0).getId()).build(), Arrays.asList(new StartWorkflowRequestBodyFilesField.Builder().type(StartWorkflowRequestBodyFilesTypeField.FILE).id(workflowFileId).build()), new StartWorkflowRequestBodyFolderField.Builder().type(StartWorkflowRequestBodyFolderTypeField.FOLDER).id(workflowFolderId).build()).type(StartWorkflowRequestBodyTypeField.WORKFLOW_PARAMETERS).build())
```

### Arguments

- workflowId `String`
  - The ID of the workflow. Example: "12345"
- requestBody `StartWorkflowRequestBody`
  - Request body of startWorkflow method
- headers `StartWorkflowHeaders`
  - Headers of startWorkflow method


### Returns

This function returns a value of type `void`.

Starts the workflow.


