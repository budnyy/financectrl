package dev.budny.financectrl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String desc;
    private BigDecimal value;
    private LocalDate date;

    public Expense(){}

    public Expense(Long id, String desc, BigDecimal value, LocalDate date){
        this.id = id;
        this.desc = desc;
        this.value = value;
        this.date = date;
    }
}
