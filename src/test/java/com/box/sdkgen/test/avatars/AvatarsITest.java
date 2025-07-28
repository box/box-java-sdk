package com.box.sdkgen.test.avatars;

import static com.box.sdkgen.internal.utils.UtilsManager.bufferEquals;
import static com.box.sdkgen.internal.utils.UtilsManager.decodeBase64ByteStream;
import static com.box.sdkgen.internal.utils.UtilsManager.generateByteBuffer;
import static com.box.sdkgen.internal.utils.UtilsManager.readByteStream;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.avatars.CreateUserAvatarRequestBody;
import com.box.sdkgen.schemas.useravatar.UserAvatar;
import com.box.sdkgen.schemas.userfull.UserFull;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

public class AvatarsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testAvatars() {
    UserFull user = client.getUsers().getUserMe();
    UserAvatar createdAvatar =
        client
            .getAvatars()
            .createUserAvatar(
                user.getId(),
                new CreateUserAvatarRequestBody.Builder(
                        decodeBase64ByteStream(
                            "iVBORw0KGgoAAAANSUhEUgAAAQAAAAEAAQMAAABmvDolAAAAA1BMVEW10NBjBBbqAAAAH0lEQVRoge3BAQ0AAADCoPdPbQ43oAAAAAAAAAAAvg0hAAABmmDh1QAAAABJRU5ErkJggg=="))
                    .picFileName("avatar.png")
                    .picContentType("image/png")
                    .build());
    assert !(createdAvatar.getPicUrls().getSmall() == null);
    assert !(createdAvatar.getPicUrls().getLarge() == null);
    assert !(createdAvatar.getPicUrls().getPreview() == null);
    InputStream response = client.getAvatars().getUserAvatar(user.getId());
    assert bufferEquals(readByteStream(response), generateByteBuffer(0)) == false;
    client.getAvatars().deleteUserAvatar(user.getId());
    assertThrows(RuntimeException.class, () -> client.getAvatars().getUserAvatar(user.getId()));
  }
}
