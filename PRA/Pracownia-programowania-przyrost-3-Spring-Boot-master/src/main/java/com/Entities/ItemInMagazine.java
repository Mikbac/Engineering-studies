package com.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "item_in_magazine_table")
public class ItemInMagazine {
    @Id
    @GeneratedValue
    @Column(name = "itemInMagazineId")
    private long itemInMagazineId;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private float amount;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(int day, int month, int year) {
        LocalDate pomoc = null;
        date = LocalDate.of(year, month, day);
    }

    /* ManyToOne */
    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemInMagazine() {
    }

    public long getItemInMagazineId() {
        return itemInMagazineId;
    }

    public void setItemInMagazineId(long itemInMagazineId) {
        this.itemInMagazineId = itemInMagazineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
