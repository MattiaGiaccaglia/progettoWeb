package progettoWeb.Administrator;

import org.springframework.data.jpa.repository.JpaRepository;
import progettoWeb.User.UserRecord;
public interface AdministratorRepository extends JpaRepository<UserRecord, Integer> {
}
