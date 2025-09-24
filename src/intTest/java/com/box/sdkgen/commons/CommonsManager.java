package com.box.sdkgen.commons;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.decodeBase64;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.isBrowser;

import com.box.sdkgen.box.ccgauth.BoxCCGAuth;
import com.box.sdkgen.box.ccgauth.CCGConfig;
import com.box.sdkgen.box.jwtauth.BoxJWTAuth;
import com.box.sdkgen.box.jwtauth.JWTConfig;
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBody;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigClassificationField;
import com.box.sdkgen.managers.classifications.AddClassificationRequestBodyDataStaticConfigField;
import com.box.sdkgen.managers.classifications.CreateClassificationTemplateRequestBody;
import com.box.sdkgen.managers.classifications.CreateClassificationTemplateRequestBodyFieldsField;
import com.box.sdkgen.managers.folders.CreateFolderRequestBody;
import com.box.sdkgen.managers.folders.CreateFolderRequestBodyParentField;
import com.box.sdkgen.managers.shieldinformationbarriers.CreateShieldInformationBarrierRequestBody;
import com.box.sdkgen.managers.termsofservices.CreateTermsOfServiceRequestBody;
import com.box.sdkgen.managers.termsofservices.CreateTermsOfServiceRequestBodyStatusField;
import com.box.sdkgen.managers.termsofservices.CreateTermsOfServiceRequestBodyTosTypeField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBody;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesField;
import com.box.sdkgen.managers.uploads.UploadFileRequestBodyAttributesParentField;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplate;
import com.box.sdkgen.schemas.classificationtemplate.ClassificationTemplateFieldsOptionsField;
import com.box.sdkgen.schemas.enterprisebase.EnterpriseBase;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.files.Files;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarriers.ShieldInformationBarriers;
import com.box.sdkgen.schemas.termsofservice.TermsOfService;
import com.box.sdkgen.schemas.termsofservices.TermsOfServices;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommonsManager {

  public static BoxCCGAuth getCcgAuth() {
    CCGConfig ccgConfig =
        new CCGConfig.Builder(getEnvVar("CLIENT_ID"), getEnvVar("CLIENT_SECRET"))
            .enterpriseId(getEnvVar("ENTERPRISE_ID"))
            .build();
    BoxCCGAuth auth = new BoxCCGAuth(ccgConfig);
    return auth;
  }

  public static BoxJWTAuth getJwtAuth() {
    JWTConfig jwtConfig =
        JWTConfig.fromConfigJsonString(decodeBase64(getEnvVar("JWT_CONFIG_BASE_64")));
    BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
    return auth;
  }

  public static BoxClient getDefaultClientWithUserSubject(String userId) {
    if (isBrowser()) {
      BoxCCGAuth ccgAuth = getCcgAuth();
      BoxCCGAuth ccgAuthUser = ccgAuth.withUserSubject(userId);
      return new BoxClient(ccgAuthUser);
    }
    BoxJWTAuth auth = getJwtAuth();
    BoxJWTAuth authUser = auth.withUserSubject(userId);
    return new BoxClient(authUser);
  }

  public static BoxClient getDefaultClient() {
    BoxClient client = new BoxClient((isBrowser() ? getCcgAuth() : getJwtAuth()));
    return client;
  }

  public static FolderFull createNewFolder() {
    BoxClient client = getDefaultClient();
    String newFolderName = getUuid();
    return client
        .getFolders()
        .createFolder(
            new CreateFolderRequestBody(
                newFolderName, new CreateFolderRequestBodyParentField("0")));
  }

  public static FileFull uploadNewFile() {
    BoxClient client = getDefaultClient();
    String newFileName = String.join("", getUuid(), ".pdf");
    InputStream fileContentStream = generateByteStream(1024 * 1024);
    Files uploadedFiles =
        client
            .getUploads()
            .uploadFile(
                new UploadFileRequestBody(
                    new UploadFileRequestBodyAttributesField(
                        newFileName, new UploadFileRequestBodyAttributesParentField("0")),
                    fileContentStream));
    return uploadedFiles.getEntries().get(0);
  }

  public static TermsOfService getOrCreateTermsOfServices() {
    BoxClient client = getDefaultClient();
    TermsOfServices tos = client.getTermsOfServices().getTermsOfService();
    int numberOfTos = tos.getEntries().size();
    if (numberOfTos >= 1) {
      TermsOfService firstTos = tos.getEntries().get(0);
      if (convertToString(firstTos.getTosType()).equals("managed")) {
        return firstTos;
      }
    }
    if (numberOfTos >= 2) {
      TermsOfService secondTos = tos.getEntries().get(1);
      if (convertToString(secondTos.getTosType()).equals("managed")) {
        return secondTos;
      }
    }
    return client
        .getTermsOfServices()
        .createTermsOfService(
            new CreateTermsOfServiceRequestBody.Builder(
                    CreateTermsOfServiceRequestBodyStatusField.DISABLED, "Test TOS")
                .tosType(CreateTermsOfServiceRequestBodyTosTypeField.MANAGED)
                .build());
  }

  public static ClassificationTemplateFieldsOptionsField getOrCreateClassification(
      ClassificationTemplate classificationTemplate) {
    BoxClient client = getDefaultClient();
    List<ClassificationTemplateFieldsOptionsField> classifications =
        classificationTemplate.getFields().get(0).getOptions();
    int currentNumberOfClassifications = classifications.size();
    if (currentNumberOfClassifications == 0) {
      ClassificationTemplate classificationTemplateWithNewClassification =
          client
              .getClassifications()
              .addClassification(
                  Arrays.asList(
                      new AddClassificationRequestBody(
                          new AddClassificationRequestBodyDataField.Builder(getUuid())
                              .staticConfig(
                                  new AddClassificationRequestBodyDataStaticConfigField.Builder()
                                      .classification(
                                          new AddClassificationRequestBodyDataStaticConfigClassificationField
                                                  .Builder()
                                              .classificationDefinition("Some description")
                                              .colorId(3L)
                                              .build())
                                      .build())
                              .build())));
      return classificationTemplateWithNewClassification.getFields().get(0).getOptions().get(0);
    }
    return classifications.get(0);
  }

  public static ClassificationTemplate getOrCreateClassificationTemplate() {
    BoxClient client = getDefaultClient();
    try {
      return client.getClassifications().getClassificationTemplate();
    } catch (Exception exception) {
      return client
          .getClassifications()
          .createClassificationTemplate(
              new CreateClassificationTemplateRequestBody(
                  Arrays.asList(
                      new CreateClassificationTemplateRequestBodyFieldsField(
                          Collections.emptyList()))));
    }
  }

  public static ShieldInformationBarrier getOrCreateShieldInformationBarrier(
      BoxClient client, String enterpriseId) {
    ShieldInformationBarriers barriers =
        client.getShieldInformationBarriers().getShieldInformationBarriers();
    int numberOfBarriers = barriers.getEntries().size();
    if (numberOfBarriers == 0) {
      return client
          .getShieldInformationBarriers()
          .createShieldInformationBarrier(
              new CreateShieldInformationBarrierRequestBody(
                  new EnterpriseBase.Builder().id(enterpriseId).build()));
    }
    return barriers.getEntries().get(numberOfBarriers - 1);
  }
}
