package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest class AssociationBoundaryTest {
  @Autowired EntityManager em;
  @Test void convenience_method_lazy_boundary_and_orphan_removal() {
    AppUser user=new AppUser("u"); Todo todo=new Todo("t"); user.add(todo); em.persist(user); em.flush(); Long userId=user.id; Long todoId=todo.id; em.clear();
    Todo loaded=em.find(Todo.class,todoId);
    assertThat(em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(loaded.user)).isFalse();
    assertThat(loaded.user.name()).isEqualTo("u");
    assertThat(em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(loaded.user)).isTrue();
    AppUser owner=em.find(AppUser.class,userId); owner.removeFirst(); em.flush(); em.clear();
    assertThat(em.find(Todo.class,todoId)).isNull();
  }
}
