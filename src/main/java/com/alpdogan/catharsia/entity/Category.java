package com.alpdogan.catharsia.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column
    private ECategory name;

    public Category() {

    }

    public Category(ECategory name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ECategory getName() {
        return name;
    }

    public void setName(ECategory name) {
        this.name = name;
    }


}
