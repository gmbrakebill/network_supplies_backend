package com.galvanize.backend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
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

}
