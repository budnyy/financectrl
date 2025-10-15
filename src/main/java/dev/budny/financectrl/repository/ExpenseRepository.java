package dev.budny.financectrl.repository;

import dev.budny.financectrl.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    public List<Expense> findAllByUserId(Long userId);

    public Expense findByIdAndUserId(Long id, Long userId);

    public void deletebyIdAndUserId(Long id, Long userId);

}
