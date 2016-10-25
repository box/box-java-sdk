Devices
======

Device pinning is a feature that allows enterprise admins to pin their user’s corporate-managed Box account to a particular mobile device or Box Sync client.

* [Get Device Pin](#get-device-pin)

Get Device Pin
--------------

Existing collections can be retrieved by calling the [`getInfo(String...)`][get-device-pin] method.
Optional parameters can be used to retrieve specific fields of the Device Pin object.

```java
BoxDevicePin devicePin = new BoxDevicePin(api, id);
BoxDevicePin.Info devicePinInfo = devicePin.getInfo();
```

[get-device-pin]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxDevicePin.html#getInfo(java.lang.String...)