package com.mvnikitin.boot;

import com.mvnikitin.boot.entities.Product;
import com.mvnikitin.boot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        uiModel.addAttribute("minprice",
                minPrice.isPresent() ? minPrice.get().intValue() : null);
        uiModel.addAttribute("maxprice",
                maxPrice.isPresent() ? maxPrice.get().intValue() : null);
        uiModel.addAttribute("sortby",
                sortBy.isPresent() ? sortBy.get() : null);
        uiModel.addAttribute("sortdir",
                sortDirection.isPresent() ? sortDirection.get() : null);


        return "index";
    }

    @GetMapping("/{id}")
    public String showProduct(Model uiModel,
                              @PathVariable(value = "id") Optional <Long> id) {
        uiModel.addAttribute("pagecontent", productService.findById(id));
        return "index";
    }

    @GetMapping("/edit")
    public String showProuctForm(
            Model uiModel, @RequestParam(value = "id") Optional<Long> id) {
        uiModel.addAttribute("product",
                productService.findById(id).getContent().get(0));
        uiModel.addAttribute("isupdate", true);
        return "product";
    }

    @GetMapping("/del")
    public String deleteProduct(@RequestParam(value = "id") Optional<Long> id) {
        if (id.isPresent()) {
            productService.remove(id.get());
        }
        return "redirect:/";
    }
}
