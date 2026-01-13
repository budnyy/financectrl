package dev.budny.financectrl.service;

import dev.budny.financectrl.model.User;
import dev.budny.financectrl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<User> userList = userRepository.findAll();
        for(User u : userList){
            if (u.getName().equals(user.getName())){
                return u;
            }
        }
        return userRepository.save(user);
    }

    //delete user
    public void delete(Long id){
        userRepository.deleteById(id);
        expenseService.deleteAll(id);
    }
}
