package com.mvnikitin.hiberexamp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String name;

    @OneToMany(mappedBy = "customer",
//            cascade = CascadeType.ALL)
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.DETACH})
    private List<Purchase> purchases;

    public Customer() {
        purchases = new ArrayList<>();
    }

    public Customer(String name) {
        this();
        this.name = name;
    }

    public void addPurchase(Purchase purchase) {
        purchase.setCustomer(this);
        purchases.add(purchase);
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
