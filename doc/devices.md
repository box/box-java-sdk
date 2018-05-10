Devices
======

Device pinning is a feature that allows enterprise admins to pin their user’s
corporate-managed Box account to a particular mobile device or Box Sync client.

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->


- [Get Enterprise Device Pins](#get-enterprise-device-pins)
- [Get Device Pin](#get-device-pin)
- [Delete Device Pin](#delete-device-pin)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Get Enterprise Device Pins
--------------------------

Calling the static [`getEnterpriceDevicePins(BoxAPIConnection api, String enterpriseID, String... fields)`][get-enterprise-device-pins]
will return an iterable that will page through all of the device pins belongs to
enterprise with given ID. It is possible to specify maximum number of retrieved
items per single response by passing the maxiumum number of records to retrieve to
[`getEnterpriceDevicePins(BoxAPIConnection api, String enterpriseID, int limit, String... fields)`][get-enterprise-device-pins-with-limit]

```java
Iterable<BoxDevicePin.Info> enterpriseDevicePins = BoxDevicePin.getEnterpriceDevicePins(api, id);
for (BoxDevicePin.Info devicePin : enterpriseDevicePins) {
    // Do something with the device pin.
}
```

[get-enterprise-device-pins]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getEnterpriceDevicePins-com.box.sdk.BoxAPIConnection-java.lang.String-java.lang.String...-
[get-enterprise-device-pins-with-limit]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getEnterpriceDevicePins-com.box.sdk.BoxAPIConnection-java.lang.String-int-java.lang.String...-

Get Device Pin
--------------

Existing collections can be retrieved by calling the [`getInfo(String... fields)`][get-device-pin] method.
Optional parameters can be used to retrieve specific fields of the Device Pin object.

```java
BoxDevicePin devicePin = new BoxDevicePin(api, id);
BoxDevicePin.Info devicePinInfo = devicePin.getInfo();
```

[get-device-pin]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getInfo-java.lang.String...-

Delete Device Pin
--------------

A device pin can be deleted by calling the [`delete()`][delete] method.

```java
BoxDevicePin devicePin = new BoxDevicePin(api, id);
devicePin.delete();
```

[delete]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#delete--
