package com.box.sdkgen.managers.shieldinformationbarrierreports;

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
import com.box.sdkgen.schemas.shieldinformationbarrierreference.ShieldInformationBarrierReference;
import com.box.sdkgen.schemas.shieldinformationbarrierreport.ShieldInformationBarrierReport;
import com.box.sdkgen.schemas.shieldinformationbarrierreports.ShieldInformationBarrierReports;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ShieldInformationBarrierReportsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ShieldInformationBarrierReportsManager() {
    this.networkSession = new NetworkSession();
  }

  protected ShieldInformationBarrierReportsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Lists shield information barrier reports.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierReports method
   */
  public ShieldInformationBarrierReports getShieldInformationBarrierReports(
      GetShieldInformationBarrierReportsQueryParams queryParams) {
    return getShieldInformationBarrierReports(
        queryParams, new GetShieldInformationBarrierReportsHeaders());
  }

  /**
   * Lists shield information barrier reports.
   *
   * @param queryParams Query parameters of getShieldInformationBarrierReports method
   * @param headers Headers of getShieldInformationBarrierReports method
   */
  public ShieldInformationBarrierReports getShieldInformationBarrierReports(
      GetShieldInformationBarrierReportsQueryParams queryParams,
      GetShieldInformationBarrierReportsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf(
                    "shield_information_barrier_id",
                    convertToString(queryParams.getShieldInformationBarrierId())),
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
                            "/2.0/shield_information_barrier_reports"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierReports.class);
  }

  /**
   * Creates a shield information barrier report for a given barrier.
   *
   * @param requestBody Request body of createShieldInformationBarrierReport method
   */
  public ShieldInformationBarrierReport createShieldInformationBarrierReport(
      ShieldInformationBarrierReference requestBody) {
    return createShieldInformationBarrierReport(
        requestBody, new CreateShieldInformationBarrierReportHeaders());
  }

  /**
   * Creates a shield information barrier report for a given barrier.
   *
   * @param requestBody Request body of createShieldInformationBarrierReport method
   * @param headers Headers of createShieldInformationBarrierReport method
   */
  public ShieldInformationBarrierReport createShieldInformationBarrierReport(
      ShieldInformationBarrierReference requestBody,
      CreateShieldInformationBarrierReportHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_reports"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierReport.class);
  }

  /**
   * Retrieves a shield information barrier report by its ID.
   *
   * @param shieldInformationBarrierReportId The ID of the shield information barrier Report.
   *     Example: "3423"
   */
  public ShieldInformationBarrierReport getShieldInformationBarrierReportById(
      String shieldInformationBarrierReportId) {
    return getShieldInformationBarrierReportById(
        shieldInformationBarrierReportId, new GetShieldInformationBarrierReportByIdHeaders());
  }

  /**
   * Retrieves a shield information barrier report by its ID.
   *
   * @param shieldInformationBarrierReportId The ID of the shield information barrier Report.
   *     Example: "3423"
   * @param headers Headers of getShieldInformationBarrierReportById method
   */
  public ShieldInformationBarrierReport getShieldInformationBarrierReportById(
      String shieldInformationBarrierReportId,
      GetShieldInformationBarrierReportByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/shield_information_barrier_reports/",
                            convertToString(shieldInformationBarrierReportId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), ShieldInformationBarrierReport.class);
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public ShieldInformationBarrierReportsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new ShieldInformationBarrierReportsManager(this);
    }
  }
}
