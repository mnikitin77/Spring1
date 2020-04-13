package com.mvnikitin.hiberexamp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchases")
@NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p")
@NamedQuery(name = "Purchase.findByCustomer",
        query = "SELECT p FROM Purchase p WHERE p.customer = :customer")
public class Purchase {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name = "fk_customer_id"))
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "purchases_products",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            foreignKey = @ForeignKey(name = "fk_purchase_id")
    )
    private List<Product> proucts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id",
            foreignKey = @ForeignKey(name = "fk_purchases_details_id"))
    private PurchaseDetails details;

    public Purchase() {
        proucts = new ArrayList<>();
        details = new PurchaseDetails();
        details.setPurchased(new Date());
        details.setPurchase(this);
    }

    public void addProduct (Product product){
        proucts.add(product);
    }

    public void removeProduct (Product product){
        proucts.remove(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProucts() {
        return proucts;
    }

    public void setProucts(List<Product> proucts) {
        this.proucts = proucts;
    }

    public PurchaseDetails getDetails() {
        return details;
    }

    public void setDetails(PurchaseDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", details=" + details +
                ", proucts=" + proucts +
                '}';
    }
}
