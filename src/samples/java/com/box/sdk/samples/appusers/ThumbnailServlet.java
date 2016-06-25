package com.box.sdk.samples.appusers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.samples.appusers.helpers.BoxHelper;

public class ThumbnailServlet extends HttpServlet {
    private static final int MAX_RETRIES = 3;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String boxFileId = request.getParameter("id");
        String boxAppUserId = BoxHelper.getBoxAppUserId(request);
        if (boxAppUserId == null) { // session timeout. force login again.
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        BoxAPIConnection userClient = BoxHelper.userClient(boxAppUserId);
        if (userClient == null) { // session timeout. force login again.
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        BoxFile file = new BoxFile(userClient, boxFileId);
        byte[] thumbBytes;
        int retries = 0;
        // Thumbnails are not always generated immediately upon upload
        do {
            thumbBytes = file.getThumbnail(BoxFile.ThumbnailFileType.PNG, 256, 256, 256, 256);
            if (thumbBytes.length == 0) {
                retries++;
                try {
                    Thread.sleep(2000); // wait a couple of seconds for conversion to generate a thumbnail
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        } while (thumbBytes.length == 0 && (retries <= MAX_RETRIES));

        response.setContentType("image/png");

        OutputStream out = response.getOutputStream();
        out.write(thumbBytes);
        out.close();
    }
}
