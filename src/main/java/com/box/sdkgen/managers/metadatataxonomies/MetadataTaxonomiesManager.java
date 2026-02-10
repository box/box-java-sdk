package com.box.sdkgen.managers.metadatataxonomies;

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
import com.box.sdkgen.schemas.metadatataxonomies.MetadataTaxonomies;
import com.box.sdkgen.schemas.metadatataxonomy.MetadataTaxonomy;
import com.box.sdkgen.schemas.metadatataxonomylevel.MetadataTaxonomyLevel;
import com.box.sdkgen.schemas.metadatataxonomylevels.MetadataTaxonomyLevels;
import com.box.sdkgen.schemas.metadatataxonomynode.MetadataTaxonomyNode;
import com.box.sdkgen.schemas.metadatataxonomynodes.MetadataTaxonomyNodes;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class MetadataTaxonomiesManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public MetadataTaxonomiesManager() {
    this.networkSession = new NetworkSession();
  }

  protected MetadataTaxonomiesManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Creates a new metadata taxonomy that can be used in metadata templates.
   *
   * @param requestBody Request body of createMetadataTaxonomy method
   */
  public MetadataTaxonomy createMetadataTaxonomy(CreateMetadataTaxonomyRequestBody requestBody) {
    return createMetadataTaxonomy(requestBody, new CreateMetadataTaxonomyHeaders());
  }

  /**
   * Creates a new metadata taxonomy that can be used in metadata templates.
   *
   * @param requestBody Request body of createMetadataTaxonomy method
   * @param headers Headers of createMetadataTaxonomy method
   */
  public MetadataTaxonomy createMetadataTaxonomy(
      CreateMetadataTaxonomyRequestBody requestBody, CreateMetadataTaxonomyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomy.class);
  }

  /**
   * Used to retrieve all metadata taxonomies in a namespace.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   */
  public MetadataTaxonomies getMetadataTaxonomies(String namespace) {
    return getMetadataTaxonomies(
        namespace, new GetMetadataTaxonomiesQueryParams(), new GetMetadataTaxonomiesHeaders());
  }

  /**
   * Used to retrieve all metadata taxonomies in a namespace.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param queryParams Query parameters of getMetadataTaxonomies method
   */
  public MetadataTaxonomies getMetadataTaxonomies(
      String namespace, GetMetadataTaxonomiesQueryParams queryParams) {
    return getMetadataTaxonomies(namespace, queryParams, new GetMetadataTaxonomiesHeaders());
  }

  /**
   * Used to retrieve all metadata taxonomies in a namespace.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param headers Headers of getMetadataTaxonomies method
   */
  public MetadataTaxonomies getMetadataTaxonomies(
      String namespace, GetMetadataTaxonomiesHeaders headers) {
    return getMetadataTaxonomies(namespace, new GetMetadataTaxonomiesQueryParams(), headers);
  }

  /**
   * Used to retrieve all metadata taxonomies in a namespace.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param queryParams Query parameters of getMetadataTaxonomies method
   * @param headers Headers of getMetadataTaxonomies method
   */
  public MetadataTaxonomies getMetadataTaxonomies(
      String namespace,
      GetMetadataTaxonomiesQueryParams queryParams,
      GetMetadataTaxonomiesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
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
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomies.class);
  }

  /**
   * Used to retrieve a metadata taxonomy by taxonomy key.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   */
  public MetadataTaxonomy getMetadataTaxonomyByKey(String namespace, String taxonomyKey) {
    return getMetadataTaxonomyByKey(namespace, taxonomyKey, new GetMetadataTaxonomyByKeyHeaders());
  }

  /**
   * Used to retrieve a metadata taxonomy by taxonomy key.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param headers Headers of getMetadataTaxonomyByKey method
   */
  public MetadataTaxonomy getMetadataTaxonomyByKey(
      String namespace, String taxonomyKey, GetMetadataTaxonomyByKeyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomy.class);
  }

  /**
   * Updates an existing metadata taxonomy.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of updateMetadataTaxonomy method
   */
  public MetadataTaxonomy updateMetadataTaxonomy(
      String namespace, String taxonomyKey, UpdateMetadataTaxonomyRequestBody requestBody) {
    return updateMetadataTaxonomy(
        namespace, taxonomyKey, requestBody, new UpdateMetadataTaxonomyHeaders());
  }

  /**
   * Updates an existing metadata taxonomy.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of updateMetadataTaxonomy method
   * @param headers Headers of updateMetadataTaxonomy method
   */
  public MetadataTaxonomy updateMetadataTaxonomy(
      String namespace,
      String taxonomyKey,
      UpdateMetadataTaxonomyRequestBody requestBody,
      UpdateMetadataTaxonomyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey)),
                        "PATCH")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomy.class);
  }

  /**
   * Delete a metadata taxonomy. This deletion is permanent and cannot be reverted.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   */
  public void deleteMetadataTaxonomy(String namespace, String taxonomyKey) {
    deleteMetadataTaxonomy(namespace, taxonomyKey, new DeleteMetadataTaxonomyHeaders());
  }

  /**
   * Delete a metadata taxonomy. This deletion is permanent and cannot be reverted.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param headers Headers of deleteMetadataTaxonomy method
   */
  public void deleteMetadataTaxonomy(
      String namespace, String taxonomyKey, DeleteMetadataTaxonomyHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Creates new metadata taxonomy levels.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of createMetadataTaxonomyLevel method
   */
  public MetadataTaxonomyLevels createMetadataTaxonomyLevel(
      String namespace, String taxonomyKey, List<MetadataTaxonomyLevel> requestBody) {
    return createMetadataTaxonomyLevel(
        namespace, taxonomyKey, requestBody, new CreateMetadataTaxonomyLevelHeaders());
  }

  /**
   * Creates new metadata taxonomy levels.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of createMetadataTaxonomyLevel method
   * @param headers Headers of createMetadataTaxonomyLevel method
   */
  public MetadataTaxonomyLevels createMetadataTaxonomyLevel(
      String namespace,
      String taxonomyKey,
      List<MetadataTaxonomyLevel> requestBody,
      CreateMetadataTaxonomyLevelHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/levels"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyLevels.class);
  }

  /**
   * Updates an existing metadata taxonomy level.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param levelIndex The index of the metadata taxonomy level. Example: 1
   * @param requestBody Request body of updateMetadataTaxonomyLevelById method
   */
  public MetadataTaxonomyLevel updateMetadataTaxonomyLevelById(
      String namespace,
      String taxonomyKey,
      long levelIndex,
      UpdateMetadataTaxonomyLevelByIdRequestBody requestBody) {
    return updateMetadataTaxonomyLevelById(
        namespace,
        taxonomyKey,
        levelIndex,
        requestBody,
        new UpdateMetadataTaxonomyLevelByIdHeaders());
  }

  /**
   * Updates an existing metadata taxonomy level.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param levelIndex The index of the metadata taxonomy level. Example: 1
   * @param requestBody Request body of updateMetadataTaxonomyLevelById method
   * @param headers Headers of updateMetadataTaxonomyLevelById method
   */
  public MetadataTaxonomyLevel updateMetadataTaxonomyLevelById(
      String namespace,
      String taxonomyKey,
      long levelIndex,
      UpdateMetadataTaxonomyLevelByIdRequestBody requestBody,
      UpdateMetadataTaxonomyLevelByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/levels/",
                            convertToString(levelIndex)),
                        "PATCH")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyLevel.class);
  }

  /**
   * Creates a new metadata taxonomy level and appends it to the existing levels. If there are no
   * levels defined yet, this will create the first level.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of addMetadataTaxonomyLevel method
   */
  public MetadataTaxonomyLevels addMetadataTaxonomyLevel(
      String namespace, String taxonomyKey, AddMetadataTaxonomyLevelRequestBody requestBody) {
    return addMetadataTaxonomyLevel(
        namespace, taxonomyKey, requestBody, new AddMetadataTaxonomyLevelHeaders());
  }

  /**
   * Creates a new metadata taxonomy level and appends it to the existing levels. If there are no
   * levels defined yet, this will create the first level.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of addMetadataTaxonomyLevel method
   * @param headers Headers of addMetadataTaxonomyLevel method
   */
  public MetadataTaxonomyLevels addMetadataTaxonomyLevel(
      String namespace,
      String taxonomyKey,
      AddMetadataTaxonomyLevelRequestBody requestBody,
      AddMetadataTaxonomyLevelHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/levels:append"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyLevels.class);
  }

  /**
   * Deletes the last level of the metadata taxonomy.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   */
  public MetadataTaxonomyLevels deleteMetadataTaxonomyLevel(String namespace, String taxonomyKey) {
    return deleteMetadataTaxonomyLevel(
        namespace, taxonomyKey, new DeleteMetadataTaxonomyLevelHeaders());
  }

  /**
   * Deletes the last level of the metadata taxonomy.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param headers Headers of deleteMetadataTaxonomyLevel method
   */
  public MetadataTaxonomyLevels deleteMetadataTaxonomyLevel(
      String namespace, String taxonomyKey, DeleteMetadataTaxonomyLevelHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/levels:trim"),
                        "POST")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyLevels.class);
  }

  /**
   * Used to retrieve metadata taxonomy nodes based on the parameters specified. Results are sorted
   * in lexicographic order unless a `query` parameter is passed. With a `query` parameter
   * specified, results are sorted in order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   */
  public MetadataTaxonomyNodes getMetadataTaxonomyNodes(String namespace, String taxonomyKey) {
    return getMetadataTaxonomyNodes(
        namespace,
        taxonomyKey,
        new GetMetadataTaxonomyNodesQueryParams(),
        new GetMetadataTaxonomyNodesHeaders());
  }

  /**
   * Used to retrieve metadata taxonomy nodes based on the parameters specified. Results are sorted
   * in lexicographic order unless a `query` parameter is passed. With a `query` parameter
   * specified, results are sorted in order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param queryParams Query parameters of getMetadataTaxonomyNodes method
   */
  public MetadataTaxonomyNodes getMetadataTaxonomyNodes(
      String namespace, String taxonomyKey, GetMetadataTaxonomyNodesQueryParams queryParams) {
    return getMetadataTaxonomyNodes(
        namespace, taxonomyKey, queryParams, new GetMetadataTaxonomyNodesHeaders());
  }

  /**
   * Used to retrieve metadata taxonomy nodes based on the parameters specified. Results are sorted
   * in lexicographic order unless a `query` parameter is passed. With a `query` parameter
   * specified, results are sorted in order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param headers Headers of getMetadataTaxonomyNodes method
   */
  public MetadataTaxonomyNodes getMetadataTaxonomyNodes(
      String namespace, String taxonomyKey, GetMetadataTaxonomyNodesHeaders headers) {
    return getMetadataTaxonomyNodes(
        namespace, taxonomyKey, new GetMetadataTaxonomyNodesQueryParams(), headers);
  }

  /**
   * Used to retrieve metadata taxonomy nodes based on the parameters specified. Results are sorted
   * in lexicographic order unless a `query` parameter is passed. With a `query` parameter
   * specified, results are sorted in order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param queryParams Query parameters of getMetadataTaxonomyNodes method
   * @param headers Headers of getMetadataTaxonomyNodes method
   */
  public MetadataTaxonomyNodes getMetadataTaxonomyNodes(
      String namespace,
      String taxonomyKey,
      GetMetadataTaxonomyNodesQueryParams queryParams,
      GetMetadataTaxonomyNodesHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("level", convertToString(queryParams.getLevel())),
                entryOf("parent", convertToString(queryParams.getParent())),
                entryOf("ancestor", convertToString(queryParams.getAncestor())),
                entryOf("query", convertToString(queryParams.getQuery())),
                entryOf(
                    "include-total-result-count",
                    convertToString(queryParams.getIncludeTotalResultCount())),
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
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/nodes"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyNodes.class);
  }

  /**
   * Creates a new metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of createMetadataTaxonomyNode method
   */
  public MetadataTaxonomyNode createMetadataTaxonomyNode(
      String namespace, String taxonomyKey, CreateMetadataTaxonomyNodeRequestBody requestBody) {
    return createMetadataTaxonomyNode(
        namespace, taxonomyKey, requestBody, new CreateMetadataTaxonomyNodeHeaders());
  }

  /**
   * Creates a new metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param requestBody Request body of createMetadataTaxonomyNode method
   * @param headers Headers of createMetadataTaxonomyNode method
   */
  public MetadataTaxonomyNode createMetadataTaxonomyNode(
      String namespace,
      String taxonomyKey,
      CreateMetadataTaxonomyNodeRequestBody requestBody,
      CreateMetadataTaxonomyNodeHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/nodes"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyNode.class);
  }

  /**
   * Retrieves a metadata taxonomy node by its identifier.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   */
  public MetadataTaxonomyNode getMetadataTaxonomyNodeById(
      String namespace, String taxonomyKey, String nodeId) {
    return getMetadataTaxonomyNodeById(
        namespace, taxonomyKey, nodeId, new GetMetadataTaxonomyNodeByIdHeaders());
  }

  /**
   * Retrieves a metadata taxonomy node by its identifier.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   * @param headers Headers of getMetadataTaxonomyNodeById method
   */
  public MetadataTaxonomyNode getMetadataTaxonomyNodeById(
      String namespace,
      String taxonomyKey,
      String nodeId,
      GetMetadataTaxonomyNodeByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/nodes/",
                            convertToString(nodeId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyNode.class);
  }

  /**
   * Updates an existing metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   */
  public MetadataTaxonomyNode updateMetadataTaxonomyNode(
      String namespace, String taxonomyKey, String nodeId) {
    return updateMetadataTaxonomyNode(
        namespace,
        taxonomyKey,
        nodeId,
        new UpdateMetadataTaxonomyNodeRequestBody(),
        new UpdateMetadataTaxonomyNodeHeaders());
  }

  /**
   * Updates an existing metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   * @param requestBody Request body of updateMetadataTaxonomyNode method
   */
  public MetadataTaxonomyNode updateMetadataTaxonomyNode(
      String namespace,
      String taxonomyKey,
      String nodeId,
      UpdateMetadataTaxonomyNodeRequestBody requestBody) {
    return updateMetadataTaxonomyNode(
        namespace, taxonomyKey, nodeId, requestBody, new UpdateMetadataTaxonomyNodeHeaders());
  }

  /**
   * Updates an existing metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   * @param headers Headers of updateMetadataTaxonomyNode method
   */
  public MetadataTaxonomyNode updateMetadataTaxonomyNode(
      String namespace,
      String taxonomyKey,
      String nodeId,
      UpdateMetadataTaxonomyNodeHeaders headers) {
    return updateMetadataTaxonomyNode(
        namespace, taxonomyKey, nodeId, new UpdateMetadataTaxonomyNodeRequestBody(), headers);
  }

  /**
   * Updates an existing metadata taxonomy node.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   * @param requestBody Request body of updateMetadataTaxonomyNode method
   * @param headers Headers of updateMetadataTaxonomyNode method
   */
  public MetadataTaxonomyNode updateMetadataTaxonomyNode(
      String namespace,
      String taxonomyKey,
      String nodeId,
      UpdateMetadataTaxonomyNodeRequestBody requestBody,
      UpdateMetadataTaxonomyNodeHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/nodes/",
                            convertToString(nodeId)),
                        "PATCH")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyNode.class);
  }

  /**
   * Delete a metadata taxonomy node. This deletion is permanent and cannot be reverted. Only
   * metadata taxonomy nodes without any children can be deleted.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   */
  public void deleteMetadataTaxonomyNode(String namespace, String taxonomyKey, String nodeId) {
    deleteMetadataTaxonomyNode(
        namespace, taxonomyKey, nodeId, new DeleteMetadataTaxonomyNodeHeaders());
  }

  /**
   * Delete a metadata taxonomy node. This deletion is permanent and cannot be reverted. Only
   * metadata taxonomy nodes without any children can be deleted.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param taxonomyKey The key of the metadata taxonomy. Example: "geography"
   * @param nodeId The identifier of the metadata taxonomy node. Example:
   *     "14d3d433-c77f-49c5-b146-9dea370f6e32"
   * @param headers Headers of deleteMetadataTaxonomyNode method
   */
  public void deleteMetadataTaxonomyNode(
      String namespace,
      String taxonomyKey,
      String nodeId,
      DeleteMetadataTaxonomyNodeHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/metadata_taxonomies/",
                            convertToString(namespace),
                            "/",
                            convertToString(taxonomyKey),
                            "/nodes/",
                            convertToString(nodeId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Used to retrieve metadata taxonomy nodes which are available for the taxonomy field based on
   * its configuration and the parameters specified. Results are sorted in lexicographic order
   * unless a `query` parameter is passed. With a `query` parameter specified, results are sorted in
   * order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param fieldKey The key of the metadata taxonomy field in the template. Example: "geography"
   */
  public MetadataTaxonomyNodes getMetadataTemplateFieldOptions(
      String namespace, String templateKey, String fieldKey) {
    return getMetadataTemplateFieldOptions(
        namespace,
        templateKey,
        fieldKey,
        new GetMetadataTemplateFieldOptionsQueryParams(),
        new GetMetadataTemplateFieldOptionsHeaders());
  }

  /**
   * Used to retrieve metadata taxonomy nodes which are available for the taxonomy field based on
   * its configuration and the parameters specified. Results are sorted in lexicographic order
   * unless a `query` parameter is passed. With a `query` parameter specified, results are sorted in
   * order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param fieldKey The key of the metadata taxonomy field in the template. Example: "geography"
   * @param queryParams Query parameters of getMetadataTemplateFieldOptions method
   */
  public MetadataTaxonomyNodes getMetadataTemplateFieldOptions(
      String namespace,
      String templateKey,
      String fieldKey,
      GetMetadataTemplateFieldOptionsQueryParams queryParams) {
    return getMetadataTemplateFieldOptions(
        namespace,
        templateKey,
        fieldKey,
        queryParams,
        new GetMetadataTemplateFieldOptionsHeaders());
  }

  /**
   * Used to retrieve metadata taxonomy nodes which are available for the taxonomy field based on
   * its configuration and the parameters specified. Results are sorted in lexicographic order
   * unless a `query` parameter is passed. With a `query` parameter specified, results are sorted in
   * order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param fieldKey The key of the metadata taxonomy field in the template. Example: "geography"
   * @param headers Headers of getMetadataTemplateFieldOptions method
   */
  public MetadataTaxonomyNodes getMetadataTemplateFieldOptions(
      String namespace,
      String templateKey,
      String fieldKey,
      GetMetadataTemplateFieldOptionsHeaders headers) {
    return getMetadataTemplateFieldOptions(
        namespace,
        templateKey,
        fieldKey,
        new GetMetadataTemplateFieldOptionsQueryParams(),
        headers);
  }

  /**
   * Used to retrieve metadata taxonomy nodes which are available for the taxonomy field based on
   * its configuration and the parameters specified. Results are sorted in lexicographic order
   * unless a `query` parameter is passed. With a `query` parameter specified, results are sorted in
   * order of relevance.
   *
   * @param namespace The namespace of the metadata taxonomy. Example: "enterprise_123456"
   * @param templateKey The name of the metadata template. Example: "properties"
   * @param fieldKey The key of the metadata taxonomy field in the template. Example: "geography"
   * @param queryParams Query parameters of getMetadataTemplateFieldOptions method
   * @param headers Headers of getMetadataTemplateFieldOptions method
   */
  public MetadataTaxonomyNodes getMetadataTemplateFieldOptions(
      String namespace,
      String templateKey,
      String fieldKey,
      GetMetadataTemplateFieldOptionsQueryParams queryParams,
      GetMetadataTemplateFieldOptionsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("level", convertToString(queryParams.getLevel())),
                entryOf("parent", convertToString(queryParams.getParent())),
                entryOf("ancestor", convertToString(queryParams.getAncestor())),
                entryOf("query", convertToString(queryParams.getQuery())),
                entryOf(
                    "include-total-result-count",
                    convertToString(queryParams.getIncludeTotalResultCount())),
                entryOf(
                    "only-selectable-options",
                    convertToString(queryParams.getOnlySelectableOptions())),
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
                            "/2.0/metadata_templates/",
                            convertToString(namespace),
                            "/",
                            convertToString(templateKey),
                            "/fields/",
                            convertToString(fieldKey),
                            "/options"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), MetadataTaxonomyNodes.class);
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

    public MetadataTaxonomiesManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new MetadataTaxonomiesManager(this);
    }
  }
}
