package com.c2.hospital.equipmentservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_EQUIPMENT")
@Getter
@Setter
@ToString
public class EquipmentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idType;
    private int providerId;
    private int quantity;
    private int disponibility;
}
