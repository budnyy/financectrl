package dev.budny.financectrl.service;

import dev.budny.financectrl.model.User;
import dev.budny.financectrl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(validateUser(user.getName()) != null){
            return null;
        }
        return userRepository.save(user);
    }

    //delete user
    public void delete(Long id){
        userRepository.deleteById(id);
        expenseService.deleteAll(id);
    }

    public Long validateUser(String name){
        List<User> userList = userRepository.findAll();
        for(User u : userList){
            if (u.getName().equals(name)){
                return u.getId();
            }
        }
        return null;
    }
}
