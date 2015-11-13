package napp.controllers;

import com.fasterxml.jackson.annotation.JsonRawValue;
import javafx.beans.property.StringProperty;
import napp.dao.UserDAO;
import napp.model.User;
import napp.security.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * Created by humblehound on 13.11.15.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> loginDefault(@RequestParam("login") String login, @RequestParam("password") String password){
        User user = userDAO.findByLogin(login);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(!user.getPassword().equals(password)){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(new Wrapper(user.getId()), HttpStatus.OK);

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Wrapper registerDefault(@RequestParam("login") String login, @RequestParam("password") String password){
        User user = new User(login, password);
        try{
            userDAO.save(user);
        }catch (Exception ex){
            return null;
        }
        return new Wrapper(user.getId());
    }

    public class Wrapper {

        private String userId;

        public Wrapper(Long id) {
            this.userId = id.toString();
        }

        public String getUserId() {
            return userId;
        }
    }

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginService loginService;

}
