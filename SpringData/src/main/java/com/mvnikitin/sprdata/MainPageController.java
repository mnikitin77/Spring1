package com.mvnikitin.sprdata;

import com.mvnikitin.sprdata.entities.Product;
import com.mvnikitin.sprdata.services.ProductService;

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

    @GetMapping
    public String displayFilteredPage(Model uiModel,
                                      @RequestParam(value = "minPrice")
                                              Optional<Double> minPrice,
                                      @RequestParam(value = "maxPrice")
                                              Optional<Double> maxPrice,
                                      @RequestParam(value = "page",
                                              defaultValue = "1")
                                              Optional<Integer> page,
                                      @RequestParam(value = "rows",
                                              defaultValue = "5")
                                              Optional<Integer> rows) {
        Page<Product> resultPage = productService.findByPage(
                minPrice, maxPrice, page, rows);

        uiModel.addAttribute("pagecontent", resultPage);
        uiModel.addAttribute("itemscount", rows.get());
        uiModel.addAttribute("minprice",
                minPrice.isPresent() ? minPrice.get().intValue() : null);
        uiModel.addAttribute("maxprice",
                maxPrice.isPresent() ? maxPrice.get().intValue() : null);

        return "index";
    }

    @GetMapping("/{id}")
    public String displayProduct(Model uiModel,
                                 @PathVariable(value = "id") Optional <Long> id) {
        uiModel.addAttribute("pagecontent", productService.findById(id));
        return "index";
    }

    @GetMapping("/change")
    public String displayFormForChange(
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
