package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/all")
    public Iterable<Customer> getAll()
    {
        return this.customerRepository.findAll();
    }


    @PostMapping("/add-customer")
    public Customer createCustomer(@RequestBody Customer customer)
    {
        return this.customerRepository.save(customer);

    }
    @DeleteMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable Long id)
    {
         this.customerRepository.deleteById(id);
         return "Customer has been deleted.";
    }

}
