package com.swe699.userManagement;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Singleton, instance gets stored the first time and then checks repository if instance is there or not when annotation
// says autowired
//aka business service
@Service
public class TheService {

    @Autowired
    private UsersRepository userRepository;

    //create
    public Users create(String uName, String email, String pass){
        return userRepository.save(new Users(uName, email, pass));
    }

    //retrieve
    public List<Users> getAll(){
        return userRepository.findAll();
    }

    // user from username
    public Users getUserName1(String name){
        return (Users) userRepository.findByuName(name);
    }

    // username as user
    public Users getUserName(String name){
        return userRepository.findByuName(name);
    }
    //update operation
    public Users update(String uname, String phone, String planDetail, String language){
        Users u = userRepository.findByuName(uname);
        u.setPhone(phone);
        u.setPlanDetail(planDetail);
        u.setLanguage(language);
        return userRepository.save(u);
    }

    //delete account
    public void deleteAccount(String un1){
        Users u = userRepository.findByuName(un1);
        userRepository.delete(u);
    }


//    private List<Users> user = new ArrayList<>(Arrays.asList(
//            new Users("Anki","A@a.com", "1234"),
//            new Users("Bunny","B@b.com", "12345"),
//            new Users("RandomCat","Cat@meow.com", "1234567")
//    ));
//
//    public void addUser(Users users) {
//        user.add(users);
//    }
//
//    public List<Users> getAllUsers(){
//        return user;
//    }

}
