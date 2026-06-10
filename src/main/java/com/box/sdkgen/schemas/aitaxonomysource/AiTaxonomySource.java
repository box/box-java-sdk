package com.box.sdkgen.schemas.aitaxonomysource;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aitaxonomyfilereference.AiTaxonomyFileReference;
import com.box.sdkgen.schemas.aitaxonomyreference.AiTaxonomyReference;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = AiTaxonomySource.AiTaxonomySourceDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiTaxonomySource extends OneOfTwo<AiTaxonomyReference, AiTaxonomyFileReference> {

  protected final String type;

  protected final String taxonomyKey;

  public AiTaxonomySource(AiTaxonomyReference aiTaxonomyReference) {
    super(aiTaxonomyReference, null);
    this.type = EnumWrapper.convertToString(aiTaxonomyReference.getType());
    this.taxonomyKey = aiTaxonomyReference.getTaxonomyKey();
  }

  public AiTaxonomySource(AiTaxonomyFileReference aiTaxonomyFileReference) {
    super(null, aiTaxonomyFileReference);
    this.type = EnumWrapper.convertToString(aiTaxonomyFileReference.getType());
    this.taxonomyKey = aiTaxonomyFileReference.getTaxonomyKey();
  }

  public boolean isAiTaxonomyReference() {
    return value0 != null;
  }

  public AiTaxonomyReference getAiTaxonomyReference() {
    return value0;
  }

  public boolean isAiTaxonomyFileReference() {
    return value1 != null;
  }

  public AiTaxonomyFileReference getAiTaxonomyFileReference() {
    return value1;
  }

  public String getType() {
    return type;
  }

  public String getTaxonomyKey() {
    return taxonomyKey;
  }

  static class AiTaxonomySourceDeserializer extends JsonDeserializer<AiTaxonomySource> {

    public AiTaxonomySourceDeserializer() {
      super();
    }

    @Override
    public AiTaxonomySource deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "taxonomy":
            return new AiTaxonomySource(JsonManager.deserialize(node, AiTaxonomyReference.class));
          case "file":
            return new AiTaxonomySource(
                JsonManager.deserialize(node, AiTaxonomyFileReference.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiTaxonomySource");
    }
  }
}
