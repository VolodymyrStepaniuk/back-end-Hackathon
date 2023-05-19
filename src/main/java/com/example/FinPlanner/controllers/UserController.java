package com.example.FinPlanner.controllers;

import com.example.FinPlanner.model.User;
import com.example.FinPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    // If will be added login,use this method instead of getFirstUser
    //    @GetMapping
    //    public Iterable<User> getAllUsers(){
    //        return service.getAll();
    //    }

    @GetMapping(value = "/")
    public User getFirstUser(){
        return userService.getAll().iterator().next();
    }

    @PostMapping(value = "/registration")
    public void createUser(@RequestBody User user){
        userService.save(user);
    }
    //The method will not be used in this case
    @GetMapping(value = "/login")
    public String loginUser(@RequestParam String email,@RequestParam String password){
        User user = userService.getByEmailAndPassword(email,password);
        return (user!=null)?"redirect:/":"redirect:/registration";
    }
    //The method will not be used in this case
    @GetMapping(value = "/balance")
    public Double getTotalBalance(){
        return userService.getAll().iterator().next().getTotalIncome();
    }

    @PutMapping(value = "/users/{userId}/{changingType}")
    public User updatePassword(@PathVariable("userId") Long id,
                               @PathVariable("changingType")UserService.PrivateChangingType changingType,
                               @RequestParam String changeable){
        return userService.update(id,changeable,changingType);
    }

}
