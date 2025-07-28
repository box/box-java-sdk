# TermsOfServicesManager


- [List terms of services](#list-terms-of-services)
- [Create terms of service](#create-terms-of-service)
- [Get terms of service](#get-terms-of-service)
- [Update terms of service](#update-terms-of-service)

## List terms of services

Returns the current terms of service text and settings
for the enterprise.

This operation is performed by calling function `getTermsOfService`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-terms-of-services/).

<!-- sample get_terms_of_services -->
```
client.getTermsOfServices().getTermsOfService()
```

### Arguments

- queryParams `GetTermsOfServiceQueryParams`
  - Query parameters of getTermsOfService method
- headers `GetTermsOfServiceHeaders`
  - Headers of getTermsOfService method


### Returns

This function returns a value of type `TermsOfServices`.

Returns a collection of terms of service text and settings for the
enterprise.


## Create terms of service

Creates a terms of service for a given enterprise
and type of user.

This operation is performed by calling function `createTermsOfService`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-terms-of-services/).

<!-- sample post_terms_of_services -->
```
client.getTermsOfServices().createTermsOfService(new CreateTermsOfServiceRequestBody.Builder(CreateTermsOfServiceRequestBodyStatusField.DISABLED, "Test TOS").tosType(CreateTermsOfServiceRequestBodyTosTypeField.MANAGED).build())
```

### Arguments

- requestBody `CreateTermsOfServiceRequestBody`
  - Request body of createTermsOfService method
- headers `CreateTermsOfServiceHeaders`
  - Headers of createTermsOfService method


### Returns

This function returns a value of type `TermsOfService`.

Returns a new task object.


## Get terms of service

Fetches a specific terms of service.

This operation is performed by calling function `getTermsOfServiceById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-terms-of-services-id/).

*Currently we don't have an example for calling `getTermsOfServiceById` in integration tests*

### Arguments

- termsOfServiceId `String`
  - The ID of the terms of service. Example: "324234"
- headers `GetTermsOfServiceByIdHeaders`
  - Headers of getTermsOfServiceById method


### Returns

This function returns a value of type `TermsOfService`.

Returns a terms of service object.


## Update terms of service

Updates a specific terms of service.

This operation is performed by calling function `updateTermsOfServiceById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/put-terms-of-services-id/).

<!-- sample put_terms_of_services_id -->
```
client.getTermsOfServices().updateTermsOfServiceById(tos.getId(), new UpdateTermsOfServiceByIdRequestBody(UpdateTermsOfServiceByIdRequestBodyStatusField.DISABLED, "TOS"))
```

### Arguments

- termsOfServiceId `String`
  - The ID of the terms of service. Example: "324234"
- requestBody `UpdateTermsOfServiceByIdRequestBody`
  - Request body of updateTermsOfServiceById method
- headers `UpdateTermsOfServiceByIdHeaders`
  - Headers of updateTermsOfServiceById method


### Returns

This function returns a value of type `TermsOfService`.

Returns an updated terms of service object.


