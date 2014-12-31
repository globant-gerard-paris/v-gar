package com.searshc.mygarage.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author rammel.maestre
 */
@Controller
public class DefaultController {

    private static final Log log = LogFactory.getLog(DefaultController.class);

    @RequestMapping(value = {"/dashboard", "/car-profile", "/store-locator", "/recalls", "/services"})
    public String redirect(HttpServletRequest request) {
        Object forward = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
        log.warn("Not found: " + (forward == null ? request.getRequestURI() : forward));
        return "redirect:/";
    }

}
