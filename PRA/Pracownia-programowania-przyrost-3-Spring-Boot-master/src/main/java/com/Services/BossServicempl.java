package com.Services;

import com.Entities.Boss;
import com.Repositories.BossRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BossServicempl implements BossService {


    @Autowired
    private BossRepository bossRepository;

    @Override
    public Iterable<Boss> listAllBosses() {
        return bossRepository.findAll();
    }

    @Override
    public Boss getBossesById(Integer id) {
        return bossRepository.findOne(id);
    }

    @Override
    public Boss saveBosses(Boss boss) {
        return bossRepository.save(boss);
    }

    @Override
    public void deleteBosses(Integer id) {
        bossRepository.delete(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        return bossRepository.checkIfExist(id) > 0;
    }

    @Override
    public Integer getNumberOfBosses() {
        return bossRepository.countBossesById();
    }

}
