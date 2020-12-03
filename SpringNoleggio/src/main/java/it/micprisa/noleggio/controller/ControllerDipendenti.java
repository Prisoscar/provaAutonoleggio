//<!--<input type="hidden" name="idStato" value="${elemento.stato.id}"> -->
package it.micprisa.noleggio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControllerDipendenti {
    

 /*   @RequestMapping(method = RequestMethod.GET)
    public String first(
            ModelMap model
    ) {
        return "login";
    }*/

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "home";
    }*/
    
}
