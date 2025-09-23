# ShieldInformationBarrierSegmentsManager


- [Get shield information barrier segment with specified ID](#get-shield-information-barrier-segment-with-specified-id)
- [Delete shield information barrier segment](#delete-shield-information-barrier-segment)
- [Update shield information barrier segment with specified ID](#update-shield-information-barrier-segment-with-specified-id)
- [List shield information barrier segments](#list-shield-information-barrier-segments)
- [Create shield information barrier segment](#create-shield-information-barrier-segment)

## Get shield information barrier segment with specified ID

Retrieves shield information barrier segment based on provided ID..

This operation is performed by calling function `getShieldInformationBarrierSegmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segments-id/).

<!-- sample get_shield_information_barrier_segments_id -->
```
client.getShieldInformationBarrierSegments().getShieldInformationBarrierSegmentById(segmentId)
```

### Arguments

- shieldInformationBarrierSegmentId `String`
  - The ID of the shield information barrier segment. Example: "3423"
- headers `GetShieldInformationBarrierSegmentByIdHeaders`
  - Headers of getShieldInformationBarrierSegmentById method


### Returns

This function returns a value of type `ShieldInformationBarrierSegment`.

Returns the shield information barrier segment object.


## Delete shield information barrier segment

Deletes the shield information barrier segment
based on provided ID.

This operation is performed by calling function `deleteShieldInformationBarrierSegmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-shield-information-barrier-segments-id/).

<!-- sample delete_shield_information_barrier_segments_id -->
```
client.getShieldInformationBarrierSegments().deleteShieldInformationBarrierSegmentById(segmentId)
```

### Arguments

- shieldInformationBarrierSegmentId `String`
  - The ID of the shield information barrier segment. Example: "3423"
- headers `DeleteShieldInformationBarrierSegmentByIdHeaders`
  - Headers of deleteShieldInformationBarrierSegmentById method


### Returns

This function returns a value of type `void`.

Empty body in response.


## Update shield information barrier segment with specified ID

Updates the shield information barrier segment based on provided ID..

This operation is performed by calling function `updateShieldInformationBarrierSegmentById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-shield-information-barrier-segments-id/).

<!-- sample put_shield_information_barrier_segments_id -->
```
client.getShieldInformationBarrierSegments().updateShieldInformationBarrierSegmentById(segmentId, new UpdateShieldInformationBarrierSegmentByIdRequestBody.Builder().description(updatedSegmentDescription).build())
```

### Arguments

- shieldInformationBarrierSegmentId `String`
  - The ID of the shield information barrier segment. Example: "3423"
- requestBody `UpdateShieldInformationBarrierSegmentByIdRequestBody`
  - Request body of updateShieldInformationBarrierSegmentById method
- headers `UpdateShieldInformationBarrierSegmentByIdHeaders`
  - Headers of updateShieldInformationBarrierSegmentById method


### Returns

This function returns a value of type `ShieldInformationBarrierSegment`.

Returns the updated shield information barrier segment object.


## List shield information barrier segments

Retrieves a list of shield information barrier segment objects
for the specified Information Barrier ID.

This operation is performed by calling function `getShieldInformationBarrierSegments`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-segments/).

<!-- sample get_shield_information_barrier_segments -->
```
client.getShieldInformationBarrierSegments().getShieldInformationBarrierSegments(new GetShieldInformationBarrierSegmentsQueryParams(barrierId))
```

### Arguments

- queryParams `GetShieldInformationBarrierSegmentsQueryParams`
  - Query parameters of getShieldInformationBarrierSegments method
- headers `GetShieldInformationBarrierSegmentsHeaders`
  - Headers of getShieldInformationBarrierSegments method


### Returns

This function returns a value of type `ShieldInformationBarrierSegments`.

Returns a paginated list of shield information barrier segment objects.


## Create shield information barrier segment

Creates a shield information barrier segment.

This operation is performed by calling function `createShieldInformationBarrierSegment`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-shield-information-barrier-segments/).

<!-- sample post_shield_information_barrier_segments -->
```
client.getShieldInformationBarrierSegments().createShieldInformationBarrierSegment(new CreateShieldInformationBarrierSegmentRequestBody.Builder(new ShieldInformationBarrierBase.Builder().id(barrierId).type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER).build(), segmentName).description(segmentDescription).build())
```

### Arguments

- requestBody `CreateShieldInformationBarrierSegmentRequestBody`
  - Request body of createShieldInformationBarrierSegment method
- headers `CreateShieldInformationBarrierSegmentHeaders`
  - Headers of createShieldInformationBarrierSegment method


### Returns

This function returns a value of type `ShieldInformationBarrierSegment`.

Returns a new shield information barrier segment object.


