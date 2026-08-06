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
class TodoApiTest {
  @Autowired MockMvc mvc;
  @Test void create_and_read_contract() throws Exception {
    mvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"flow\"}"))
      .andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("flow"));
  }
  @Test void rejects_blank_title() throws Exception {
    mvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"\"}"))
      .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("VALIDATION_FAILED"));
  }
  @Test void maps_missing_todo_to_404() throws Exception {
    mvc.perform(get("/todos/999999")).andExpect(status().isNotFound())
      .andExpect(jsonPath("$.code").value("TODO_NOT_FOUND"));
  }
}
