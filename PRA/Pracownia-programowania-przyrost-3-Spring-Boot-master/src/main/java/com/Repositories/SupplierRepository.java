package com.Repositories;

import com.Entities.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Integer>, PagingAndSortingRepository<Supplier, Integer> {

    Supplier findBySupplierId(Integer supplierId);

    @Query("select count(*) from Supplier p where p.supplierId = ?1")
    Integer checkIfExist(Integer supplierId);

    @Query("select count(*) from Supplier")
    Integer countSuppliersById();

}
