package com.mvnikitin.boot;

import com.mvnikitin.boot.entity.Product;
import com.mvnikitin.boot.service.ProductService;
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
    public String showProuctForm(Model uiModel) {
        uiModel.addAttribute("product", new Product());
        return "product";
    }

    @GetMapping("/{id}")
    public String showProuctFormForEdit(
            Model uiModel, @PathVariable(value = "id") Optional<Long> id) {
        uiModel.addAttribute("product",
                productService.findById(id).getContent().get(0));
        uiModel.addAttribute("isupdate", true);
        return "product";
    }

    @PostMapping
    public String addOrEditProduct(Product product) {
        productService.save(product);
        return "redirect:/";
    }
}
