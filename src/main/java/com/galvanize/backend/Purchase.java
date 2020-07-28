package com.galvanize.backend;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "suppliers_id", nullable = false)
    private Supplier supplier;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long numberReceived;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date purchasedDate;
    private Long startingInventory;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long inventoryShipped;
    private Long onHandInventory;

    private int minRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getNumberReceived() {
        return numberReceived;
    }

    public void setNumberReceived(Long numberReceived) {
        this.numberReceived = numberReceived;
        this.onHandInventory += numberReceived;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Long getStartingInventory() {
        return startingInventory;
    }

    public void setStartingInventory(Long startingInventory) {
        this.onHandInventory = startingInventory;
        this.startingInventory = startingInventory;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long getInventoryShipped() {
        return inventoryShipped;
    }


    public Long getOnHandInventory() {
        return onHandInventory;
    }


    public int getMinRequired() {
        return minRequired;
    }

    public void setMinRequired(int minRequired) {
        this.minRequired = minRequired;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long setShipInventory(Long n)
    {
        Long shipped = this.inventoryShipped + n;
        this.inventoryShipped = shipped;
        Long onHand = this.onHandInventory-n;
        this.onHandInventory = onHand;

        return this.inventoryShipped;

    }
    public Long getShipInventory()
    {
        return this.inventoryShipped;
    }
    public void setInventoryShipped(Long inventoryShipped) {
        this.inventoryShipped = inventoryShipped;
    }
}