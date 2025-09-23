package com.box.sdk;

import static com.box.sdk.BoxApiProvider.jwtApiForServiceAccount;
import static com.box.sdk.BoxApiProvider.jwtBoxConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

public class BoxAPIConnectionIT {

  @Test
  public void requestIsSentNormallyWhenInterceptorReturnsNullResponse()
      throws MalformedURLException {
    BoxAPIConnection api = new BoxAPIConnection("");

    api.setRequestInterceptor(request -> null);

    BoxAPIRequest request = new BoxAPIRequest(api, new URL("https://box.com"), "GET");
    try (BoxAPIResponse response = request.send()) {
      assertThat(response.getResponseCode(), is(200));
    }
  }

  @Test
  public void refreshSucceeds() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String originalAccessToken = api.getAccessToken();

    api.refresh();

    String actualAccessToken = api.getAccessToken();

    assertThat(originalAccessToken, not(equalTo(actualAccessToken)));
  }

  @Test
  public void refreshesWhenGetAccessTokenIsCalledAndTokenHasExpired() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String originalAccessToken = api.getAccessToken();
    api.setExpires(-1);

    String actualAccessToken = api.getAccessToken();

    assertThat(originalAccessToken, not(equalTo(actualAccessToken)));
  }

  @Test
  public void doesNotRefreshWhenGetAccessTokenIsCalledAndTokenHasNotExpired() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String originalAccessToken = api.getAccessToken();
    api.setExpires(Long.MAX_VALUE);

    String actualAccessToken = api.getAccessToken();

    assertThat(originalAccessToken, equalTo(actualAccessToken));
  }

  @Test
  public void successfullySavesAndRestoresConnection() {
    BoxAPIConnection api = jwtApiForServiceAccount();
    String state = api.save();
    BoxConfig boxConfig = jwtBoxConfig();

    BoxAPIConnection restoredAPI =
        BoxAPIConnection.restore(boxConfig.getClientId(), boxConfig.getClientSecret(), state);
    assertThat(BoxFolder.getRootFolder(restoredAPI).getInfo().getID(), is("0"));
  }

  @Test
  public void revokeToken() {
    BoxAPIConnection api = jwtApiForServiceAccount();

    BoxFolder.getRootFolder(api);

    api.revokeToken();

    try {
      BoxFolder.getRootFolder(api);
    } catch (BoxAPIException ex) {
      assertEquals(401, ex.getResponseCode());
    }
  }

  @Test
  public void appAndDeveloperEditionApiConnectionWorks() {
    BoxDeveloperEditionAPIConnection appAuthConnection = jwtApiForServiceAccount();

    final String name = "app user name two";
    BoxUser.Info createdUserInfo = BoxUser.createAppUser(appAuthConnection, name);
    final String appUserId = createdUserInfo.getID();

    InMemoryLRUAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(1);
    BoxDeveloperEditionAPIConnection api =
        BoxDeveloperEditionAPIConnection.getUserConnection(
            appUserId, jwtBoxConfig(), accessTokenCache);
    BoxUser appUser = new BoxUser(api, appUserId);

    assertThat(api.getAccessToken(), not(equalTo(null)));

    BoxUser.Info info = appUser.getInfo();

    assertThat(info.getID(), equalTo(appUserId));
    assertThat(info.getName(), equalTo(name));

    api.refresh();

    BoxUser appUserFromAdmin = new BoxUser(appAuthConnection, appUserId);
    appUserFromAdmin.delete(false, true);
  }

  @Test
  @Ignore("Takes too long to run")
  public void appUsersAutomaticallyPaginatesCorrectly() {
    BoxDeveloperEditionAPIConnection api = jwtApiForServiceAccount();

    assertThat(api.getAccessToken(), not(equalTo(null)));

    long timestamp = Calendar.getInstance().getTimeInMillis();
    final String name = "appUsersAutomaticallyPaginate ";
    final String externalAppUserId = "@example.com";

    // Must be greater than the default response page size (100) to ensure a second page of results
    final int totalCreatedTestAppUsers = 105;

    // Create Test App Users
    System.out.println("Creating Test App Users");
    for (int i = 1; i <= totalCreatedTestAppUsers; i++) {
      CreateUserParams params = new CreateUserParams();
      params.setExternalAppUserId(timestamp + "_" + i + externalAppUserId);
      BoxUser.createAppUser(api, name + timestamp + " " + i, params);
    }

    // Get App Users
    Iterable<BoxUser.Info> users =
        BoxUser.getAppUsersByExternalAppUserID(api, null, "external_app_user_id", "name");

    // Iterate over the returned App Users
    int totalReturnedTestAppUsers = 0;
    System.out.println("Cleanup (delete) Test App Users");
    for (BoxUser.Info userInfo : users) {
      System.out.println(userInfo.getName());
      BoxUser appUser = new BoxUser(api, userInfo.getID());

      // Count the Test App Users just created.
      if (userInfo.getName().startsWith(name + timestamp)) {
        totalReturnedTestAppUsers++;
      }

      // Delete the Test App Users just created.
      // Deletes Test App Users created in previous test run, as well.
      if (userInfo.getName().startsWith(name)) {
        appUser.delete(false, true);
      }
    }

    assertEquals(totalCreatedTestAppUsers, totalReturnedTestAppUsers);
    api.refresh();
  }

  @Test
  @Ignore("Takes too long to run")
  public void appUsersManuallyPaginatesCorrectly() {
    BoxDeveloperEditionAPIConnection api = jwtApiForServiceAccount();

    assertThat(api.getAccessToken(), not(equalTo(null)));

    final long timestamp = Calendar.getInstance().getTimeInMillis();
    final String name = "appUsersManuallyPaginate ";
    final String externalAppUserId = "@example.com";

    // Must be greater than defaultNetworkResponsePageSize to ensure a second page of results
    final int totalCreatedTestAppUsers = 105;

    // Must be equal to default response limit (usually 100)
    final int defaultNetworkResponsePageSize = 100;

    // Create Test App Users
    System.out.println("Creating Test App Users");
    for (int i = 1; i <= totalCreatedTestAppUsers; i++) {
      CreateUserParams params = new CreateUserParams();
      params.setExternalAppUserId(timestamp + "_" + i + externalAppUserId);
      BoxUser.createAppUser(api, name + timestamp + " " + i, params);
    }

    // Get App Users
    BoxResourceIterable<BoxUser.Info> users =
        (BoxResourceIterable<BoxUser.Info>)
            BoxUser.getAppUsersByExternalAppUserID(
                api, null, true, null, "external_app_user_id", "name");

    // Grab the marker to be able to resume iterating at some point in the future...
    String marker = users.getNextMarker();

    // As we iterate over the page of results, pageCursor is used to keep track
    // of the current item in the page, so that iteration can be picked up where you left
    // off later on.
    int pageCursor = 0;

    int totalReturnedTestAppUsers = 0;

    // Count App Users and stop iterating once we get to the last item on the first page of results
    System.out.println("===FIRST  PAGE===");
    for (BoxUser.Info userInfo : users) {
      System.out.println(userInfo.getName());

      // Count the Test App Users just created.
      if (userInfo.getName().startsWith(name + timestamp)) {
        totalReturnedTestAppUsers++;
      }

      // Stop iterating once we get to the last item on the page of results.
      // If we were to keep iterating in this loop, then BoxResourceIterable would
      // automatically make the request to get the next page of results.
      if (pageCursor == defaultNetworkResponsePageSize - 1) {
        break;
      }

      pageCursor++;
    }

    // Manually get the second page of results, by passing the nextMarker from the last response.
    // This simulates the scenario where we no longer have the users collection, so we need to
    // make a new request, using the preserved marker value which points to the page containing
    // the next item in the collection.
    users =
        (BoxResourceIterable<BoxUser.Info>)
            BoxUser.getAppUsersByExternalAppUserID(
                api, null, true, marker, "external_app_user_id", "name");

    // We're now starting with the first item on the second page of results,
    // so we reset pageCursor
    pageCursor = 0;

    // Continue counting App Users
    // If there are more pages of results, this loop uses the automatic pagination by continuously
    // retrieving the next item and letting BoxResourceIterable determine and manage getting new
    // pages.
    System.out.println("===SECOND PAGE===");
    for (BoxUser.Info userInfo : users) {
      System.out.println(userInfo.getName());

      // Count the users just created.
      if (userInfo.getName().startsWith(name + timestamp)) {
        totalReturnedTestAppUsers++;
      }

      pageCursor++;
    }

    // Get App Users for post-test clean up
    users =
        (BoxResourceIterable<BoxUser.Info>)
            BoxUser.getAppUsersByExternalAppUserID(
                api, null, true, null, "external_app_user_id", "name");

    // Clean up
    System.out.println("Cleanup (delete) Test App Users");
    for (BoxUser.Info userInfo : users) {
      BoxUser appUser = new BoxUser(api, userInfo.getID());

      // Deletes users created in previous test run, as well.
      if (userInfo.getName().startsWith(name)) {
        appUser.delete(false, true);
      }
    }

    assertEquals(totalCreatedTestAppUsers, totalReturnedTestAppUsers);
    api.refresh();
  }

  @Test
  public void getLowerScopedTokenWorks() {
    BoxDeveloperEditionAPIConnection api = jwtApiForServiceAccount();

    String originalToken = api.getAccessToken();
    List<String> scopes = new ArrayList<>();
    scopes.add("item_preview");
    scopes.add("item_content_upload");

    ScopedToken newToken = api.getLowerScopedToken(scopes, null);
    assertThat(newToken, notNullValue());
    assertThat(newToken.getAccessToken(), not(originalToken));
  }
}
