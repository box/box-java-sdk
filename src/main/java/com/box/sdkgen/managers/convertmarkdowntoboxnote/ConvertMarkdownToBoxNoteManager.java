package com.box.sdkgen.managers.convertmarkdowntoboxnote;

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
import com.box.sdkgen.schemas.v2026r0.notesconvertrequestbodyv2026r0.NotesConvertRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.notesconvertresponsev2026r0.NotesConvertResponseV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class ConvertMarkdownToBoxNoteManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public ConvertMarkdownToBoxNoteManager() {
    this.networkSession = new NetworkSession();
  }

  protected ConvertMarkdownToBoxNoteManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Creates a Box Note (`.boxnote` file) from supported source content. See the `content_format`
   * field for supported formats.
   *
   * @param requestBody Request body of createNoteConvertV2026R0 method
   */
  public NotesConvertResponseV2026R0 createNoteConvertV2026R0(
      NotesConvertRequestBodyV2026R0 requestBody) {
    return createNoteConvertV2026R0(requestBody, new CreateNoteConvertV2026R0Headers());
  }

  /**
   * Creates a Box Note (`.boxnote` file) from supported source content. See the `content_format`
   * field for supported formats.
   *
   * @param requestBody Request body of createNoteConvertV2026R0 method
   * @param headers Headers of createNoteConvertV2026R0 method
   */
  public NotesConvertResponseV2026R0 createNoteConvertV2026R0(
      NotesConvertRequestBodyV2026R0 requestBody, CreateNoteConvertV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/notes/convert"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), NotesConvertResponseV2026R0.class);
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

    public ConvertMarkdownToBoxNoteManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new ConvertMarkdownToBoxNoteManager(this);
    }
  }
}
