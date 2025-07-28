# ShieldInformationBarrierReportsManager


- [List shield information barrier reports](#list-shield-information-barrier-reports)
- [Create shield information barrier report](#create-shield-information-barrier-report)
- [Get shield information barrier report by ID](#get-shield-information-barrier-report-by-id)

## List shield information barrier reports

Lists shield information barrier reports.

This operation is performed by calling function `getShieldInformationBarrierReports`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-reports/).

<!-- sample get_shield_information_barrier_reports -->
```
client.getShieldInformationBarrierReports().getShieldInformationBarrierReports(new GetShieldInformationBarrierReportsQueryParams(barrierId))
```

### Arguments

- queryParams `GetShieldInformationBarrierReportsQueryParams`
  - Query parameters of getShieldInformationBarrierReports method
- headers `GetShieldInformationBarrierReportsHeaders`
  - Headers of getShieldInformationBarrierReports method


### Returns

This function returns a value of type `ShieldInformationBarrierReports`.

Returns a paginated list of shield information barrier report objects.


## Create shield information barrier report

Creates a shield information barrier report for a given barrier.

This operation is performed by calling function `createShieldInformationBarrierReport`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/post-shield-information-barrier-reports/).

<!-- sample post_shield_information_barrier_reports -->
```
client.getShieldInformationBarrierReports().createShieldInformationBarrierReport(new ShieldInformationBarrierReference.Builder().shieldInformationBarrier(new ShieldInformationBarrierBase.Builder().id(barrierId).type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER).build()).build())
```

### Arguments

- requestBody `ShieldInformationBarrierReference`
  - Request body of createShieldInformationBarrierReport method
- headers `CreateShieldInformationBarrierReportHeaders`
  - Headers of createShieldInformationBarrierReport method


### Returns

This function returns a value of type `ShieldInformationBarrierReport`.

Returns the shield information barrier report information object.


## Get shield information barrier report by ID

Retrieves a shield information barrier report by its ID.

This operation is performed by calling function `getShieldInformationBarrierReportById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-shield-information-barrier-reports-id/).

<!-- sample get_shield_information_barrier_reports_id -->
```
client.getShieldInformationBarrierReports().getShieldInformationBarrierReportById(createdReport.getId())
```

### Arguments

- shieldInformationBarrierReportId `String`
  - The ID of the shield information barrier Report. Example: "3423"
- headers `GetShieldInformationBarrierReportByIdHeaders`
  - Headers of getShieldInformationBarrierReportById method


### Returns

This function returns a value of type `ShieldInformationBarrierReport`.

Returns the  shield information barrier report object.


