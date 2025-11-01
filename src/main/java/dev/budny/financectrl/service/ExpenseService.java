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

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
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
            expense.setDescr(desc);
            expense.setValue(value);
            expense.setDate(date);
            return expenseRepository.save(expense);
        }
        return null;
    }

    //get all
    public List<Expense> getAll(Long userId){
        return expenseRepository.getAllByUserId(userId);
    }

    //get sum
    public BigDecimal sumAllByUserId(Long userId){
        return expenseRepository.sumAllByUserId(userId);
    }

    //get by month
    public List<Expense> getMonth(Long userId, int month){
        List<Expense> expenseListMonth = expenseRepository.getAllByUserId(userId);
        for(Expense exp : expenseListMonth){
            if(exp.getDate().getMonthValue() != month){
                expenseListMonth.remove(exp);
            }
        }
        return expenseListMonth;
    }

    //get sum month
    public BigDecimal sumAllMonth(Long userId, int month){
        List<Expense> expenseList = getMonth(userId, month);
        BigDecimal sum = new BigDecimal("0");
        for(Expense e : expenseList) {
            sum = sum.add(e.getValue());
        }
        return sum;
    }

    //delete
    public void delete(Long id){
        expenseRepository.deleteById(id);
    }

    //delete user expenses
    public void deleteAll(Long user_id){
        List<Expense> expenseList = getAll(user_id);
        for(Expense exp : expenseList){
            expenseRepository.delete(exp);
        }
    }
}
