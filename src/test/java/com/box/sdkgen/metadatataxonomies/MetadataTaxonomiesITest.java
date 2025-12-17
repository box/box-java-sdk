package com.box.sdkgen.metadatataxonomies;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.delayInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.metadatataxonomies.AddMetadataTaxonomyLevelRequestBody;
import com.box.sdkgen.managers.metadatataxonomies.CreateMetadataTaxonomyNodeRequestBody;
import com.box.sdkgen.managers.metadatataxonomies.CreateMetadataTaxonomyRequestBody;
import com.box.sdkgen.managers.metadatataxonomies.UpdateMetadataTaxonomyNodeRequestBody;
import com.box.sdkgen.managers.metadatataxonomies.UpdateMetadataTaxonomyRequestBody;
import com.box.sdkgen.schemas.metadatataxonomies.MetadataTaxonomies;
import com.box.sdkgen.schemas.metadatataxonomy.MetadataTaxonomy;
import com.box.sdkgen.schemas.metadatataxonomylevel.MetadataTaxonomyLevel;
import com.box.sdkgen.schemas.metadatataxonomylevels.MetadataTaxonomyLevels;
import com.box.sdkgen.schemas.metadatataxonomynode.MetadataTaxonomyNode;
import com.box.sdkgen.schemas.metadatataxonomynodes.MetadataTaxonomyNodes;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MetadataTaxonomiesITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testMetadataTaxonomiesCrud() {
    String namespace = String.join("", "enterprise_", getEnvVar("ENTERPRISE_ID"));
    String uuid = getUuid();
    String taxonomyKey = String.join("", "geography", uuid);
    String displayName = String.join("", "Geography Taxonomy", uuid);
    MetadataTaxonomy createdTaxonomy =
        client
            .getMetadataTaxonomies()
            .createMetadataTaxonomy(
                new CreateMetadataTaxonomyRequestBody.Builder(displayName, namespace)
                    .key(taxonomyKey)
                    .build());
    assert createdTaxonomy.getDisplayName().equals(displayName);
    assert createdTaxonomy.getNamespace().equals(namespace);
    MetadataTaxonomies taxonomies = client.getMetadataTaxonomies().getMetadataTaxonomies(namespace);
    assert taxonomies.getEntries().size() > 0;
    assert taxonomies.getEntries().get(0).getNamespace().equals(namespace);
    String updatedDisplayName = String.join("", "Geography Taxonomy UPDATED", uuid);
    MetadataTaxonomy updatedTaxonomy =
        client
            .getMetadataTaxonomies()
            .updateMetadataTaxonomy(
                namespace, taxonomyKey, new UpdateMetadataTaxonomyRequestBody(updatedDisplayName));
    assert updatedTaxonomy.getDisplayName().equals(updatedDisplayName);
    assert updatedTaxonomy.getNamespace().equals(namespace);
    assert updatedTaxonomy.getId().equals(createdTaxonomy.getId());
    MetadataTaxonomy getTaxonomy =
        client.getMetadataTaxonomies().getMetadataTaxonomyByKey(namespace, taxonomyKey);
    assert getTaxonomy.getDisplayName().equals(updatedDisplayName);
    assert getTaxonomy.getNamespace().equals(namespace);
    assert getTaxonomy.getId().equals(createdTaxonomy.getId());
    client.getMetadataTaxonomies().deleteMetadataTaxonomy(namespace, taxonomyKey);
    assertThrows(
        RuntimeException.class,
        () -> client.getMetadataTaxonomies().getMetadataTaxonomyByKey(namespace, taxonomyKey));
  }

  @Test
  public void testMetadataTaxonomiesNodes() {
    String namespace = String.join("", "enterprise_", getEnvVar("ENTERPRISE_ID"));
    String uuid = getUuid();
    String taxonomyKey = String.join("", "geography", uuid);
    String displayName = String.join("", "Geography Taxonomy", uuid);
    MetadataTaxonomy createdTaxonomy =
        client
            .getMetadataTaxonomies()
            .createMetadataTaxonomy(
                new CreateMetadataTaxonomyRequestBody.Builder(displayName, namespace)
                    .key(taxonomyKey)
                    .build());
    assert createdTaxonomy.getDisplayName().equals(displayName);
    assert createdTaxonomy.getNamespace().equals(namespace);
    MetadataTaxonomyLevels taxonomyLevels =
        client
            .getMetadataTaxonomies()
            .createMetadataTaxonomyLevel(
                namespace,
                taxonomyKey,
                Arrays.asList(
                    new MetadataTaxonomyLevel.Builder()
                        .displayName("Continent")
                        .description("Continent Level")
                        .build(),
                    new MetadataTaxonomyLevel.Builder()
                        .displayName("Country")
                        .description("Country Level")
                        .build()));
    assert taxonomyLevels.getEntries().size() == 2;
    assert taxonomyLevels.getEntries().get(0).getDisplayName().equals("Continent");
    assert taxonomyLevels.getEntries().get(1).getDisplayName().equals("Country");
    MetadataTaxonomyLevels taxonomyLevelsAfterAddition =
        client
            .getMetadataTaxonomies()
            .addMetadataTaxonomyLevel(
                namespace,
                taxonomyKey,
                new AddMetadataTaxonomyLevelRequestBody.Builder("Region")
                    .description("Region Description")
                    .build());
    assert taxonomyLevelsAfterAddition.getEntries().size() == 3;
    assert taxonomyLevelsAfterAddition.getEntries().get(2).getDisplayName().equals("Region");
    MetadataTaxonomyLevels taxonomyLevelsAfterDeletion =
        client.getMetadataTaxonomies().deleteMetadataTaxonomyLevel(namespace, taxonomyKey);
    assert taxonomyLevelsAfterDeletion.getEntries().size() == 2;
    assert taxonomyLevelsAfterDeletion.getEntries().get(0).getDisplayName().equals("Continent");
    assert taxonomyLevelsAfterDeletion.getEntries().get(1).getDisplayName().equals("Country");
    MetadataTaxonomyNode continentNode =
        client
            .getMetadataTaxonomies()
            .createMetadataTaxonomyNode(
                namespace, taxonomyKey, new CreateMetadataTaxonomyNodeRequestBody("Europe", 1));
    assert continentNode.getDisplayName().equals("Europe");
    assert continentNode.getLevel() == 1;
    MetadataTaxonomyNode countryNode =
        client
            .getMetadataTaxonomies()
            .createMetadataTaxonomyNode(
                namespace,
                taxonomyKey,
                new CreateMetadataTaxonomyNodeRequestBody.Builder("Poland", 2)
                    .parentId(continentNode.getId())
                    .build());
    assert countryNode.getDisplayName().equals("Poland");
    assert countryNode.getLevel() == 2;
    assert countryNode.getParentId().equals(continentNode.getId());
    delayInSeconds(5);
    MetadataTaxonomyNodes allNodes =
        client.getMetadataTaxonomies().getMetadataTaxonomyNodes(namespace, taxonomyKey);
    assert allNodes.getEntries().size() == 2;
    MetadataTaxonomyNode updatedCountryNode =
        client
            .getMetadataTaxonomies()
            .updateMetadataTaxonomyNode(
                namespace,
                taxonomyKey,
                countryNode.getId(),
                new UpdateMetadataTaxonomyNodeRequestBody.Builder()
                    .displayName("Poland UPDATED")
                    .build());
    assert updatedCountryNode.getDisplayName().equals("Poland UPDATED");
    assert updatedCountryNode.getLevel() == 2;
    assert updatedCountryNode.getParentId().equals(countryNode.getParentId());
    assert updatedCountryNode.getId().equals(countryNode.getId());
    MetadataTaxonomyNode getCountryNode =
        client
            .getMetadataTaxonomies()
            .getMetadataTaxonomyNodeById(namespace, taxonomyKey, countryNode.getId());
    assert getCountryNode.getDisplayName().equals("Poland UPDATED");
    assert getCountryNode.getId().equals(countryNode.getId());
    client
        .getMetadataTaxonomies()
        .deleteMetadataTaxonomyNode(namespace, taxonomyKey, countryNode.getId());
    client
        .getMetadataTaxonomies()
        .deleteMetadataTaxonomyNode(namespace, taxonomyKey, continentNode.getId());
    delayInSeconds(5);
    MetadataTaxonomyNodes allNodesAfterDeletion =
        client.getMetadataTaxonomies().getMetadataTaxonomyNodes(namespace, taxonomyKey);
    assert allNodesAfterDeletion.getEntries().size() == 0;
    client.getMetadataTaxonomies().deleteMetadataTaxonomy(namespace, taxonomyKey);
  }
}
