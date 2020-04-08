package com.mvnikitin.mvcexamp;

import com.mvnikitin.mvcexamp.model.Product;
import com.mvnikitin.mvcexamp.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MainPageController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String displayPage(Model uiModel) {
        uiModel.addAttribute("products", productService.getProducts());
        return "index";
    }

    @GetMapping("/{id}")
    public String displayProduct(Model uiModel, @PathVariable(value = "id") Integer id) {
        uiModel.addAttribute("products", productService.getProduct(id));
        return "index";
    }

    @PostMapping("/del")
    public String deleteProduct(@RequestParam Integer id) {
        productService.remove(id);
        return "index";
    }
}
