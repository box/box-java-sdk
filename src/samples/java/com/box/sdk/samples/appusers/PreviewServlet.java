package com.box.sdk.samples.appusers;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.sdk.BoxFile;
import com.box.sdk.samples.appusers.helpers.BoxHelper;
import com.box.sdk.BoxAPIConnection;


public class PreviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String boxFileId = request.getParameter("id");
        String boxId = BoxHelper.getBoxAppUserId(request);

        URL previewUrl;
        BoxAPIConnection userClient = BoxHelper.userClient(boxId);
        if (userClient == null) { // session timeout. force login again.
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        BoxFile boxFile = new BoxFile(userClient, boxFileId);
        previewUrl = boxFile.getPreviewLink();
        response.sendRedirect(previewUrl.toString());
    }
}
