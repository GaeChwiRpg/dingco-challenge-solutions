package co.dingco.answer;
import jakarta.persistence.*;
@Entity class Todo {
  @Id @GeneratedValue(strategy=GenerationType.SEQUENCE) Long id;
  String title;
  protected Todo() {}
  Todo(String title) { this.title=title; }
  void rename(String value) { title=value; }
}
