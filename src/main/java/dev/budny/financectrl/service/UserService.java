package dev.budny.financectrl.service;

import dev.budny.financectrl.model.User;
import dev.budny.financectrl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ExpenseService expenseService;

    @Autowired
    public UserService(UserRepository userRepository, ExpenseService expenseService){
        this.userRepository = userRepository;
        this.expenseService = expenseService;
    }

    //create user
    public User save(User user){
        return save(user);
    }

    //delete user
    public void delete(Long id){
        userRepository.deleteById(id);
        expenseService.deleteAll(id);
    }
}
