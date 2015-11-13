package napp.security;

import napp.dao.UserDAO;
import napp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by humblehound on 13.11.15.
 */
@Service
public class LoginService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByLogin(username);
        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }


    @Autowired
    private UserDAO userDAO;
}
