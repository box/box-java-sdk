package com.box.sdkgen.networking.fetchoptions;

import com.fasterxml.jackson.databind.JsonNode;

public interface RequestDataFactory {

  /** Creates a new request body */
  JsonNode create();
}
