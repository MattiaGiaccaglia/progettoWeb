package progettoWeb.Coupon;

import org.springframework.data.repository.CrudRepository;
import progettoWeb.User.UserRecord;

import java.util.List;

public interface CouponRepository extends CrudRepository<CouponRecord, Integer> {
}
