package com.dataart.controller;

import com.dataart.dto.PasswordDTO;
import com.dataart.model.User;
import com.dataart.security.UserAuthentication;
import com.dataart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Роман on 06.11.2017.
 */
/*@CrossOrigin(//origins = {"http://localhost:8080","http://localhost:8090"},
        maxAge = 4800)*/
@RestController
@RequestMapping("/api")
public class UserController extends BaseController{

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

/*    @GetMapping("/welcome")
    public String welcome(){
        logger.info("...start welcome method (testing)");
        return "Welcome, user (testing)";
    }*/

   /* @GetMapping("/login")
    public String login(){
        logger.debug("...start welcome method");
        return "Welcome, user";
    }


    @PostMapping("/reqistration")
    public String reqistration(@RequestBody User user){
        userService.saveUser(user);
        return "redirect:/welcome";
    }*/

/*    @GetMapping(value = "/user", produces = "application/json")
    public User getUser(Principal principial) {

        if (principial != null) {

            logger.info("Got user info for login = " + principial.getName());

            if (principial instanceof AbstractAuthenticationToken){
                return (User) ((AbstractAuthenticationToken)
                        principial).getPrincipal();
            }
        }

        return null;

    }

    @PostMapping(value = "/registration")//, produces = "application/json")
    public ResponseEntity <User> registration(@RequestBody User user) {
        logger.info("Calling the metod for User registration" );
        if(!userService.isUserExist(user)){

            userService.saveUser(user);

            return new ResponseEntity<User>(HttpStatus.CREATED);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletRequest rq, HttpServletResponse rs) {

        SecurityContextLogoutHandler securityContextLogoutHandler =
                new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(rq, rs, null);

    }*/


    @Autowired
    private UserAuthentication userAuthentication;


    @RequestMapping(value = "/register", method = RequestMethod.POST)//UriComponentsBuilder builder
    public ResponseEntity<Void> registerUser(@RequestBody User user) {
        logger.debug("...start registration");
        if (userService.isUserExistByLogin(user.getLogin())) {//, user.getEmail()
            HttpHeaders headers = new HttpHeaders();
            logger.debug("...user with this login exists");
            headers.set("Error.", "Such user exist.");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(new User(user.getFirstName(),user.getLastName(),user.getLogin(), user.getPassword(), user.getEmail()));
        logger.debug("...save user in db");
//        HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(builder.path("/article?id={id}").buildAndExpand(article.getArticleId()).toUri());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> changeUserSettings(@RequestBody User user) {
        logger.debug("...start method for changing the user with id: " + getUserId());
        User existUser = userService.findById(getUserId());
        if (existUser==null){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }

        if (userService.isUserExistByLogin(user.getLogin())) {//, user.getEmail()
            HttpHeaders headers = new HttpHeaders();
            logger.debug("...user with this login exists");
            headers.set("Error.", "Such user with pointed login is existed.");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        //userService.saveUser(new User(user.getFirstName(),user.getLastName(),user.getLogin(), user.getPassword(), user.getEmail()));

        existUser.setLogin(user.getLogin());
        existUser.setFirstName(user.getFirstName());
        existUser.setLastName(user.getLastName());
        existUser.setEmail(user.getEmail());
        existUser.setBirthDate(user.getBirthDate());
        existUser.setSex(user.getSex());
        existUser.setAbout(user.getAbout());

        userService.updateUser(existUser);
        logger.debug("...update user in db");
//        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //TODO return token in body or not return (c) Shekspir
    //@RequestMapping(value = "/user/password/{id}", method = RequestMethod.POST)
    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    public ResponseEntity <Void> changePassword( @RequestBody PasswordDTO passwordDTO){
        logger.debug("...change password user with Id:" + getUserId());

        User existUser = userService.findById(getUserId());
        if (existUser==null){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }

        if(!bCryptPasswordEncoder.matches(passwordDTO.getPaswwordOld(),existUser.getPassword())){
            return new ResponseEntity <>(HttpStatus.CONFLICT);
        }

        existUser.setPassword(passwordDTO.getPaswwordNew());

        userService.changePassword(existUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUser() {
        logger.debug("...get user by Id:" + getUserId());
        User user = userService.findById(getUserId());
        if (user==null){
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String token = userAuthentication.attemptAuthentication(user.getLogin(), user.getPassword());
        logger.debug("...authentication user");
        if (token == null) {
            logger.debug("...token == null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        logger.debug("...return response entity with token inside");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> welcome() {
        logger.debug("...testing the controller");
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }


}
