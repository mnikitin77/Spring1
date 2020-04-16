package com.mvnikitin.sprdata.services;

import com.mvnikitin.sprdata.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageInfo {
    private Page<Product> currentPage;

    public PageInfo(Page<Product> currentPage) {
        this.currentPage = currentPage;
    }

    public List<Product> getPageContent() {
        return currentPage.getContent();
    }

    public int getTotalPages() {
        return currentPage.getTotalPages();
    }

    public long getTotalElements() {
        return  currentPage.getTotalElements();
    }

    public int getSize() {
        return currentPage.getSize();
    }

    public boolean hasNext() {
        return currentPage.hasNext();
    }

    public boolean hasPrevious() {
        return currentPage.hasPrevious();
    }
}
