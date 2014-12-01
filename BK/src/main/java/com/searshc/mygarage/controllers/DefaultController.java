package com.searshc.mygarage.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rammel.maestre
 */
@Controller
public class DefaultController implements ErrorController {

    private static final String PATH = "/error";

    private static final Log log = LogFactory.getLog(DefaultController.class);

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request) {
        log.warn("Not found: " + request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI));
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}