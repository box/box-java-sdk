package com.box.sdk;

import org.junit.Test;

public class BoxTransactionalAPIConnectionTest {
    @Test(expected = UnsupportedOperationException.class)
    public void attemptingToAuthenticateATransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.authenticate("authCode");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void attemptingToRefreshATransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.refresh();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void attemptingToSetAutoRefreshOnTransactionalConnectionThrowsError() {
        BoxTransactionalAPIConnection transactionalConnection = new BoxTransactionalAPIConnection("accessToken");
        transactionalConnection.setAutoRefresh(true);
    }
}
