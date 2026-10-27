package co.dingco.answer;
import jakarta.persistence.*;
@Entity class AppUser { @Id @GeneratedValue Long id; String name; protected AppUser(){} AppUser(String name){this.name=name;} }
