package progettoWeb.User;


import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserRecord, Integer> {

    Optional<UserRecord> findByUsername(String username);
    UserRecord findByEmail(String email);
}
