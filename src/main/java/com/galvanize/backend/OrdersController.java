package com.galvanize.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

   private final OrdersRepository ordersRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all")
    public Iterable<Orders> getAll()
    {
        return this.ordersRepository.findAll();
    }
    @PostMapping("/add-order")
     public String addOrder(@RequestBody Orders orders)
    {
         this.ordersRepository.save(orders);
         return "order added.";
    }
    @DeleteMapping("/delete-order/{id}")
    public String deleteOrder(@PathVariable Long id)
    {
        this.ordersRepository.deleteById(id);
        return "deleted";
    }
}
