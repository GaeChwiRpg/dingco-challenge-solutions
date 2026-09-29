package co.dingco.answer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
@Configuration
class PolicyConfiguration {
  @Bean @Qualifier("fast") TodoPolicy fastPolicy() { return title -> "fast:" + title; }
  @Bean @Qualifier("safe") TodoPolicy safePolicy() { return title -> "safe:" + title; }
}
