package com.box.sdkgen.networking.networkclient;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;

public interface NetworkClient {

  FetchResponse fetch(FetchOptions options);
}
