package co.dingco.answer;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
@SpringBootTest
class TransactionRollbackTest {
  @Autowired UserTodoService service;
  @Autowired JdbcTemplate jdbc;
  @MockBean FailurePoint failure;
  @BeforeEach void seed() { jdbc.update("delete from todo"); jdbc.update("delete from app_user"); jdbc.update("insert into app_user(id,todo_count) values(1,0)"); }
  @Test void both_writes_roll_back_after_middle_failure() {
    doThrow(new IllegalStateException("intentional")).when(failure).afterUserUpdate();
    assertThatThrownBy(() -> service.assign(1, "atomic")).isInstanceOf(IllegalStateException.class);
    assertThat(jdbc.queryForObject("select todo_count from app_user where id=1", Integer.class)).isZero();
    assertThat(jdbc.queryForObject("select count(*) from todo", Integer.class)).isZero();
  }
}
