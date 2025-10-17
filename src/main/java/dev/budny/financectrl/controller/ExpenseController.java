package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){this.expenseService = expenseService;}

    @PostMapping("/save")
    public Expense save(@RequestBody String desc, BigDecimal value, LocalDate date, Long userId){
        Expense expense = new Expense(desc, value, date, userId);
        return expenseService.save(expense);
    }

    @PutMapping("/update")
    public Expense update(@RequestBody Long id, String desc, BigDecimal value, LocalDate date, Long userId){
        return expenseService.update(id, desc, value, date, userId);
    }

    @GetMapping("/home")
    public List<Expense> getAll(@RequestBody Long userId){
        return expenseService.getAll(userId);
    }

    @GetMapping("/home/{id}")
    public List<Expense> getExp(@RequestBody Long userId, int month){
        return expenseService.getMonth(userId, month);
    }

    @DeleteMapping("/delete")
    public void delete(Long id){
        expenseService.delete(id);
    }







}
