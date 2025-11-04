package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){this.expenseService = expenseService;}

    @GetMapping
    public String renderExpense(Model model, @RequestParam Long userId, @RequestParam(required = false) Integer month){
        List<Expense> expenseList = getAll(userId);

        if(month == null){
            month = LocalDate.now().getMonthValue();
        }

        model.addAttribute("expenses",expenseList);
        model.addAttribute("sum", expenseService.sumAllByUserId(userId));
        model.addAttribute("month", month);
        model.addAttribute("monthsum", expenseService.sumAllMonth(userId, month));
        model.addAttribute("userId", userId);
        return "home";
    }

    @PostMapping("/save")
    public Expense save(@RequestBody Expense expense){
        return expenseService.save(expense);
    }

    @PutMapping("/update")
    public Expense update(@RequestBody Long id, @RequestBody String descr, @RequestBody BigDecimal value, @RequestBody LocalDate date){
        return expenseService.update(id, descr, value, date);
    }

    @GetMapping("/home")
    public List<Expense> getAll(@RequestParam Long userId){
        return expenseService.getAll(userId);
    }

    @GetMapping("/home/{month}")
    public List<Expense> getExp(@RequestBody Long userId, @RequestBody int month){
        return expenseService.getMonth(userId, month);
    }

    @DeleteMapping("/delete")
    public void delete(Long id){
        expenseService.delete(id);
    }







}
