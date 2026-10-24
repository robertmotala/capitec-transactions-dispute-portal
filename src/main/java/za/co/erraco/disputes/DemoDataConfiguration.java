package za.co.erraco.disputes;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DemoDataConfiguration {
  @Bean
  CommandLineRunner seedTransactions(TransactionRepository transactions) {
    return arguments -> {
      if (transactions.count() == 0) {
        transactions.save(transaction("Cape Union Mart", "1249.00", LocalDate.now().minusDays(2)));
        transactions.save(transaction("Checkers", "642.50", LocalDate.now().minusDays(5)));
        transactions.save(transaction("Netflix", "199.00", LocalDate.now().minusDays(10)));
      }
    };
  }

  private Transaction transaction(String merchant, String amount, LocalDate postedOn) {
    var transaction = new Transaction();
    transaction.accountOwner = "customer";
    transaction.merchant = merchant;
    transaction.amount = new BigDecimal(amount);
    transaction.postedOn = postedOn;
    return transaction;
  }
}
