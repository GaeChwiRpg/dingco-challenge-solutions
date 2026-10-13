package co.dingco.answer;
import jakarta.persistence.*; import java.util.*;
@Entity class AppUser {
  @Id @GeneratedValue Long id; String name;
  @OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true) List<Todo> todos=new ArrayList<>();
  protected AppUser(){} AppUser(String name){this.name=name;}
  String name(){return name;}
  void add(Todo todo){todos.add(todo);todo.user=this;} void remove(Todo todo){todos.remove(todo);todo.user=null;}
  void removeFirst(){remove(todos.get(0));}
}
