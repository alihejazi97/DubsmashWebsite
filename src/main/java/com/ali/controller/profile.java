package com.ali.controller;

import com.ali.StorageService;
import com.ali.database.model.User;
import com.ali.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class profile {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ModelAndView profile(@RequestParam String username,Principal principal,User user){
        ModelAndView mv = new ModelAndView("profile");
        System.out.println(user.username);
        mv.addObject("user",user);
        if (principal != null){
            mv.addObject("principal",principal);
        }
        return mv;
    }

    @Autowired
    StorageService storageService;

    @PostMapping("/updateAvatar")
    public RedirectView updateAvatar(@RequestParam("avatar") MultipartFile avatarImage, Principal principal) throws IOException {
        File file = storageService.save2File(avatarImage, principal.getName());
        avatarImage.transferTo(file);
        return new RedirectView("redirect:profile");
    }

}
