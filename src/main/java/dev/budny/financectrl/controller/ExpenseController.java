package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.service.ExpenseService;
import dev.budny.financectrl.service.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/financectrl/expense")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    @Autowired
    ExpenseController(ExpenseService expenseService, UserService userService){this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping("/dashboard/{userId}")
    public String renderExpense(Model model, @PathVariable Long userId){
        List<Expense> expenseList = getAll(userId);

        int current_month = LocalDate.now().getMonthValue();

        model.addAttribute("expenses",expenseList);
        model.addAttribute("sum", expenseService.sumAllByUserId(userId));
        model.addAttribute("monthsum", expenseService.sumAllMonth(userId, current_month));
        model.addAttribute("userId", userId);
        return "dashboard";
    }

    @PostMapping("/save")
    @ResponseBody
    public Expense save(@RequestBody Expense expense){
        return expenseService.save(expense);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public Expense update(@PathVariable Long id, @RequestBody Expense expense){
        return expenseService.update(id, expense);
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

    @GetMapping("/download/{userId}")
    public String download(@PathVariable Long userId, HttpServletResponse response){
        response.setHeader("Content-Type", "text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"expenses.csv\"");
        List<Expense> expenseList = expenseService.getAll(userId);
        try{
            response.getWriter().write("Description, Value, Date\n");
            for(Expense e : expenseList){
                response.getWriter().write(e.getDescr()+ ", " + e.getValue() + ", " + e.getDate() + "\n");
                System.out.println(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/financectrl/expense/dashboard/{userId}";
    }





}
