package com.mvnikitin.boot;

import com.mvnikitin.boot.entity.Product;
import com.mvnikitin.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping
public class MainPageController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/login")
    public String onLogin() {
        return "login";
    }

    @GetMapping
    public String showProducts(Model uiModel,
                               @RequestParam(value = "minPrice")
                                              Optional<Double> minPrice,
                               @RequestParam(value = "maxPrice")
                                              Optional<Double> maxPrice,
                               @RequestParam(value = "page",
                                              defaultValue = "1")
                                              Optional<Integer> page,
                               @RequestParam(value = "rows",
                                              defaultValue = "5")
                                              Optional<Integer> rows,
                               @RequestParam(value = "sortby",
                                              defaultValue = "#")
                                              Optional<String> sortBy,
                               @RequestParam(value = "sortdir",
                                              defaultValue = "true")
                                              Optional<Boolean> sortDirection) {
        Page<Product> resultPage = productService.findByPage(
                minPrice, maxPrice, page, rows, sortBy, sortDirection);

        uiModel.addAttribute("pagecontent", resultPage);
        uiModel.addAttribute("itemscount", rows.get());
        uiModel.addAttribute("sortby", sortBy.orElse(null));
        uiModel.addAttribute("sortdir", sortDirection.orElse(null));

        return "index";
    }

    @GetMapping("/{id}")
    public String showProduct(Model uiModel,
                              @PathVariable(value = "id") Optional <Long> id) {
        uiModel.addAttribute("pagecontent", productService.findById(id));
        return "index";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.remove(id);
        return "redirect:/";
    }
}
