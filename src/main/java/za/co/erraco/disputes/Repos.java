package za.co.erraco.disputes;
import org.springframework.data.jpa.repository.JpaRepository; interface TransactionRepository extends JpaRepository<Transaction,Long>{} interface DisputeRepository extends JpaRepository<Dispute,Long>{}
