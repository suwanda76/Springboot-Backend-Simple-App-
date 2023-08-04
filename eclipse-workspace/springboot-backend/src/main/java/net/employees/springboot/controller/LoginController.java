package net.employees.springboot.controller;

import net.employees.springboot.model.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        //perform authentication logic such as cheking username and password
        //can use Spring Security or implement own security
    	//to use token / other method for safer authorization , not saving the password
    	//in the db instead store hash value.

        if(isValidCredentials(loginRequest.getUserName(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    public boolean isValidCredentials(String userName, String password) {
    	///this is a very simple demo and should not be used in production. 
       if(userName.equals("suwanda") && password.equals("suwanda")) {
         return  true;
       }else{
           return false;
       }
    }
}
