package za.co.erraco.disputes;
import jakarta.persistence.*; import java.time.Instant;
@Entity @Table(name="disputes") public class Dispute { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @ManyToOne(optional=false) public Transaction transaction; public String reason; public String status="OPEN"; public Instant createdAt=Instant.now(); }
