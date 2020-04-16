package com.mvnikitin.sprdata;

import com.mvnikitin.sprdata.entities.Product;
import com.mvnikitin.sprdata.services.PageInfo;
import com.mvnikitin.sprdata.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
                                      @RequestParam(value = "minPrice",
                                      required = false) Integer minPrice,
                                      @RequestParam(value = "maxPrice",
                                      required = false) Integer maxPrice,
                                      @RequestParam(value = "page",
                                      required = false) Integer page,
                                      @RequestParam(value = "rows",
                                      required = false,
                                      defaultValue = "5") Integer rows) {
        double minBorder = minPrice != null ?
                minPrice.doubleValue() : Double.MIN_VALUE;
        double maxBorder = maxPrice != null ?
                maxPrice.doubleValue() : Double.MAX_VALUE;

        int pageNumber = page != null ? page - 1 : 0;
        int rowsPerPage = rows != null ? rows :
                (int) productService.countByPriceBetween(minBorder, maxBorder);

        PageInfo pageInfo;

        if (minBorder == Double.MIN_VALUE && maxBorder == Double.MAX_VALUE) {
            pageInfo = productService.findByPage(pageNumber, rowsPerPage);
        } else {
            pageInfo = productService.findByPage(
                    pageNumber, rowsPerPage, minBorder, maxBorder);
        }

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= pageInfo.getTotalPages(); i++) {
            pages.add(i);
        }

        uiModel.addAttribute("products", pageInfo.getPageContent());
        uiModel.addAttribute("pages", pages);
        uiModel.addAttribute("currpage", pageNumber);
        uiModel.addAttribute("itemscount", rowsPerPage);
        uiModel.addAttribute("minprice", minPrice);
        uiModel.addAttribute("maxprice", maxPrice);

        return "index";
    }

    @GetMapping("/{id}")
    public String displayProduct(Model uiModel, @PathVariable(value = "id") Long id) {
        uiModel.addAttribute("products", productService.getById(id));
        return "index";
    }

    @PostMapping("/del")
    public String deleteProduct(@RequestParam Long id) {
        productService.remove(id);
        return "index";
    }
}
