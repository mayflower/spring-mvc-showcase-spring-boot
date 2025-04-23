package org.springframework.mvc.extensions.ajax;

import org.springframework.web.context.request.WebRequest;

/**
 * Utility class for handling Ajax requests.
 */
public class AjaxUtils {

    /**
     * Determines if the request is an Ajax request by checking the X-Requested-With header.
     * 
     * @param webRequest the current web request
     * @return true if the request is an Ajax request, false otherwise
     */
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null && "XMLHttpRequest".equals(requestedWith);
    }

    /**
     * Determines if the request is an Ajax upload request.
     * 
     * @param webRequest the current web request
     * @return true if the request is an Ajax upload request, false otherwise
     */
    public static boolean isAjaxUploadRequest(WebRequest webRequest) {
        return webRequest.getParameter("ajaxUpload") != null;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private AjaxUtils() {
    }
}