package com.box.sdkgen.enterpriseconfigurations;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.enterpriseconfigurations.GetEnterpriseConfigurationByIdV2025R0QueryParams;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationcontentandsharingv2025r0.EnterpriseConfigurationContentAndSharingV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationsecurityv2025r0.EnterpriseConfigurationSecurityV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationshieldv2025r0.EnterpriseConfigurationShieldV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationusersettingsv2025r0.EnterpriseConfigurationUserSettingsV2025R0;
import com.box.sdkgen.schemas.v2025r0.enterpriseconfigurationv2025r0.EnterpriseConfigurationV2025R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class EnterpriseConfigurationsITest {

  private static final BoxClient adminClient =
      getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testGetEnterpriseConfigurationById() {
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    EnterpriseConfigurationV2025R0 enterpriseConfiguration =
        adminClient
            .getEnterpriseConfigurations()
            .getEnterpriseConfigurationByIdV2025R0(
                enterpriseId,
                new GetEnterpriseConfigurationByIdV2025R0QueryParams(
                    Arrays.asList("user_settings", "content_and_sharing", "security", "shield")));
    assert convertToString(enterpriseConfiguration.getType()).equals("enterprise_configuration");
    EnterpriseConfigurationUserSettingsV2025R0 userSettings =
        enterpriseConfiguration.getUserSettings();
    assert userSettings.getIsEnterpriseSsoRequired().getValue() == false;
    assert userSettings.getNewUserDefaultLanguage().getValue().equals("English (US)");
    assert userSettings.getNewUserDefaultStorageLimit().getValue() == -1;
    EnterpriseConfigurationContentAndSharingV2025R0 contentAndSharing =
        enterpriseConfiguration.getContentAndSharing();
    assert contentAndSharing.getCollaborationPermissions().getValue().getIsEditorRoleEnabled()
        == true;
    EnterpriseConfigurationSecurityV2025R0 security = enterpriseConfiguration.getSecurity();
    assert security.getIsManagedUserSignupEnabled().getValue() == false;
    EnterpriseConfigurationShieldV2025R0 shield = enterpriseConfiguration.getShield();
    assert shield.getShieldRules().size() == 0;
  }
}
