package com.galvanize.backend;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository <Supplier, Long>{
    @Query(value = "SELECT * FROM purchases WHERE suppliers_id = :id", nativeQuery = true)

    Long findIfSupplierIsInPurchases(long id);



}
