package com.box.sdkgen.test.shieldlists;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentcountryv2025r0.ShieldListContentCountryV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentcountryv2025r0.ShieldListContentCountryV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentdomainv2025r0.ShieldListContentDomainV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentdomainv2025r0.ShieldListContentDomainV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentemailv2025r0.ShieldListContentEmailV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentemailv2025r0.ShieldListContentEmailV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentipv2025r0.ShieldListContentIpV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentipv2025r0.ShieldListContentIpV2025R0TypeField;
import com.box.sdkgen.schemas.v2025r0.shieldlistscreatev2025r0.ShieldListsCreateV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistsupdatev2025r0.ShieldListsUpdateV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistsv2025r0.ShieldListsV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistv2025r0.ShieldListV2025R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShieldListsITest {

  private static final String userId = getEnvVar("USER_ID");

  private static final BoxClient client = getDefaultClientWithUserSubject(userId);

  @Test
  public void testCreateGetUpdateDeleteShieldList() {
    String shieldListCountryName = String.join("", getUuid(), "shieldListCountry");
    ShieldListV2025R0 shieldListCountry =
        client
            .getShieldLists()
            .createShieldListV2025R0(
                new ShieldListsCreateV2025R0.Builder(
                        shieldListCountryName,
                        new ShieldListContentCountryV2025R0.Builder(Arrays.asList("US", "PL"))
                            .type(ShieldListContentCountryV2025R0TypeField.COUNTRY)
                            .build())
                    .description("A list of things that are shielded")
                    .build());
    String shieldListContentDomainName = String.join("", getUuid(), "shieldListContentDomain");
    ShieldListV2025R0 shieldListContentDomain =
        client
            .getShieldLists()
            .createShieldListV2025R0(
                new ShieldListsCreateV2025R0.Builder(
                        shieldListContentDomainName,
                        new ShieldListContentDomainV2025R0.Builder(
                                Arrays.asList("box.com", "example.com"))
                            .type(ShieldListContentDomainV2025R0TypeField.DOMAIN)
                            .build())
                    .description("A list of things that are shielded")
                    .build());
    String shieldListContentEmailName = String.join("", getUuid(), "shieldListContentEmail");
    ShieldListV2025R0 shieldListContentEmail =
        client
            .getShieldLists()
            .createShieldListV2025R0(
                new ShieldListsCreateV2025R0.Builder(
                        shieldListContentEmailName,
                        new ShieldListContentEmailV2025R0.Builder(
                                Arrays.asList("test@box.com", "test@example.com"))
                            .type(ShieldListContentEmailV2025R0TypeField.EMAIL)
                            .build())
                    .description("A list of things that are shielded")
                    .build());
    String shieldListContentIpName = String.join("", getUuid(), "shieldListContentIp");
    ShieldListV2025R0 shieldListContentIp =
        client
            .getShieldLists()
            .createShieldListV2025R0(
                new ShieldListsCreateV2025R0.Builder(
                        shieldListContentIpName,
                        new ShieldListContentIpV2025R0.Builder(
                                Arrays.asList("127.0.0.1", "80.12.12.12/24"))
                            .type(ShieldListContentIpV2025R0TypeField.IP)
                            .build())
                    .description("A list of things that are shielded")
                    .build());
    ShieldListsV2025R0 shieldLists = client.getShieldLists().getShieldListsV2025R0();
    assert shieldLists.getEntries().size() > 0;
    ShieldListV2025R0 getShieldListCountry =
        client.getShieldLists().getShieldListByIdV2025R0(shieldListCountry.getId());
    assert getShieldListCountry.getName().equals(shieldListCountryName);
    assert getShieldListCountry.getDescription().equals("A list of things that are shielded");
    client
        .getShieldLists()
        .updateShieldListByIdV2025R0(
            shieldListCountry.getId(),
            new ShieldListsUpdateV2025R0.Builder(
                    shieldListCountryName,
                    new ShieldListContentCountryV2025R0.Builder(Arrays.asList("US"))
                        .type(ShieldListContentCountryV2025R0TypeField.COUNTRY)
                        .build())
                .description("Updated description")
                .build());
    ShieldListV2025R0 getShieldListCountryUpdated =
        client.getShieldLists().getShieldListByIdV2025R0(shieldListCountry.getId());
    assert getShieldListCountryUpdated.getDescription().equals("Updated description");
    client.getShieldLists().deleteShieldListByIdV2025R0(shieldListCountry.getId());
    client.getShieldLists().deleteShieldListByIdV2025R0(shieldListContentDomain.getId());
    client.getShieldLists().deleteShieldListByIdV2025R0(shieldListContentEmail.getId());
    client.getShieldLists().deleteShieldListByIdV2025R0(shieldListContentIp.getId());
  }
}
