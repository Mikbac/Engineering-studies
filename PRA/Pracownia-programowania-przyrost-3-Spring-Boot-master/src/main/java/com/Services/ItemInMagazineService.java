package com.Services;

import com.Entities.ItemInMagazine;

public interface ItemInMagazineService {

    Iterable<ItemInMagazine> listAllItemsInMagazine();

    ItemInMagazine getItemsInMagazineById(Integer id);

    ItemInMagazine saveItemsInMagazine(ItemInMagazine itemInMagazine);

    void deleteItemsInMagazine(Integer id);

    Boolean checkIfExist(Integer id);

    Integer getNumberOfItemsInMagazine();
}
