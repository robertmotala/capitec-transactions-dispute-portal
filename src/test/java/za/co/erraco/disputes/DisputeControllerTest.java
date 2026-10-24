package za.co.erraco.disputes;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DisputeControllerTest {
  private MockMvc mvc; private DisputeService service;
  @BeforeEach void setUp() { service = mock(DisputeService.class); mvc = MockMvcBuilders.standaloneSetup(new DisputeController(service)).setControllerAdvice(new ApiExceptionHandler()).build(); }
  @Test void createsDispute() throws Exception { var dispute = new Dispute(); dispute.reason = "Card not present"; when(service.dispute(4L, "Card not present")).thenReturn(dispute);
    mvc.perform(post("/api/transactions/4/disputes").contentType(MediaType.APPLICATION_JSON).content("{\"reason\":\"Card not present\"}"))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.reason").value("Card not present")); }
  @Test void rejectsBlankReason() throws Exception { mvc.perform(post("/api/transactions/4/disputes").contentType(MediaType.APPLICATION_JSON).content("{\"reason\":\" \"}"))
      .andExpect(status().isBadRequest()); verifyNoInteractions(service); }
  @Test void returnsNotFoundForMissingTransaction() throws Exception { when(service.dispute(eq(44L), anyString())).thenThrow(new DisputeService.TransactionNotFoundException(44L));
    mvc.perform(post("/api/transactions/44/disputes").contentType(MediaType.APPLICATION_JSON).content("{\"reason\":\"Missing\"}"))
      .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Transaction 44 was not found")); }
}
