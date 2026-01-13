package dev.budny.financectrl.controller;

import dev.budny.financectrl.model.User;
import dev.budny.financectrl.service.ExpenseService;
import dev.budny.financectrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/financectrl/user")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;

    @Autowired
    UserController(UserService userService, ExpenseService expenseService){
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public String renderUser(Model model){
        String nameVar = "";
        model.addAttribute(nameVar);
        return "index";
    }

    @PostMapping("/create")
    public String save(@RequestParam String name, RedirectAttributes redirectAttributes){
        User user = new User(name.strip());
        User u = userService.save(user);

        redirectAttributes.addAttribute("userId", u.getId());
        return "redirect:/financectrl/expense/dashboard/{userId}";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id){
        userService.delete(id);
        expenseService.deleteAll(id);
        return "redirect:/user";
    }
}
