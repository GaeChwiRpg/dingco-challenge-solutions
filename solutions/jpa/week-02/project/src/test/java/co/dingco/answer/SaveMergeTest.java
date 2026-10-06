package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest class SaveMergeTest {
  @Autowired AssignedTodoRepository repository; @Autowired EntityManager em;
  @Test void assigned_id_uses_explicit_new_state_and_merge_returns_managed_copy() {
    AssignedTodo created=repository.save(new AssignedTodo(7L,"new")); em.flush(); em.detach(created);
    created.rename("detached");
    AssignedTodo merged=repository.save(created);
    assertThat(merged).isNotSameAs(created);
    em.flush(); em.clear();
    assertThat(repository.findById(7L).orElseThrow().title).isEqualTo("detached");
  }
}
