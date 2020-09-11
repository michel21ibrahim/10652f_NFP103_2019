package com.c2.hospital.equipmentservice.repository;

import com.c2.hospital.equipmentservice.model.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {

    @Query("SELECT u FROM EquipmentEntity u WHERE u.disponibility = ?1")
    List<EquipmentEntity> findAvailableEquipment(int i);
}
