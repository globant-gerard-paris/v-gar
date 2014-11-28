package com.searshc.mygarage.controllers.landing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * The {@link LandingController} have the responsibility to redirect to Landing page.
 * 
 * @author Jero
 *
 */
@Controller
public class LandingController extends AbstractController{

	
	
//	@RequestMapping(value = "/landing", method = RequestMethod.GET)
//	public String redirect(@RequestParam("token") String token) {
//		return "redirect:/?token=" + token;
//	}

	// @RequestMapping(value = "/landing", method = RequestMethod.GET)
	// public ModelAndView redirect(@RequestParam("token") String token) {
	// ModelMap mm = new ModelMap();
	// mm.put("token", token);
	// return new ModelAndView("redirect:/");
	// // return new ModelAndView("redirect:home/user/token/" + token);
	// // return new ModelAndView("redirect:/landing/token/" + token);
	// // return "redirect:/?token=" + token;
	// }

	// @RequestMapping(value = "/landing", method = RequestMethod.GET)
	// public void method(HttpServletResponse httpServletResponse, @RequestParam("token") String
	// token) {
	// httpServletResponse.encodeRedirectURL("/?token=" + token);
	// }

	 @Override
	 @RequestMapping(value = "/landing", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Object attribute = request.getAttribute("token");
		 request.setAttribute("token","123123123123123123123");
		  return new ModelAndView("redirect:/?token=" + attribute);
	}

//	@RequestMapping(value = "/landing", method = RequestMethod.GET)
//	 public String landing(
//			 HttpServletResponse response,HttpServletRequest request,
//			 Model model,
//	 RedirectAttributes redirectAttributes, @RequestParam("token") String token) {
//	 redirectAttributes.addFlashAttribute("token", token);
//	 redirectAttributes.addAttribute("token", token);
//	 response.setDateHeader("token="+token, System.currentTimeMillis());
//	 request.setAttribute("token", token);
//	 return "redirect:/?token="+token;
//	 }
}
