package com.fdmgroup.project_gamesdatabase.controller;

import com.fdmgroup.project_gamesdatabase.model.User;
import com.fdmgroup.project_gamesdatabase.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static Logger LOGGER = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/Login")
    public String login() {
        return "login.jsp";
    }

    @PostMapping("/LoginSubmit")
    public ModelAndView loginSubmit(
            @ModelAttribute("user") User user,
            ModelAndView modelAndView) {
        User userFromDb = userService.getByUsernameAndPassword(user.getUsername(), user.getPassword()).get();
        if (userFromDb == null){
            LOGGER.info("Incorrect username and password combination");
            modelAndView.addObject("message", "incorrect username and or password");
            modelAndView.setViewName("login.jsp");
            return modelAndView;
        }
        modelAndView.addObject("username", userFromDb.getUsername());
        modelAndView.setViewName("WEB-INF/main.jsp");
        return modelAndView;
    }

}
