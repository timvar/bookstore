package hh.palvelinohjelmointi.bookstore.repository;

import hh.palvelinohjelmointi.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
