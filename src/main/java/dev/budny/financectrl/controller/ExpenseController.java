package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){this.expenseService = expenseService;}

    @GetMapping("/dashboard/{userId}")
    public String renderExpense(Model model, @PathVariable Long userId, @RequestParam(required = false) Integer month){
        List<Expense> expenseList = getAll(userId);

        if(month == null){
            month = LocalDate.now().getMonthValue();
        }

        model.addAttribute("expenses",expenseList);
        model.addAttribute("sum", expenseService.sumAllByUserId(userId));
        model.addAttribute("month", month);
        model.addAttribute("monthsum", expenseService.sumAllMonth(userId, month));
        model.addAttribute("userId", userId);
        return "dashboard";
    }

    @PostMapping("/save")
    @ResponseBody
    public Expense save(@RequestBody Expense expense){
        return expenseService.save(expense);
    }

    @PutMapping("/update")
    @ResponseBody
    public Expense update(@RequestBody Long id, @RequestBody String descr, @RequestBody BigDecimal value, @RequestBody LocalDate date){
        return expenseService.update(id, descr, value, date);
    }

    @GetMapping("/get/{userId}")
    @ResponseBody
    public List<Expense> getAll(@PathVariable Long userId){
        return expenseService.getAll(userId);
    }

    @GetMapping("/get/{userId}/{month}")
    @ResponseBody
    public List<Expense> getExp(@PathVariable Long userId, @PathVariable int month){
        return expenseService.getMonth(userId, month);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Long id){
        expenseService.delete(id);
    }







}
