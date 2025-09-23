# SignTemplatesManager


- [List Box Sign templates](#list-box-sign-templates)
- [Get Box Sign template by ID](#get-box-sign-template-by-id)

## List Box Sign templates

Gets Box Sign templates created by a user.

This operation is performed by calling function `getSignTemplates`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-sign-templates/).

<!-- sample get_sign_templates -->
```
client.getSignTemplates().getSignTemplates(new GetSignTemplatesQueryParams.Builder().limit(2L).build())
```

### Arguments

- queryParams `GetSignTemplatesQueryParams`
  - Query parameters of getSignTemplates method
- headers `GetSignTemplatesHeaders`
  - Headers of getSignTemplates method


### Returns

This function returns a value of type `SignTemplates`.

Returns a collection of templates.


## Get Box Sign template by ID

Fetches details of a specific Box Sign template.

This operation is performed by calling function `getSignTemplateById`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/get-sign-templates-id/).

<!-- sample get_sign_templates_id -->
```
client.getSignTemplates().getSignTemplateById(signTemplates.getEntries().get(0).getId())
```

### Arguments

- templateId `String`
  - The ID of a Box Sign template. Example: "123075213-7d117509-8f05-42e4-a5ef-5190a319d41d"
- headers `GetSignTemplateByIdHeaders`
  - Headers of getSignTemplateById method


### Returns

This function returns a value of type `SignTemplate`.

Returns details of a template.


