package napp.dao;

import napp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by humblehound on 13.11.15.
 */
@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findById(long id);
}
