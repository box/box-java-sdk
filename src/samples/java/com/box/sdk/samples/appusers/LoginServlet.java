package com.box.sdk.samples.appusers;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.samples.appusers.helpers.BoxHelper;

/**
 *
 */
public class LoginServlet extends HttpServlet {
    private String connectMessage;
    private Boolean loggedIn = false;


    @Override
    public void init() throws ServletException {
        Logger logger = Logger.getLogger("com.box.sdk");
        logger.setLevel(Level.FINE);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINE);
        logger.addHandler(handler);
        log(" init called ");
    }

    /**
     * doGet kicks of the signin/create pprocess.
     * @param request  HttpServletRequest
     * @param response HttpServletResponse to return
     * @throws ServletException if servlet error
     * @throws IOException if error reading config file
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log(" Servlet Path: " + request.getServletPath());
        request.setAttribute("connectMessage", "Connecting");
        BoxAPIConnection adminClient = BoxHelper.adminClient();
        if (adminClient != null) {
            request.setAttribute("connectMessage", "Successfully connected to the Box Enterprise Server");
            log("connectMessage  Successfully connected as ");
        } else {
            request.setAttribute("connectMessage", "Counld not connect to the Box Enterprise Server");
        }
        int maxIdle = request.getSession().getMaxInactiveInterval();
        log(" Max idle time for session: " + maxIdle);
        // set session timeout to two hours.
        request.getSession().setMaxInactiveInterval(120*60);
        //    reset user name and id here.
        BoxHelper.setBoxAppUserId(request, null);
        BoxHelper.setBoxAppUserName(request, null);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
