package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest class PersistenceContextTest {
  @Autowired EntityManager em;
  @Test void identity_cache_dirty_check_and_clear() {
    Todo todo=new Todo("before"); em.persist(todo); em.flush(); em.clear();
    Todo first=em.find(Todo.class,todo.id); Todo second=em.find(Todo.class,todo.id);
    assertThat(first).isSameAs(second);
    first.rename("after"); em.flush(); em.clear();
    assertThat(em.find(Todo.class,todo.id).title).isEqualTo("after");
  }
}
