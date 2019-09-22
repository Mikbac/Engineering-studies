package com.Services;

import com.Entities.Item;

public interface ItemService {

    Iterable<Item> listAllItems();

    Item getItemsById(Integer id);

    Item saveItems(Item item);

    void deleteItems(Integer id);

    Boolean checkIfExist(Integer id);

    Integer getNumberOfItems();

}
