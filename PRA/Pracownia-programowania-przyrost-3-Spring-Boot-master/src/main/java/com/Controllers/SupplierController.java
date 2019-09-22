package com.Controllers;

import com.Entities.Supplier;
import com.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/supplier-management/")
public class SupplierController {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @RequestMapping(value = "/suppliers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Supplier> allSuppliers() {
        return supplierRepository.findAll();
    }

    @RequestMapping(value = "/supplier", method = RequestMethod.POST)
    public ResponseEntity<Supplier> addSuppliers(@RequestBody @Valid @NotNull Supplier supplier) {
        supplierRepository.save(supplier);
        return ResponseEntity.ok().body(supplier);
    }

    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editSuppliers(@RequestBody @Valid @NotNull Supplier supplier, @PathVariable("id") int id) {
        if (supplierRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            supplier.setSupplierId(id);
            supplierRepository.save(supplier);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteSuppliers(@PathVariable("id") int id) {
        supplierRepository.delete(id);
        return new RedirectView("/suppliers", true);
    }

    @RequestMapping(value = "/supplier/amount", method = RequestMethod.GET)
    public Integer getSuppliersSize() {
        return supplierRepository.countSuppliersById();
    }

}