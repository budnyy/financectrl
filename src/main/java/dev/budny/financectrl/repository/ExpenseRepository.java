package dev.budny.financectrl.repository;

import dev.budny.financectrl.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.value) FROM Expense e WHERE e.userId =:userId")
    BigDecimal sumAllByUserId(@Param("userId") Long userId);

    @Query("SELECT e FROM Expense e WHERE e.userId =:userId")
    List<Expense> getAllByUserId(@Param("userId") Long userId);

}
