# ShieldInformationBarrierSegmentMembersManager


- [Get shield information barrier segment member by ID](#get-shield-information-barrier-segment-member-by-id)
- [Delete shield information barrier segment member by ID](#delete-shield-information-barrier-segment-member-by-id)
- [List shield information barrier segment members](#list-shield-information-barrier-segment-members)
- [Create shield information barrier segment member](#create-shield-information-barrier-segment-member)

## Get shield information barrier segment member by ID

Retrieves a shield information barrier
segment member by its ID.

This operation is performed by calling function `getShieldInformationBarrierSegmentMemberById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segment-members-id/).

<!-- sample get_shield_information_barrier_segment_members_id -->
```
client.getShieldInformationBarrierSegmentMembers().getShieldInformationBarrierSegmentMemberById(segmentMember.getId())
```

### Arguments

- shieldInformationBarrierSegmentMemberId `String`
  - The ID of the shield information barrier segment Member. Example: "7815"
- headers `GetShieldInformationBarrierSegmentMemberByIdHeaders`
  - Headers of getShieldInformationBarrierSegmentMemberById method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentMember`.

Returns the shield information barrier segment member object.


## Delete shield information barrier segment member by ID

Deletes a shield information barrier
segment member based on provided ID.

This operation is performed by calling function `deleteShieldInformationBarrierSegmentMemberById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-shield-information-barrier-segment-members-id/).

<!-- sample delete_shield_information_barrier_segment_members_id -->
```
client.getShieldInformationBarrierSegmentMembers().deleteShieldInformationBarrierSegmentMemberById(segmentMember.getId())
```

### Arguments

- shieldInformationBarrierSegmentMemberId `String`
  - The ID of the shield information barrier segment Member. Example: "7815"
- headers `DeleteShieldInformationBarrierSegmentMemberByIdHeaders`
  - Headers of deleteShieldInformationBarrierSegmentMemberById method


### Returns

This function returns a value of type `void`.

Returns an empty response if the
segment member was deleted successfully.


## List shield information barrier segment members

Lists shield information barrier segment members
based on provided segment IDs.

This operation is performed by calling function `getShieldInformationBarrierSegmentMembers`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segment-members/).

<!-- sample get_shield_information_barrier_segment_members -->
```
client.getShieldInformationBarrierSegmentMembers().getShieldInformationBarrierSegmentMembers(new GetShieldInformationBarrierSegmentMembersQueryParams(segment.getId()))
```

### Arguments

- queryParams `GetShieldInformationBarrierSegmentMembersQueryParams`
  - Query parameters of getShieldInformationBarrierSegmentMembers method
- headers `GetShieldInformationBarrierSegmentMembersHeaders`
  - Headers of getShieldInformationBarrierSegmentMembers method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentMembers`.

Returns a paginated list of
shield information barrier segment member objects.


## Create shield information barrier segment member

Creates a new shield information barrier segment member.

This operation is performed by calling function `createShieldInformationBarrierSegmentMember`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-shield-information-barrier-segment-members/).

<!-- sample post_shield_information_barrier_segment_members -->
```
client.getShieldInformationBarrierSegmentMembers().createShieldInformationBarrierSegmentMember(new CreateShieldInformationBarrierSegmentMemberRequestBody(new CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField.Builder().id(segment.getId()).type(CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField.SHIELD_INFORMATION_BARRIER_SEGMENT).build(), new UserBase(getEnvVar("USER_ID"))))
```

### Arguments

- requestBody `CreateShieldInformationBarrierSegmentMemberRequestBody`
  - Request body of createShieldInformationBarrierSegmentMember method
- headers `CreateShieldInformationBarrierSegmentMemberHeaders`
  - Headers of createShieldInformationBarrierSegmentMember method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentMember`.

Returns a new shield information barrier segment member object.


