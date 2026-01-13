package dev.budny.financectrl.service;

import dev.budny.financectrl.model.Expense;
import dev.budny.financectrl.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository)
    {
        this.expenseRepository = expenseRepository;
    }

    //post
    public Expense save(Expense expense){
        return expenseRepository.save(expense);
    }

    //put
    public Expense update(Long id, Expense newExpense){
        Expense expense = expenseRepository.findById(id).get();
        expense.setDescr(newExpense.getDescr());
        expense.setValue(newExpense.getValue());
        expense.setDate(newExpense.getDate());
        return expenseRepository.save(expense);
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
        expenseListMonth.removeIf(exp ->exp.getDate().getMonthValue() != month);
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
    public void deleteAll(Long userId){
        List<Expense> expenseList = getAll(userId);
        for(Expense exp : expenseList){
            expenseRepository.delete(exp);
        }
    }
}
