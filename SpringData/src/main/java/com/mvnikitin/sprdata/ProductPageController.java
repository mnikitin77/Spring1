package com.mvnikitin.sprdata;


//import com.mvnikitin.mvcexamp.model.ProductService;
import com.mvnikitin.sprdata.entities.Product;
import com.mvnikitin.sprdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
