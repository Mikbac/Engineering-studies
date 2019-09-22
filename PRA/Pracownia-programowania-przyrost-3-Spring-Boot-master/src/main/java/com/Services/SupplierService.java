package com.Services;

import com.Entities.Supplier;

public interface SupplierService {


    Iterable<Supplier> listAllSuppliers();

    Supplier getSuppliersById(Integer id);

    Supplier saveSuppliers(Supplier supplier);

    void deleteSuppliers(Integer id);

    Boolean checkIfExist(Integer id);

    Integer getNumberOfSuppliers();

}
