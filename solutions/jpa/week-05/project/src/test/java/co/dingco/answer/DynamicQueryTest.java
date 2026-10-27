package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
@DataJpaTest class DynamicQueryTest {
  @Autowired EntityManager em; @Autowired TodoRepository repository;
  @Test void combines_conditions_and_fetches_user_without_n_plus_one() {
    AppUser user=new AppUser("u"); em.persist(user); em.persist(new Todo("target",false,user)); em.persist(new Todo("other",true,user)); em.flush(); em.clear();
    Specification<Todo> spec=(root,query,cb)->cb.and(cb.like(root.get("title"),"%target%"),cb.isFalse(root.get("done")));
    var page=repository.findAll(spec,PageRequest.of(0,10));
    assertThat(page.getContent()).hasSize(1);
    assertThat(em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(page.getContent().get(0).user)).isTrue();
  }
}
