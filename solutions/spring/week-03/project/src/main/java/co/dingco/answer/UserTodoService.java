package co.dingco.answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
class UserTodoService {
  private final JdbcTemplate jdbc;
  private final FailurePoint failure;
  UserTodoService(JdbcTemplate jdbc, FailurePoint failure) { this.jdbc = jdbc; this.failure = failure; }
  @Transactional
  void assign(long userId, String title) {
    jdbc.update("update app_user set todo_count = todo_count + 1 where id = ?", userId);
    failure.afterUserUpdate();
    jdbc.update("insert into todo(user_id, title) values (?, ?)", userId, title);
  }
}
