package tw.team.project.model;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    @Query("select tra.transactionId as id, tra.amount as amount from Transaction tra")
	Page<Transaction> findAllTransactions(org.springframework.data.domain.Pageable pgb);
    
    @Query("from Transaction tra where tra.orderRoom.orderId = :id")
    Transaction findByOrderId(@Param("id") Integer orderId);
}
