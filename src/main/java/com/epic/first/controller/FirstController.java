package com.epic.first.controller;

import com.epic.first.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class FirstController {


    @PutMapping("/Update")
    public void updateStrings(@RequestBody String details) {
        updateStrings(details);
    }

    @PostMapping(value = "/insertParam")
    public String getInfo(@RequestParam("Username") String Username, @RequestParam("email") String email, @RequestParam("Password") String Password) {
        System.out.println("Passing Multiple values using @RequestParam");
        return "Username-->" + Username + "\n email-->" + email + "\nPassword-->" + Password;

    }


    @GetMapping(value = "/user")
    public String getUser(@RequestParam String name) {
        System.out.println("Return single value from requestParam");
        return "My name is " + name;
    }


    @PostMapping(value = "/insertHead", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getData(@RequestHeader String name, @RequestHeader int age, @RequestHeader String address) {
        System.out.println("Post Mapping Headers called");
        return "name-->" + name + "\n age-->" + age + "\naddress-->" + address;

    }//@PathVari,MultiValueMap

    @GetMapping(value = "/userName/{userName}")
    public String printUserName(@PathVariable(name = "userName") String name) {
        System.out.println("@PathVariable single value");
        return "My name is " + name;
    }

    @GetMapping(value = "/shape/{color}/{Length}/{width}")
    public String printDetails(@PathVariable(name = "color") String color,
                               @PathVariable(name = "Length") int Length, @PathVariable(name = "width") int width) {
        System.out.println("@PathVariable multiple values");
        return "Color-->" + color + "\nLength-->" + Length + "\nWidth-->" + width;
    }

    @PostMapping(value = "/getHeaderData")
    public String handleRequest(@RequestHeader("key") String request) {
        System.out.println("RequestHeader Method called " + request);
        return "Header is " + request;
    }

    @GetMapping(value = "/getMultiHeadValue")
    public String multipleRequest(@RequestHeader("name") String name,
                                  @RequestHeader("age") int age, @RequestHeader("height") int height) {
        System.out.println("Multiple @RequestHeader called");
        return "Name--> " + name + "\n age-->" + age + "\nHeight-->" + height;
    }

    @GetMapping("/getmultivalue")
    public String multivalue(String name, String password) {
        System.out.println("MultivaluedMap is Called");

        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("Sandun", "\tsssss");
        multiValueMap.add("\nKamal", "\tkkk2");
        multiValueMap.add("\nAmal", "\tdwd3");

        return "Multivalues-->" + multiValueMap;
    }

    @PostMapping("/requestBodyExample")
    public String requestBodyEx(@RequestBody String name, String color) {
        System.out.println("@RequestBody is called");
        return "" + name;
    }

    @PostMapping("/requestBodyBean")
    public String requestBodyEx(@RequestBody User user) {
        System.out.println("@RequestBody is called");
        System.out.println("User parameter->" + user.getUserName());
        System.out.println("User parameter" + user.getPassword());
        return "" + user.getUserName() + "\t" + user.getPassword();
    }


    //use
    @PostMapping("/getResponseEntity")
    public ResponseEntity<?> getDetails(@RequestParam String name) {
        System.out.println("ResponseEntity Example");

        System.out.println("UserName--> " + name);

        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @PostMapping(value = "/responseEntity")
    public ResponseEntity<?> getResponseDetails(@RequestBody User user) {
        System.out.println("ResponseEntity Example 2");

        System.out.println("UserName--> " + user.getUserName());
        System.out.println("Password--> " + user.getPassword());


        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/MultiDetails")
    public String getAlldata(@RequestHeader MultiValueMap<String, String> headerdata) {

        System.out.println("All headers with multivaluedMap");
        String MobileNo = headerdata.getFirst("MobileNo:");

        //System.out.println("Username is -->" + MobileNo);
        headerdata.forEach((k, v) -> System.out.println(" value" + v));

        return ("mobile numbers are " + MobileNo);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/readProperties")
    public String getProperties() {

        System.out.println("Property of application.properties file");
        System.out.println("Port--> " + port);
        return "server port is--> " + port;
    }


    @PostMapping(value = "/beanValidate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String beanValidate(@Valid @RequestBody User user) {
        System.out.println("BeanValidation Example");

        System.out.println("UserName--> " + user.getUserName());

        return user.getUserName();
    }

    @PostMapping(value = "/validateBeans", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validateBeans(@Valid @RequestBody User user) {


        System.out.println("ResponseEntity Example 2");
        System.out.println("UserName--> " + user.getUserName());
        System.out.println("Password--> " + user.getPassword());

     return new ResponseEntity<>(user, HttpStatus.OK);
       // return ResponseEntity.ok("User data is valid");

    }


}










