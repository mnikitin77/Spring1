package com.mvnikitin.hiberexamp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchases_details")
public class PurchaseDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date purchased;

    @OneToOne(mappedBy = "details",
            cascade = CascadeType.ALL)
    Purchase purchase;

    public PurchaseDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchased() {
        return purchased;
    }

    public void setPurchased(Date purchased) {
        this.purchased = purchased;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "PurchaseDetails{" +
                "purchased=" + purchased +
                '}';
    }
}
