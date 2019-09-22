package com.Controllers;

import com.Entities.Item;
import com.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/item-management/")
public class ItemController {

    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Item> allItems() {
        return itemRepository.findAll();
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Item oneItems(@PathVariable("id") int id) {
        if (id <= itemRepository.count() && id > 0) {
            return itemRepository.findOne(id);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<Item> addItems(@RequestBody @Valid @NotNull Item item) {
        itemRepository.save(item);
        return ResponseEntity.ok().body(item);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editItems(@RequestBody @Valid @NotNull Item item, @PathVariable("id") int id) {
        if (itemRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            item.setItemId(id);
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteItems(@PathVariable("id") int id) {
        itemRepository.delete(id);
        return new RedirectView("/items", true);
    }

    @RequestMapping(value = "/item/amount", method = RequestMethod.GET)
    public Integer getItemsSize() {
        return itemRepository.countItemsById();
    }

}