package com.c2.hospital.equipmentservice.repository;

import com.c2.hospital.equipmentservice.model.EquipmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeEntity, Integer> {

}
