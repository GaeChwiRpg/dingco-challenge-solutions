package co.dingco.answer;
import jakarta.persistence.*;
@Entity class Todo {
  @Id @GeneratedValue Long id; String title;
  @ManyToOne(fetch=FetchType.LAZY) AppUser user;
  protected Todo(){} Todo(String title){this.title=title;}
}
