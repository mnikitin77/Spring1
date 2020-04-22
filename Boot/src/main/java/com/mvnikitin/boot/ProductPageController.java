package com.mvnikitin.boot;


//import com.mvnikitin.mvcexamp.model.ProductService;

import com.mvnikitin.boot.entities.Product;
import com.mvnikitin.boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductPageController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String displayFormForAdd(Model uiModel) {
        uiModel.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String newProduct(Product product) {
        productService.save(product);
        return "redirect:/";
    }
}
