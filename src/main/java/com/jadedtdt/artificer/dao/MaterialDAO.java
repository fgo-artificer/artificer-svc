package com.jadedtdt.artificer.dao;

import com.jadedtdt.artificer.jto.MaterialEntity;

import java.util.List;

public interface MaterialDAO {

    List<MaterialEntity.Material> getMaterials();
    List<MaterialEntity.Material> getMaterialsByItemType(String itemType);
    MaterialEntity.Material getMaterialByName(String name);

}
