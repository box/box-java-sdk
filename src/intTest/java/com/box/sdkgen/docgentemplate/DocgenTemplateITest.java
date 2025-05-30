package com.box.sdkgen.docgentemplate;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.v2025r0.docgenjobsv2025r0.DocGenJobsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentagsv2025r0.DocGenTagsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatebasev2025r0.DocGenTemplateBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatecreaterequestv2025r0.DocGenTemplateCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatesv2025r0.DocGenTemplatesV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatev2025r0.DocGenTemplateV2025R0;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import org.junit.jupiter.api.Test;

public class DocgenTemplateITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testDocgenTemplateCrud() {
    FileFull file = uploadNewFile();
    DocGenTemplateBaseV2025R0 createdDocgenTemplate =
        client
            .getDocgenTemplate()
            .createDocgenTemplateV2025R0(
                new DocGenTemplateCreateRequestV2025R0(new FileReferenceV2025R0(file.getId())));
    DocGenTemplatesV2025R0 docgenTemplates = client.getDocgenTemplate().getDocgenTemplatesV2025R0();
    assert docgenTemplates.getEntries().size() > 0;
    DocGenTemplateV2025R0 fetchedDocgenTemplate =
        client
            .getDocgenTemplate()
            .getDocgenTemplateByIdV2025R0(createdDocgenTemplate.getFile().getId());
    assert fetchedDocgenTemplate.getFile().getId().equals(createdDocgenTemplate.getFile().getId());
    DocGenTagsV2025R0 docgenTemplateTags =
        client
            .getDocgenTemplate()
            .getDocgenTemplateTagsV2025R0(fetchedDocgenTemplate.getFile().getId());
    DocGenJobsV2025R0 docgenTemplateJobs =
        client
            .getDocgenTemplate()
            .getDocgenTemplateJobByIdV2025R0(fetchedDocgenTemplate.getFile().getId());
    assert docgenTemplateJobs.getEntries().size() == 0;
    client
        .getDocgenTemplate()
        .deleteDocgenTemplateByIdV2025R0(createdDocgenTemplate.getFile().getId());
    client.getFiles().deleteFileById(file.getId());
  }
}
