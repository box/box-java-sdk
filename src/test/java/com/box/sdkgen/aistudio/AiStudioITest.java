package com.box.sdkgen.aistudio;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.aistudio.GetAiAgentByIdQueryParams;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiask.AiAsk;
import com.box.sdkgen.schemas.aiask.AiAskModeField;
import com.box.sdkgen.schemas.aiitemask.AiItemAsk;
import com.box.sdkgen.schemas.aiitemask.AiItemAskTypeField;
import com.box.sdkgen.schemas.aimultipleagentresponse.AiMultipleAgentResponse;
import com.box.sdkgen.schemas.airesponsefull.AiResponseFull;
import com.box.sdkgen.schemas.aisingleagentresponsefull.AiSingleAgentResponseFull;
import com.box.sdkgen.schemas.aistudioagentask.AiStudioAgentAsk;
import com.box.sdkgen.schemas.createaiagent.CreateAiAgent;
import com.box.sdkgen.schemas.filefull.FileFull;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class AiStudioITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testAiStudioCrud() {
    String agentName = getUuid();
    AiSingleAgentResponseFull createdAgent =
        client
            .getAiStudio()
            .createAiAgent(
                new CreateAiAgent.Builder(agentName, "enabled")
                    .ask(new AiStudioAgentAsk("enabled", "desc1"))
                    .build());
    assert createdAgent.getName().equals(agentName);
    AiMultipleAgentResponse agents = client.getAiStudio().getAiAgents();
    int numAgents = agents.getEntries().size();
    assert convertToString(agents.getEntries().get(0).getType()).equals("ai_agent");
    AiSingleAgentResponseFull retrievedAgent =
        client
            .getAiStudio()
            .getAiAgentById(
                createdAgent.getId(),
                new GetAiAgentByIdQueryParams.Builder().fields(Arrays.asList("ask")).build());
    assert retrievedAgent.getName().equals(agentName);
    assert convertToString(retrievedAgent.getAccessState()).equals("enabled");
    assert convertToString(retrievedAgent.getAsk().getAccessState()).equals("enabled");
    assert retrievedAgent.getAsk().getDescription().equals("desc1");
    AiSingleAgentResponseFull updatedAgent =
        client
            .getAiStudio()
            .updateAiAgentById(
                createdAgent.getId(),
                new CreateAiAgent.Builder(agentName, "enabled")
                    .ask(new AiStudioAgentAsk("disabled", "desc2"))
                    .build());
    assert convertToString(updatedAgent.getAccessState()).equals("enabled");
    assert convertToString(updatedAgent.getAsk().getAccessState()).equals("disabled");
    assert updatedAgent.getAsk().getDescription().equals("desc2");
    client.getAiStudio().deleteAiAgentById(createdAgent.getId());
    AiMultipleAgentResponse agentsAfterDelete = client.getAiStudio().getAiAgents();
    assert agentsAfterDelete.getEntries().size() == numAgents - 1;
  }

  @Test
  public void testUseAiAgentReferenceInAiAsk() {
    String agentName = getUuid();
    AiSingleAgentResponseFull createdAgent =
        client
            .getAiStudio()
            .createAiAgent(
                new CreateAiAgent.Builder(agentName, "enabled")
                    .ask(new AiStudioAgentAsk("enabled", "desc1"))
                    .build());
    FileFull fileToAsk = uploadNewFile();
    AiResponseFull response =
        client
            .getAi()
            .createAiAsk(
                new AiAsk.Builder(
                        AiAskModeField.SINGLE_ITEM_QA,
                        "which direction sun rises",
                        Arrays.asList(
                            new AiItemAsk.Builder(fileToAsk.getId(), AiItemAskTypeField.FILE)
                                .content("Sun rises in the East")
                                .build()))
                    .aiAgent(new AiAgentReference.Builder().id(createdAgent.getId()).build())
                    .build());
    assert response.getAnswer().contains("East");
    assert response.getCompletionReason().equals("done");
    assert response.getAiAgentInfo().getModels().size() > 0;
    client.getFiles().deleteFileById(fileToAsk.getId());
    client.getAiStudio().deleteAiAgentById(createdAgent.getId());
  }
}
