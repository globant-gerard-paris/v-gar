package com.searshc.mygarage.controllers.landing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * The {@link LandingController} have the responsibility to redirect to Landing
 * page.
 *
 * @author Jero
 *
 */
@Controller
public class LandingController {

    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public String redirect(@RequestParam String token) {
        return "redirect:/?token=" + token;
    }
}
