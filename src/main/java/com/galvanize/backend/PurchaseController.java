package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/all")
    public Iterable<Purchase> getAll()
    {
        return this.purchaseRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Purchase> getById(@PathVariable Long id)
    {
        return this.purchaseRepository.findById(id);
    }

    @PostMapping("/add-purchase")
    public Purchase addPurchase(@RequestBody Purchase purchase)
    {
        return this.purchaseRepository.save(purchase);
    }
    @PutMapping("/purchase-ship-inventory/{id}/{qtyToShip}")
    public String shipProduct( @PathVariable Long id, @PathVariable("qtyToShip") Long n)
    {
        Purchase shipProduct = this.purchaseRepository.findById(id).get();
        shipProduct.setInventoryShipped(n);
        shipProduct.getInventoryShipped();

        this.purchaseRepository.save(shipProduct);
        return"saved";
    }
    @PutMapping("/purchase-receive-inventory/{id}/{qtyToReceive}")
    public String receiveInventory(@PathVariable Long id, @PathVariable("qtyToReceive") Long n)
    {
        Purchase receiveProduct = this.purchaseRepository.findById(id).get();

        receiveProduct.setNumberReceived(n);
        receiveProduct.getNumberReceived();
         this.purchaseRepository.save(receiveProduct);
         return "saved";

    }
}
