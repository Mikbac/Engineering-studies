package com.Services;

import com.Entities.Supplier;
import com.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServicempl implements SupplierService {


    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Iterable<Supplier> listAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSuppliersById(Integer id) {
        return supplierRepository.findOne(id);
    }

    @Override
    public Supplier saveSuppliers(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSuppliers(Integer id) {
        supplierRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        return supplierRepository.checkIfExist(id) > 0;
    }

    @Override
    public Integer getNumberOfSuppliers() {
        return supplierRepository.countSuppliersById();
    }

}
