package co.dingco.answer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/todos")
class TodoController {
  private final AtomicLong sequence = new AtomicLong();
  private final Map<Long, TodoResponse> todos = new ConcurrentHashMap<>();
  @PostMapping ResponseEntity<TodoResponse> create(@Valid @RequestBody CreateTodo request) {
    long id = sequence.incrementAndGet();
    TodoResponse saved = new TodoResponse(id, request.title());
    todos.put(id, saved);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }
  @GetMapping("/{id}") TodoResponse get(@PathVariable long id) {
    TodoResponse value = todos.get(id);
    if (value == null) throw new TodoNotFound(id);
    return value;
  }
  record CreateTodo(@NotBlank String title) {}
  record TodoResponse(long id, String title) {}
}
class TodoNotFound extends RuntimeException {
  TodoNotFound(long id) { super("Todo " + id + " not found"); }
}
