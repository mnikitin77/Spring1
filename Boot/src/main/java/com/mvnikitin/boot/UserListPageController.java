package com.mvnikitin.boot;

import com.mvnikitin.boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/userlist")
public class UserListPageController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model uiModel) {
        uiModel.addAttribute("users",
                userService.findAll());
        return "userlist";
    }

    @GetMapping("/edit")
    public String showUserForm(
            Model uiModel, @RequestParam(value = "id") Optional<Long> id) {
//TODO
        return "user";
    }
}
