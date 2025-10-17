package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.User;
import dev.budny.financectrl.service.ExpenseService;
import dev.budny.financectrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;

    @Autowired
    UserController(UserService userService, ExpenseService expenseService){
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @PostMapping("/create")
    public User save(@RequestBody String name){
        User user = new User(name);
        return userService.save(user);
    }

    @DeleteMapping("/delete")
    public void delete(Long id){
        userService.delete(id);
        expenseService.deleteAll(id);
    }
}
