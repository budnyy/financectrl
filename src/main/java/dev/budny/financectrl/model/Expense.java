package dev.budny.financectrl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String desc;
    private BigDecimal value;
    private LocalDate date;
    private Long userId;

    public Expense(){}

    public Expense(String desc, BigDecimal value, LocalDate date, Long userId){
        this.desc = desc;
        this.value = value;
        this.date = date;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getUserId(){
        return userId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

}
