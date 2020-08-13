package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;
    private final PurchaseRepository purchaseRepository;

    public SupplierController(SupplierRepository supplierRepository, PurchaseRepository purchaseRepository)
    {
        this.supplierRepository = supplierRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping("/all")
    public Iterable<Supplier> allSuppliers()
    {
        return this.supplierRepository.findAll();
    }
    @PostMapping("/add-supplier")
    public Supplier addSupplier(@RequestBody Supplier supplier)
    {
        return this.supplierRepository.save(supplier);
    }
    @DeleteMapping("/delete-supplier/{id}")
    public String deleteById(@PathVariable Long id)
    {
        Optional purchase = this.purchaseRepository.findById((long) id);
        System.out.println(purchase);
        if(purchase.isPresent())
        {
            return "Cannot delete supplier, this supplier is attached to another purchase order";
        }else
        {
            this.supplierRepository.deleteById((long) id);
            return "Supplier has been deleted";
        }
    }


}
