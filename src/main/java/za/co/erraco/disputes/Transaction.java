package za.co.erraco.disputes;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity @Table(name="transactions") public class Transaction { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; public String accountOwner; public String merchant; public BigDecimal amount; public LocalDate postedOn; public String status="POSTED"; }
