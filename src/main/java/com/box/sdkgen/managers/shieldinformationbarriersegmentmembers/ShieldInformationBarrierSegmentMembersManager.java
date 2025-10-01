package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmember.ShieldInformationBarrierSegmentMember;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmembers.ShieldInformationBarrierSegmentMembers;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldInformationBarrierSegmentMembersManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldInformationBarrierSegmentMembersManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldInformationBarrierSegmentMembersManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a shield information barrier segment member by its ID.
   *
   * @param shieldInformationBarrierSegmentMemberId The ID of the shield information barrier segment
   *     Member. Example: "7815"
   */
  public ShieldInformationBarrierSegmentMember getShieldInformationBarrierSegmentMemberById(
      String shieldInformationBarrierSegmentMemberId) {
    return getShieldInformationBarrierSegmentMemberById(
        shieldInformationBarrierSegmentMemberId,
        new GetShieldInformationBarrierSegmentMemberByIdHeaders());
  }

  /**
   * Retrieves a shield information barrier segment member by its ID.
   *
   * @param shieldInformationBarrierSegmentMemberId The ID of the shield information barrier segment
   *     Member. Example: "7815"
   * @param headers Headers of getShieldInformationBarrierSegmentMemberById method
   */
  public ShieldInformationBarrierSegmentMember getShieldInformationBarrierSegmentMemberById(
      String shieldInformationBarrierSegmentMemberId,
      GetShieldInformationBarrierSegmentMemberByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_members/",
                            convertToString(shieldInformationBarrierSegmentMemberId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegmentMember.class);
  }

  /**
   * Deletes a shield information barrier segment member based on provided ID.
   *
   * @param shieldInformationBarrierSegmentMemberId The ID of the shield information barrier segment
   *     Member. Example: "7815"
   */
  public void deleteShieldInformationBarrierSegmentMemberById(
      String shieldInformationBarrierSegmentMemberId) {
    deleteShieldInformationBarrierSegmentMemberById(
        shieldInformationBarrierSegmentMemberId,
        new DeleteShieldInformationBarrierSegmentMemberByIdHeaders());
  }

  /**
   * Deletes a shield information barrier segment member based on provided ID.
   *
   * @param shieldInformationBarrierSegmentMemberId The ID of the shield information barrier segment
   *     Member. Example: "7815"
   * @param headers Headers of deleteShieldInformationBarrierSegmentMemberById method
   */
  public void deleteShieldInformationBarrierSegmentMemberById(
      String shieldInformationBarrierSegmentMemberId,
      DeleteShieldInformationBarrierSegmentMemberByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_members/",
                            convertToString(shieldInformationBarrierSegmentMemberId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Lists shield information barrier segment members based on provided segment IDs.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierSegmentMembers method
   */
  public ShieldInformationBarrierSegmentMembers getShieldInformationBarrierSegmentMembers(
      GetShieldInformationBarrierSegmentMembersQueryParams queryParams) {
    return getShieldInformationBarrierSegmentMembers(
        queryParams, new GetShieldInformationBarrierSegmentMembersHeaders());
  }

  /**
   * Lists shield information barrier segment members based on provided segment IDs.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierSegmentMembers method
   * @param headers Headers of getShieldInformationBarrierSegmentMembers method
   */
  public ShieldInformationBarrierSegmentMembers getShieldInformationBarrierSegmentMembers(
      GetShieldInformationBarrierSegmentMembersQueryParams queryParams,
      GetShieldInformationBarrierSegmentMembersHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "shield_information_barrier_segment_id",
                    convertToString(queryParams.getShieldInformationBarrierSegmentId())),
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_members"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(
        response.getData(), ShieldInformationBarrierSegmentMembers.class);
  }

  /**
   * Creates a new shield information barrier segment member.
   *
   * @param requestBody Request body of createShieldInformationBarrierSegmentMember method
   */
  public ShieldInformationBarrierSegmentMember createShieldInformationBarrierSegmentMember(
      CreateShieldInformationBarrierSegmentMemberRequestBody requestBody) {
    return createShieldInformationBarrierSegmentMember(
        requestBody, new CreateShieldInformationBarrierSegmentMemberHeaders());
  }

  /**
   * Creates a new shield information barrier segment member.
   *
   * @param requestBody Request body of createShieldInformationBarrierSegmentMember method
   * @param headers Headers of createShieldInformationBarrierSegmentMember method
   */
  public ShieldInformationBarrierSegmentMember createShieldInformationBarrierSegmentMember(
      CreateShieldInformationBarrierSegmentMemberRequestBody requestBody,
      CreateShieldInformationBarrierSegmentMemberHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_segment_members"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierSegmentMember.class);
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public ShieldInformationBarrierSegmentMembersManager build() {
      return new ShieldInformationBarrierSegmentMembersManager(this);
    }
  }
}
