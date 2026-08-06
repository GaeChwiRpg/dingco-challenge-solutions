package co.dingco.answer;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
class ApiExceptionHandler {
  @ExceptionHandler(TodoNotFound.class)
  ResponseEntity<Map<String,String>> notFound(TodoNotFound error) {
    return ResponseEntity.status(404).body(Map.of("code", "TODO_NOT_FOUND", "message", error.getMessage()));
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String,String>> invalid() {
    return ResponseEntity.badRequest().body(Map.of("code", "VALIDATION_FAILED"));
  }
}
