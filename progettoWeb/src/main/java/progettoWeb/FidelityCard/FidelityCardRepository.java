package progettoWeb.FidelityCard;

import org.springframework.data.repository.CrudRepository;
import progettoWeb.User.UserRecord;

import java.util.List;

public interface FidelityCardRepository extends CrudRepository<FidelityCardRecord, Integer> {

    List<FidelityCardRecord> findByVendorFidelity(UserRecord userRecord);
    FidelityCardRecord findByUser(UserRecord userRecord);
}
