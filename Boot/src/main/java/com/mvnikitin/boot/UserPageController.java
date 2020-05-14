package com.mvnikitin.boot;

import com.mvnikitin.boot.entity.User;
import com.mvnikitin.boot.rest.exception.ResourceNotFoundException;
import com.mvnikitin.boot.service.SecurityService;
import com.mvnikitin.boot.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserPageController {
    private SecurityService securityService;
    private UserValidator validator;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String showUserForm(Model uiModel) {
        uiModel.addAttribute("user", new User());
        uiModel.addAttribute("roles", securityService.findAllRoles());
        return "user";
    }

    @GetMapping("/{id}")
    public String showUserFormForEdit(
            Model uiModel, @PathVariable(value = "id") Optional<Long> id) {
        if (id.isPresent()) {
            User user = securityService.findById(id.get()).orElseThrow(() ->
                    new ResourceNotFoundException("A user with ID=" +
                    id + " is not found"));
            uiModel.addAttribute("user", user);
            uiModel.addAttribute("roles", securityService.findAllRoles());
            uiModel.addAttribute("isupdate", true);
        }
        return "user";
    }

    @PostMapping
    public String addOrEditUser(Model uiModel, @Valid User user, BindingResult result) {
        validator.validate(user, result);
        if (result.hasErrors()) {
            uiModel.addAttribute("roles", securityService.findAllRoles());
            if (user.getId() != null) {
                uiModel.addAttribute("isupdate", true);
            }
            return "user";
        }
        securityService.save(user);
        return "redirect:/userlist";
    }
}
