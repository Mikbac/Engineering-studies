package com.Controllers;

import com.Entities.ItemInMagazine;
import com.Repositories.ItemInMagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/itemInMagazine-management/")
public class ItemInMagazineController {

    private ItemInMagazineRepository itemInMagazineRepository;

    @Autowired
    public ItemInMagazineController(ItemInMagazineRepository itemInMagazineRepository) {
        this.itemInMagazineRepository = itemInMagazineRepository;
    }

    @RequestMapping(value = "/itemsInMagazine", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ItemInMagazine> allItemsInMagazine() {
        return itemInMagazineRepository.findAll();
    }

    @RequestMapping(value = "/itemInMagazine/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemInMagazine oneItemsInMagazine(@PathVariable("id") int id) {
        if (id <= itemInMagazineRepository.count() && id > 0) {
            return itemInMagazineRepository.findOne(id);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/itemInMagazine", method = RequestMethod.POST)
    public ResponseEntity<ItemInMagazine> addItemsInMagazine(@RequestBody @Valid @NotNull ItemInMagazine itemInMagazine) {
        itemInMagazineRepository.save(itemInMagazine);
        return ResponseEntity.ok().body(itemInMagazine);
    }

    @RequestMapping(value = "/itemInMagazine/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editItemsInMagazine(@RequestBody @Valid @NotNull ItemInMagazine itemInMagazine, @PathVariable("id") int id) {
        if (itemInMagazineRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            itemInMagazine.setItemInMagazineId(id);
            itemInMagazineRepository.save(itemInMagazine);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/itemInMagazine/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteItemsInMagazine(@PathVariable("id") int id) {
        itemInMagazineRepository.delete(id);
        return new RedirectView("/itemsInMagazine", true);
    }

    @RequestMapping(value = "/itemInMagazine/amount", method = RequestMethod.GET)
    public Integer getItemsInMagazineSize() {
        return itemInMagazineRepository.countItemsInMagazineById();
    }

}