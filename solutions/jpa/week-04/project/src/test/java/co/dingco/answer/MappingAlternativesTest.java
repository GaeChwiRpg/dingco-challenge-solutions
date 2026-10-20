package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest class MappingAlternativesTest {
  @Autowired EntityManager em;
  @Test void both_models_are_executable_before_adr_decision() {
    em.persist(new SingleCardPayment(1000,"S")); em.persist(new JoinedCardPayment(1000,"J")); em.flush(); em.clear();
    assertThat(em.createQuery("select p from SinglePayment p",SinglePayment.class).getResultList()).hasSize(1);
    assertThat(em.createQuery("select p from JoinedPayment p",JoinedPayment.class).getResultList()).hasSize(1);
  }
}
