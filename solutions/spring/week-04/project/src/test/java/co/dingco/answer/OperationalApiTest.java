package co.dingco.answer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest @AutoConfigureMockMvc
class OperationalApiTest {
  @Autowired MockMvc mvc;
  @Test void validates_and_pages_stably() throws Exception {
    mvc.perform(post("/api/users/1/todos").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"ship\"}"))
      .andExpect(status().isOk()).andExpect(jsonPath("$.userId").value(1));
    mvc.perform(get("/api/users/1/todos?page=0&size=500"))
      .andExpect(status().isOk()).andExpect(jsonPath("$.size").value(100))
      .andExpect(jsonPath("$.content[0].title").value("ship"));
  }
  @Test void common_validation_error_contract() throws Exception {
    mvc.perform(post("/api/users/1/todos").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\"}"))
      .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("VALIDATION_FAILED"));
  }
}
