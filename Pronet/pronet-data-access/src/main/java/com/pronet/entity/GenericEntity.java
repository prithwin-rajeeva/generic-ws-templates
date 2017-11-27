package com.pronet.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by pr250155 on 5/21/17.
 */
@MappedSuperclass
@Data
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
}
