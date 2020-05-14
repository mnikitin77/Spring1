package com.mvnikitin.boot;

import com.mvnikitin.boot.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userlist")
public class UserListPageController {
    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String showUsers(Model uiModel) {
        uiModel.addAttribute("users",
                securityService.findAll());
        return "userlist";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        securityService.remove(id);
        return "redirect:/userlist";
    }
}
