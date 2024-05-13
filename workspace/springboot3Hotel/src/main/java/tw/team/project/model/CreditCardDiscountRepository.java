package tw.team.project.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreditCardDiscountRepository extends JpaRepository<CreditCardDiscount, Integer>{

    @Query("select count(*) from CreditCardDiscount ccd where ccd.bankName = :name")
    public Long existsByName(@Param(value = "name") String bankName);
}
