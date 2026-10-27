package co.dingco.answer;
import jakarta.persistence.*;
@Entity class Todo { @Id @GeneratedValue Long id; String title; boolean done; @ManyToOne(fetch=FetchType.LAZY) AppUser user;
protected Todo(){} Todo(String title,boolean done,AppUser user){this.title=title;this.done=done;this.user=user;} }
