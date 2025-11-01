package dev.budny.financectrl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public User(){}

    public User(String name){
        this.name = name;
    }

    public Long getId() {return id;}

    public String getName(){return name;}

    public void setName(String name) {this.name = name;}
}
