package com.Controllers;

import com.Entities.Boss;
import com.Repositories.BossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/boss-management/")
public class BossController {

    private BossRepository bossRepository;

    @Autowired
    public BossController(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @RequestMapping(value = "/bosses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Boss> allBosses() {
        return bossRepository.findAll();
    }

    @RequestMapping(value = "/boss/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boss oneBosses(@PathVariable("id") int id) {
        if (id <= bossRepository.count() && id > 0) {
            return bossRepository.findOne(id);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/boss", method = RequestMethod.POST)
    public ResponseEntity<Boss> addBosses(@RequestBody @Valid @NotNull Boss boss) {
        bossRepository.save(boss);
        return ResponseEntity.ok().body(boss);
    }

    @RequestMapping(value = "/boss/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editBosses(@RequestBody @Valid @NotNull Boss boss, @PathVariable("id") int id) {
        if (bossRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            boss.setBossId(id);
            bossRepository.save(boss);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    @RequestMapping(value = "/boss/{id}", method = RequestMethod.DELETE)
    public RedirectView deleteBosses(@PathVariable("id") int id) {
        bossRepository.delete(id);
        return new RedirectView("/bosses", true);
    }

    @RequestMapping(value = "/boss/amount", method = RequestMethod.GET)
    public Integer getBossesSize() {
        return bossRepository.countBossesById();
    }

}
