package co.dingco.answer;
import org.springframework.context.annotation.*;
@Configuration class TransactionConfiguration {
  @Bean FailurePoint failurePoint() { return () -> {}; }
}
