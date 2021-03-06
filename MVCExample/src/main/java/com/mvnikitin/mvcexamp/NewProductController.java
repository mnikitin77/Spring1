package com.mvnikitin.mvcexamp;

import com.mvnikitin.mvcexamp.model.Product;
import com.mvnikitin.mvcexamp.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class NewProductController {
    private com.mvnikitin.mvcexamp.model.ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String formProduct(Model uiModel) {
        uiModel.addAttribute("product", new com.mvnikitin.mvcexamp.model.Product());
        return "product";
    }

    @PostMapping
    public String newProduct(Product product) {
        productService.add(product);
        return "redirect:/";
    }
}
