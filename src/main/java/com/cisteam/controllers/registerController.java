package com.cisteam.controllers;


import com.cisteam.Repository.UserDAOImpl;
import com.cisteam.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class registerController {

    @Autowired
    private UserDAOImpl userDAO;

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public ModelAndView registerPage(){
       ModelAndView mv=new ModelAndView("register");
       mv.addObject("user",new User());
        return mv;
    }
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String saveToDB(@ModelAttribute("user") User user){
        userDAO.register(new User(user.getUsername(),user.getFullname(),user.getPassword()));
        return "startpage";
    }

}
