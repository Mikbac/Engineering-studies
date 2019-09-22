package com.Services;

import com.Entities.Boss;

public interface BossService {


    Iterable<Boss> listAllBosses();

    Boss getBossesById(Integer id);

    Boss saveBosses(Boss boss);

    void deleteBosses(Integer id);

    Boolean checkIfExist(Integer id);

    Integer getNumberOfBosses();

}
