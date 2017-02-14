Devices
======

Device pinning is a feature that allows enterprise admins to pin their user’s corporate-managed Box account to a particular mobile device or Box Sync client.

* [Get Enterprise Device Pins](#get-enterprise-device-pins)
* [Get Device Pin](#get-device-pin)
* [Delete Device Pin](#delete-device-pin)

Get Enterprise Device Pins
--------------------------

Calling the static [`getEnterpriceDevicePins(BoxAPIConnection, String, String...)`][get-enterprise-device-pins] will return an iterable that will page through all of the device pins belongs to enterprise with given id. It is possible to specify maximum number of retrieved items per single response calling [`getEnterpriceDevicePins(BoxAPIConnection, String, int, String...)`][get-enterprise-device-pins-with-limit]

```java
Iterable<BoxDevicePin.Info> enterpriceDevicePins = BoxDevicePin.getEnterpriceDevicePins(api, id);
for (BoxDevicePin.Info devicePin : enterpriseDevicePins) {
    // Do something with the device pin.
}
```

[get-enterprise-device-pins]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getEnterpriceDevicePins(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20java.lang.String...)
[get-enterprise-device-pins-with-limit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getEnterpriceDevicePins(com.box.sdk.BoxAPIConnection,%20java.lang.String,%20int,%20java.lang.String...)

Get Device Pin
--------------

Existing collections can be retrieved by calling the [`getInfo(String...)`][get-device-pin] method.
Optional parameters can be used to retrieve specific fields of the Device Pin object.

```java
BoxDevicePin devicePin = new BoxDevicePin(api, id);
BoxDevicePin.Info devicePinInfo = devicePin.getInfo();
```

[get-device-pin]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getInfo(java.lang.String...)

Delete Device Pin
--------------

A device pin can be deleted by calling the [`delete()`][delete] method.

```java
BoxDevicePin devicePin = new BoxDevicePin(api, id);
devicePin.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#delete()