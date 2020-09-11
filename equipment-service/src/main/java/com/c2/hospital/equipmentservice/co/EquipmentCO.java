package com.c2.hospital.equipmentservice.co;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCO
{
    private int id;
    private int idType;
    private int providerId;
    private int quantity;
    private int disponibility;

    private String providerName;
    private String providerPhone;
    private String providerEmail;

    private String typeName;
    private String typeDescription;
}
