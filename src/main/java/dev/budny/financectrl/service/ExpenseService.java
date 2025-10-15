package dev.budny.financectrl.service;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserService userService){
        this.expenseRepository = expenseRepository;
        this.userService = userService;
    }

    //post
    public Expense save(Expense expense){
        return expenseRepository.save(expense);
    }

    //put
    public Expense update(Long id, String desc, BigDecimal value, LocalDate date){
        Optional<Expense> expenseOptional = expenseRepository.findById(id);
        if(expenseOptional.isPresent()){
            Expense expense = expenseOptional.get();
            expense.setDesc(desc);
            expense.setValue(value);
            expense.setDate(date);
            return expenseRepository.save(expense);
        }
        return null;
    }

    //get all
    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    //get
    public Optional<Expense> getExp(Long id){
        return expenseRepository.findById(id);
    }

    //delete
    public void delete(Long id){
        expenseRepository.deleteById(id);
    }

    //delete user expenses
    public void deleteAll(Long user_id){

    }
}
