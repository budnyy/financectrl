package dev.budny.financectrl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String descr;
    private BigDecimal value;
    private LocalDate date;
    private Long userId;

    public Expense(){}

    @JsonCreator
    public Expense(String descr, BigDecimal value, LocalDate date, Long userId) {
        this.descr = descr;
        this.value = value;
        this.date = date;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getDescr() {
        return descr;
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

    public void setDescr(String descr) {
        this.descr = descr;
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
