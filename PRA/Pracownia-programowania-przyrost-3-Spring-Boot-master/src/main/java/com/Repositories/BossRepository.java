package com.Repositories;

import com.Entities.Boss;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BossRepository extends CrudRepository<Boss, Integer>, PagingAndSortingRepository<Boss, Integer> {

    Boss findByBossId(Integer bossId);

    @Query("select count(*) from Boss p where p.bossId = ?1")
    Integer checkIfExist(Integer bossId);

    @Query("select count(*) from Boss")
    Integer countBossesById();

}
