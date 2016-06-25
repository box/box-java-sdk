package com.box.sdk.samples.appusers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.samples.appusers.helpers.BoxHelper;


public class DashboardServlet extends HttpServlet {
    private String dashBoardMessage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        if (BoxHelper.getBoxAppUserName(request) == null) { // session has timed out
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        request.setAttribute("dashboardMessage", "App User: " + BoxHelper.getBoxAppUserName(request));
        BoxAPIConnection userClient = BoxHelper.userClient(BoxHelper.getBoxAppUserId(request));
        if (userClient == null) { // session timeout. force login again.
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        List<BoxFolder.Info> folders = new ArrayList<BoxFolder.Info>();
        List<BoxFile.Info> files = new ArrayList<BoxFile.Info>();

        BoxFolder rootFolder = BoxFolder.getRootFolder(userClient);
        for (BoxItem.Info itemInfo : rootFolder) {
            if (itemInfo instanceof BoxFile.Info) {
                files.add((BoxFile.Info) itemInfo);
            } else if (itemInfo instanceof BoxFolder.Info) {
                folders.add((BoxFolder.Info) itemInfo);
            }
        }
        request.setAttribute("files", files);
        request.setAttribute("folders", folders);
        request.setAttribute("accessToken", userClient.getAccessToken());
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

}
