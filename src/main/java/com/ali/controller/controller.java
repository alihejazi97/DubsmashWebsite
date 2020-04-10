package com.ali.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class controller  {

    @GetMapping(value = {"/"})
    public ModelAndView index(Principal principal) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("principal",principal);
        return mv;
    }

}
