# TaskAssignmentsManager


- [List task assignments](#list-task-assignments)
- [Assign task](#assign-task)
- [Get task assignment](#get-task-assignment)
- [Update task assignment](#update-task-assignment)
- [Unassign task](#unassign-task)

## List task assignments

Lists all of the assignments for a given task.

This operation is performed by calling function `getTaskAssignments`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-tasks-id-assignments/).

<!-- sample get_tasks_id_assignments -->
```
client.getTaskAssignments().getTaskAssignments(task.getId())
```

### Arguments

- taskId `String`
  - The ID of the task. Example: "12345"
- headers `GetTaskAssignmentsHeaders`
  - Headers of getTaskAssignments method


### Returns

This function returns a value of type `TaskAssignments`.

Returns a collection of task assignment defining what task on
a file has been assigned to which users and by who.


## Assign task

Assigns a task to a user.

A task can be assigned to more than one user by creating multiple
assignments.

This operation is performed by calling function `createTaskAssignment`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-task-assignments/).

<!-- sample post_task_assignments -->
```
client.getTaskAssignments().createTaskAssignment(new CreateTaskAssignmentRequestBody(new CreateTaskAssignmentRequestBodyTaskField.Builder(task.getId()).type(CreateTaskAssignmentRequestBodyTaskTypeField.TASK).build(), new CreateTaskAssignmentRequestBodyAssignToField.Builder().id(currentUser.getId()).build()))
```

### Arguments

- requestBody `CreateTaskAssignmentRequestBody`
  - Request body of createTaskAssignment method
- headers `CreateTaskAssignmentHeaders`
  - Headers of createTaskAssignment method


### Returns

This function returns a value of type `TaskAssignment`.

Returns a new task assignment object.


## Get task assignment

Retrieves information about a task assignment.

This operation is performed by calling function `getTaskAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-task-assignments-id/).

<!-- sample get_task_assignments_id -->
```
client.getTaskAssignments().getTaskAssignmentById(taskAssignment.getId())
```

### Arguments

- taskAssignmentId `String`
  - The ID of the task assignment. Example: "12345"
- headers `GetTaskAssignmentByIdHeaders`
  - Headers of getTaskAssignmentById method


### Returns

This function returns a value of type `TaskAssignment`.

Returns a task assignment, specifying who the task has been assigned to
and by whom.


## Update task assignment

Updates a task assignment. This endpoint can be
used to update the state of a task assigned to a user.

This operation is performed by calling function `updateTaskAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-task-assignments-id/).

<!-- sample put_task_assignments_id -->
```
client.getTaskAssignments().updateTaskAssignmentById(taskAssignment.getId(), new UpdateTaskAssignmentByIdRequestBody.Builder().message("updated message").resolutionState(UpdateTaskAssignmentByIdRequestBodyResolutionStateField.APPROVED).build())
```

### Arguments

- taskAssignmentId `String`
  - The ID of the task assignment. Example: "12345"
- requestBody `UpdateTaskAssignmentByIdRequestBody`
  - Request body of updateTaskAssignmentById method
- headers `UpdateTaskAssignmentByIdHeaders`
  - Headers of updateTaskAssignmentById method


### Returns

This function returns a value of type `TaskAssignment`.

Returns the updated task assignment object.


## Unassign task

Deletes a specific task assignment.

This operation is performed by calling function `deleteTaskAssignmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-task-assignments-id/).

<!-- sample delete_task_assignments_id -->
```
client.getTaskAssignments().deleteTaskAssignmentById(taskAssignment.getId())
```

### Arguments

- taskAssignmentId `String`
  - The ID of the task assignment. Example: "12345"
- headers `DeleteTaskAssignmentByIdHeaders`
  - Headers of deleteTaskAssignmentById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the task
assignment was successfully deleted.


