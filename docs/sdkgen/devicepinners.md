# DevicePinnersManager


- [Get device pin](#get-device-pin)
- [Remove device pin](#remove-device-pin)
- [List enterprise device pins](#list-enterprise-device-pins)

## Get device pin

Retrieves information about an individual device pin.

This operation is performed by calling function `getDevicePinnerById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-device-pinners-id/).

<!-- sample get_device_pinners_id -->
```
client.getDevicePinners().getDevicePinnerById(devicePinnerId)
```

### Arguments

- devicePinnerId `String`
  - The ID of the device pin. Example: "2324234"
- headers `GetDevicePinnerByIdHeaders`
  - Headers of getDevicePinnerById method


### Returns

This function returns a value of type `DevicePinner`.

Returns information about a single device pin.


## Remove device pin

Deletes an individual device pin.

This operation is performed by calling function `deleteDevicePinnerById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/delete-device-pinners-id/).

<!-- sample delete_device_pinners_id -->
```
client.getDevicePinners().deleteDevicePinnerById(devicePinnerId)
```

### Arguments

- devicePinnerId `String`
  - The ID of the device pin. Example: "2324234"
- headers `DeleteDevicePinnerByIdHeaders`
  - Headers of deleteDevicePinnerById method


### Returns

This function returns a value of type `void`.

Returns an empty response when the pin has been deleted.


## List enterprise device pins

Retrieves all the device pins within an enterprise.

The user must have admin privileges, and the application
needs the "manage enterprise" scope to make this call.

This operation is performed by calling function `getEnterpriseDevicePinners`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-enterprises-id-device-pinners/).

<!-- sample get_enterprises_id_device_pinners -->
```
client.getDevicePinners().getEnterpriseDevicePinners(enterpriseId)
```

### Arguments

- enterpriseId `String`
  - The ID of the enterprise. Example: "3442311"
- queryParams `GetEnterpriseDevicePinnersQueryParams`
  - Query parameters of getEnterpriseDevicePinners method
- headers `GetEnterpriseDevicePinnersHeaders`
  - Headers of getEnterpriseDevicePinners method


### Returns

This function returns a value of type `DevicePinners`.

Returns a list of device pins for a given enterprise.


