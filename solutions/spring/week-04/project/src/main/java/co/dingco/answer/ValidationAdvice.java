package co.dingco.answer;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice class ValidationAdvice {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String,String>> invalid() {
    return ResponseEntity.badRequest().body(Map.of("code","VALIDATION_FAILED"));
  }
}
