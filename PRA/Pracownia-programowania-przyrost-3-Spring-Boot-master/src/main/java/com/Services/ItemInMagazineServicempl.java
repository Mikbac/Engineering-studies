package com.Services;

import com.Entities.ItemInMagazine;
import com.Repositories.ItemInMagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemInMagazineServicempl implements ItemInMagazineService {


    @Autowired
    private ItemInMagazineRepository itemInMagazineRepository;

    @Override
    public Iterable<ItemInMagazine> listAllItemsInMagazine() {
        return itemInMagazineRepository.findAll();
    }

    @Override
    public ItemInMagazine getItemsInMagazineById(Integer id) {
        return itemInMagazineRepository.findOne(id);
    }

    @Override
    public ItemInMagazine saveItemsInMagazine(ItemInMagazine itemInMagazine) {
        return itemInMagazineRepository.save(itemInMagazine);
    }

    @Override
    public void deleteItemsInMagazine(Integer id) {
        itemInMagazineRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        return itemInMagazineRepository.checkIfExist(id) > 0;
    }

    @Override
    public Integer getNumberOfItemsInMagazine() {
        return itemInMagazineRepository.countItemsInMagazineById();
    }
}
