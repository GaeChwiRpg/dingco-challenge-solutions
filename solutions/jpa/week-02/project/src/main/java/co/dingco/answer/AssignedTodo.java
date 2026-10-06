package co.dingco.answer;
import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;
@Entity class AssignedTodo implements Persistable<Long> {
  @Id Long id; String title; @Transient boolean fresh=true;
  protected AssignedTodo() {}
  AssignedTodo(Long id,String title){this.id=id;this.title=title;}
  public Long getId(){return id;} public boolean isNew(){return fresh;}
  @PostPersist @PostLoad void markNotNew(){fresh=false;}
  void rename(String value){title=value;}
}
