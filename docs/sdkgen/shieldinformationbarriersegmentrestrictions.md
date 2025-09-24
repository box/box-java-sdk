# ShieldInformationBarrierSegmentRestrictionsManager


- [Get shield information barrier segment restriction by ID](#get-shield-information-barrier-segment-restriction-by-id)
- [Delete shield information barrier segment restriction by ID](#delete-shield-information-barrier-segment-restriction-by-id)
- [List shield information barrier segment restrictions](#list-shield-information-barrier-segment-restrictions)
- [Create shield information barrier segment restriction](#create-shield-information-barrier-segment-restriction)

## Get shield information barrier segment restriction by ID

Retrieves a shield information barrier segment
restriction based on provided ID.

This operation is performed by calling function `getShieldInformationBarrierSegmentRestrictionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segment-restrictions-id/).

<!-- sample get_shield_information_barrier_segment_restrictions_id -->
```
client.getShieldInformationBarrierSegmentRestrictions().getShieldInformationBarrierSegmentRestrictionById(segmentRestrictionId)
```

### Arguments

- shieldInformationBarrierSegmentRestrictionId `String`
  - The ID of the shield information barrier segment Restriction. Example: "4563"
- headers `GetShieldInformationBarrierSegmentRestrictionByIdHeaders`
  - Headers of getShieldInformationBarrierSegmentRestrictionById method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentRestriction`.

Returns the shield information barrier segment
restriction object.


## Delete shield information barrier segment restriction by ID

Delete shield information barrier segment restriction
based on provided ID.

This operation is performed by calling function `deleteShieldInformationBarrierSegmentRestrictionById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-shield-information-barrier-segment-restrictions-id/).

<!-- sample delete_shield_information_barrier_segment_restrictions_id -->
```
client.getShieldInformationBarrierSegmentRestrictions().deleteShieldInformationBarrierSegmentRestrictionById(segmentRestrictionId)
```

### Arguments

- shieldInformationBarrierSegmentRestrictionId `String`
  - The ID of the shield information barrier segment Restriction. Example: "4563"
- headers `DeleteShieldInformationBarrierSegmentRestrictionByIdHeaders`
  - Headers of deleteShieldInformationBarrierSegmentRestrictionById method


### Returns

This function returns a value of type `void`.

Empty body in response.


## List shield information barrier segment restrictions

Lists shield information barrier segment restrictions
based on provided segment ID.

This operation is performed by calling function `getShieldInformationBarrierSegmentRestrictions`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segment-restrictions/).

<!-- sample get_shield_information_barrier_segment_restrictions -->
```
client.getShieldInformationBarrierSegmentRestrictions().getShieldInformationBarrierSegmentRestrictions(new GetShieldInformationBarrierSegmentRestrictionsQueryParams(segmentId))
```

### Arguments

- queryParams `GetShieldInformationBarrierSegmentRestrictionsQueryParams`
  - Query parameters of getShieldInformationBarrierSegmentRestrictions method
- headers `GetShieldInformationBarrierSegmentRestrictionsHeaders`
  - Headers of getShieldInformationBarrierSegmentRestrictions method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentRestrictions`.

Returns a paginated list of
shield information barrier segment restriction objects.


## Create shield information barrier segment restriction

Creates a shield information barrier
segment restriction object.

This operation is performed by calling function `createShieldInformationBarrierSegmentRestriction`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-shield-information-barrier-segment-restrictions/).

<!-- sample post_shield_information_barrier_segment_restrictions -->
```
client.getShieldInformationBarrierSegmentRestrictions().createShieldInformationBarrierSegmentRestriction(new CreateShieldInformationBarrierSegmentRestrictionRequestBody.Builder(new CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField.Builder().id(segmentId).type(CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField.SHIELD_INFORMATION_BARRIER_SEGMENT).build(), new CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField.Builder().id(segmentToRestrictId).type(CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField.SHIELD_INFORMATION_BARRIER_SEGMENT).build()).type(CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField.SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION).build())
```

### Arguments

- requestBody `CreateShieldInformationBarrierSegmentRestrictionRequestBody`
  - Request body of createShieldInformationBarrierSegmentRestriction method
- headers `CreateShieldInformationBarrierSegmentRestrictionHeaders`
  - Headers of createShieldInformationBarrierSegmentRestriction method


### Returns

This function returns a value of type `ShieldInformationBarrierSegmentRestriction`.

Returns the newly created Shield
Information Barrier Segment Restriction object.


