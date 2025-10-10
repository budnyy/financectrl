package dev.budny.financectrl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    public User(){}

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
