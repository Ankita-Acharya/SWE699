package com.swe699.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TheController {

    @Autowired
    private TheService theService;

    String username;

    @CrossOrigin
    @GetMapping("/current-user")
    public CurrentUser getCurrentUser() {
        CurrentUser user = new CurrentUser();
        user.setUsername(this.username);
        return user;
    }

    // Home page
    @RequestMapping(value = "/home", method = {RequestMethod.POST,  RequestMethod.GET})
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("homePage");
        return mv;
    }

    // Home page when log in
    @RequestMapping(value = "/home2", method = {RequestMethod.POST,  RequestMethod.GET})
    public ModelAndView homePage2(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("homePage2");
        return mv;
    }

    // Deleting account
    @RequestMapping(value = "/delete/{un}", method = {RequestMethod.POST,  RequestMethod.GET})
    public String del(@PathVariable String un){
        theService.deleteAccount(un);
        return "Account deleted for "+ un;
    }

    // when user log in is created
    @RequestMapping(value = "/create", method = {RequestMethod.POST,  RequestMethod.GET})
    public ModelAndView createUser(@RequestParam String uName, @RequestParam String email, @RequestParam String pass){
        Users u = theService.create(uName, email, pass);
        ModelAndView mv = new ModelAndView();
        mv.addObject("PassValue","Account created. Log in to continue");
        mv.setViewName("login");
        return mv;
    }

//    @RequestMapping(value = "/getId/{id}", method = {RequestMethod.POST,  RequestMethod.GET})
//    public Users getUserName(@PathVariable String id){
//        return theService.getUserName(id);
//    }

    // has all cookies and other
    @RequestMapping(value = "/loginUser", method = {RequestMethod.POST,  RequestMethod.GET})
    public ModelAndView loginUser(@RequestParam String loginID, @RequestParam String loginpass, Model model, HttpServletResponse response){
//        List<Users> myList = new ArrayList<>();
//        myList = theService.getAll();

        // adding attribute to model
        Users valuess = theService.getUserName(loginID);
        model.addAttribute("valuess",valuess);

        //model object
        ModelAndView mv = new ModelAndView();

        // setting cookies
        Cookie cookie = new Cookie("username", loginID);
        response.addCookie(cookie);

        if(theService.getUserName(loginID) != null){
            mv.setViewName("updateAccount");
            this.username = theService.getUserName(loginID).getUname();
            mv.addObject("PassValue",""+loginID);
            mv.addObject("PassValue2","Successfully Logged in. \n Update/Verify Information here: ");
            return mv;
        }else{
            mv.setViewName("login");
            mv.addObject("PassValue","Invalid credentials");
            return mv;
        }
    }

    // update account request
    @RequestMapping(value = "/updateComplete", method = {RequestMethod.POST,  RequestMethod.GET})
    public ModelAndView updateComplete(@RequestParam String PassValue, @RequestParam String phone, @RequestParam String planDetail, @RequestParam String language, @CookieValue(value = "username") String username){
        Users u = theService.update(PassValue, phone, planDetail, language);
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",username);
        mv.setViewName("homePage2");
        return mv;
//        return "Passvalue " + PassValue + " phone " + phone+ " planDetails " + planDetail +" language " + language;
    }

    // get user details with account name
    @RequestMapping(value = "/getInfo/{UserName}", method = {RequestMethod.POST,  RequestMethod.GET})
    public Users getInfo(@PathVariable String UserName){
        Users u = theService.getUserName1(UserName);
        return u;
    }

    // all get methods

    @CrossOrigin
    @RequestMapping("/getAll")
    public List<Users> getAll(){
        return theService.getAll();
    }

    @RequestMapping("/createAccount")
    public ModelAndView createAcc(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("createAccount");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/update")
    public ModelAndView update(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("updateAccount");
        return mv;
    }
//
//    @Autowired
//    private TheService theService;
//
//    @RequestMapping("/user")
//    public List<Users> printing(){
//        return theService.getAllUsers();
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/user")
//    public void addUser(@RequestBody Users users){
//        theService.addUser(users);
//    }


}

class CurrentUser{
    String username;
    CurrentUser(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    };

}
