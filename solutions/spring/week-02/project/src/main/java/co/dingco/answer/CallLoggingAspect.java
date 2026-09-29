package co.dingco.answer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect @Component
class CallLoggingAspect {
  @Around("execution(* co.dingco.answer.TodoService.*(..))")
  Object trace(ProceedingJoinPoint point) throws Throwable { return point.proceed(); }
}
