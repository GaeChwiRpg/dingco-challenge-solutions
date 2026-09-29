package co.dingco.answer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
class TodoService {
  private final TodoPolicy policy;
  TodoService(@Qualifier("fast") TodoPolicy policy) { this.policy = policy; }
  String execute(String title) { return policy.decide(title); }
}
