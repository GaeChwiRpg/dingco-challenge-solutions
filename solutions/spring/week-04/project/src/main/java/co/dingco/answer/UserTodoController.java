package co.dingco.answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api")
class UserTodoController {
  private final AtomicLong sequence = new AtomicLong();
  private final List<Todo> todos = new ArrayList<>();
  @PostMapping("/users/{userId}/todos")
  Todo create(@PathVariable long userId, @Valid @RequestBody CreateTodo input) {
    Todo todo = new Todo(sequence.incrementAndGet(), userId, input.title());
    todos.add(todo); return todo;
  }
  @GetMapping("/users/{userId}/todos")
  PageResult<Todo> list(@PathVariable long userId, @RequestParam(defaultValue="0") @Min(0) int page,
      @RequestParam(defaultValue="20") @Min(1) int size) {
    int bounded = Math.min(size, 100);
    List<Todo> values = todos.stream().filter(it -> it.userId() == userId)
      .sorted(Comparator.comparingLong(Todo::id).reversed()).toList();
    int from = Math.min(page * bounded, values.size());
    int to = Math.min(from + bounded, values.size());
    return new PageResult<>(values.subList(from, to), page, bounded, values.size());
  }
  record CreateTodo(@NotBlank @Size(max=100) String title) {}
  record Todo(long id, long userId, String title) {}
  record PageResult<T>(List<T> content, int page, int size, long total) {}
}
