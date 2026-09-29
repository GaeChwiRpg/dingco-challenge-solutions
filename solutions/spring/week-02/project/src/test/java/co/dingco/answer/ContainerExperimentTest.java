package co.dingco.answer;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ContainerExperimentTest {
  @Autowired TodoService managed;
  @Test void qualifier_and_proxy_are_observable() {
    assertThat(managed.execute("todo")).isEqualTo("fast:todo");
    assertThat(AopUtils.isAopProxy(managed)).isTrue();
  }
  @Test void directly_created_object_has_no_proxy() {
    TodoService direct = new TodoService(title -> "direct:" + title);
    assertThat(AopUtils.isAopProxy(direct)).isFalse();
    assertThat(direct.execute("todo")).isEqualTo("direct:todo");
  }
}
