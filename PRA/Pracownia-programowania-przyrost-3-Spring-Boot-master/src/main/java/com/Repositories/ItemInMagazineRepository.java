package com.Repositories;

import com.Entities.ItemInMagazine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemInMagazineRepository extends CrudRepository<ItemInMagazine, Integer>, PagingAndSortingRepository<ItemInMagazine, Integer> {

    ItemInMagazine findByItemInMagazineId(Integer itemInMagazineId);

    @Query("select count(*) from ItemInMagazine p where p.itemInMagazineId = ?1")
    Integer checkIfExist(Integer itemInMagazineId);

    @Query("select count(*) from ItemInMagazine")
    Integer countItemsInMagazineById();

}
