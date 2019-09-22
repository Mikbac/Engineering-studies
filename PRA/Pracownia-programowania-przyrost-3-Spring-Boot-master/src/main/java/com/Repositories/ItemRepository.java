package com.Repositories;


import com.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ItemRepository extends CrudRepository<Item, Integer>, PagingAndSortingRepository<Item, Integer> {

    Item findByItemId(Integer ItemId);
    @Query("select count(*) from Item p where p.itemId = ?1")
    Integer checkIfExist(Integer itemId);


    @Query("select count(*) from Item")
    Integer countItemsById();

}
