package com.Services;

import com.Entities.Item;
import com.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemServicempl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Iterable<Item> listAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemsById(Integer id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item saveItems(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteItems(Integer id) {
        itemRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        return itemRepository.checkIfExist(id) > 0;
    }

    @Override
    public Integer getNumberOfItems() {
        return itemRepository.countItemsById();
    }

}
