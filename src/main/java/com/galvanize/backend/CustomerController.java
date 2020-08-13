package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;

    public CustomerController(CustomerRepository customerRepository, OrdersRepository ordersRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
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
        Optional order = this.ordersRepository.findById((long) id);
        System.out.println(order);
        if(order.isPresent()){
            return "Cannot delete customer";
        }else{
            this.customerRepository.deleteById((long) id);
            return "Customer has been deleted.";
        }

    }

//    @DeleteMapping("/delete-customer/{id}")
//    public String deleteCustomer(@PathVariable Long id)
//    {
//        if(this.ordersRepository.findById((long) 12) != null){
//            return "Cannot delete customer";
//        }else{
//            this.customerRepository.deleteById((long) 12);
//            return "Customer has been deleted.";
//        }
//
//    }


}
