package com.ali.controller;


import com.ali.database.model.User;
import com.ali.security.service.SecurityService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class Registration_Login {

//    TokenServiceImp tokenServiceImp = new TokenServiceImp();
//
//    ArrayList verificationToken = new ArrayList(100);

//    @GetMapping("/mail/endPoint/{id}")
//    private ModelAndView mailEndpoint(@PathVariable(value = "id")String id){
//        Token token = tokenServiceImp.verifyToken(id);
//        ModelAndView mw = new ModelAndView("registertaion");
//        if (token == null)
//
//            return mw;
//        }
//        return null;
//    }

//    @Bean(autowire = Autowire.BY_TYPE)
//    private JavaMailSender javaMailSender(){
//        return new JavaMailSenderImpl();
//    }

    @Autowired
    SecurityService securityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    MailService mailService;

    @RequestMapping(value = {"/login"})
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }


    @GetMapping(value = {"/registration"})
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView("registration");
        return mv;
    }


    @PostMapping(value = "/registration")
    public RedirectView registrationFormHandling(HttpServletRequest servletRequest){
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        String email = servletRequest.getParameter("email");
        User user = new User(username,passwordEncoder.encode(password),email,new String[]{"ROLE_USER"});
        mailService.sendVerificationEmail(email);
        return new RedirectView("login");
    }

    @GetMapping(value = {"/logout"})
    public RedirectView logout() {
        return new RedirectView("/");
    }

    private JavaMailSender mailSender;



}
