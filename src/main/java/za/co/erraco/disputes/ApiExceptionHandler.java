package za.co.erraco.disputes;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
class ApiExceptionHandler {
  @ExceptionHandler(DisputeService.TransactionNotFoundException.class)
  ResponseEntity<Map<String, String>> notFound(RuntimeException exception) { return error(HttpStatus.NOT_FOUND, exception); }

  @ExceptionHandler(DisputeService.TransactionNotEligibleException.class)
  ResponseEntity<Map<String, String>> conflict(RuntimeException exception) { return error(HttpStatus.CONFLICT, exception); }

  private ResponseEntity<Map<String, String>> error(HttpStatus status, RuntimeException exception) {
    return ResponseEntity.status(status).body(Map.of("message", exception.getMessage()));
  }
}
