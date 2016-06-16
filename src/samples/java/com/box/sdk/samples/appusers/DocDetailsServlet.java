package com.box.sdk.samples.appusers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.samples.appusers.helpers.BoxHelper;


public class DocDetailsServlet extends HttpServlet {
    static final String[] EXTENSIONS = {"as", "as3", "asm", "bat", "c", "cc", "cmake", "cpp", "cs", "css", "csv", "cxx",
                                        "diff", "doc", "docx", "erb", "gdoc", "groovy", "gsheet", "h", "haml", "hh",
                                        "htm", "html", "java", "js", "less", "m", "make", "ml", "mm", "msg", "ods",
                                        "odt", "odp", "pdf", "php", "pl", "ppt", "pptx", "properties", "py", "rb",
                                        "rtf", "sass", "scala", "scm", "script", "sh", "sml", "sql", "txt", "vi", "vim",
                                        "wpd", "xls", "xlsm", "xlsx", "xml", "xsd", "xsl", "yaml", "ai", "bmp", "gif",
                                        "eps", "jpeg", "jpg", "png", "ps", "psd", "svg", "tif", "tiff", "dcm", "dicm",
                                        "dicom", "svs", "tga", "aac", "aifc", "aiff", "amr", "au", "flac", "m4a", "mp3",
                                        "ogg", "ra", "wav", "wma", "3g2", "3gp", "avi", "m2v", "m2ts", "m4v", "mkv",
                                        "mov", "mp4", "mpeg", "mpg", "ogg", "mts", "qt", "wmv"};
    static final Set<String> SUPPORTED_PREVIEW_EXTENSIONS = new HashSet<String>(Arrays.asList(EXTENSIONS));

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
        BoxFile file = new BoxFile(userClient, boxFileId);
        BoxFile.Info fileInfo = file.getInfo("id", "name", "size", "created_at", "extension");
        String fileName = fileInfo.getName();
        int fileSizeKB = (int) (fileInfo.getSize() / 1024);
        String createdAt = fileInfo.getCreatedAt().toString();
        String fileId = fileInfo.getID();

        String fileExtension = fileInfo.getExtension();
        request.setAttribute("supportsPreview", SUPPORTED_PREVIEW_EXTENSIONS.contains(fileExtension));

        request.setAttribute("fileName", fileName);
        request.setAttribute("fileSizeKB", fileSizeKB);
        request.setAttribute("createdAt", createdAt);
        request.setAttribute("fileId", fileId);

        request.getRequestDispatcher("docDetails.jsp").forward(request, response);
    }
}
