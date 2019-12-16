package com.jadedtdt.artificer.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jadedtdt.artificer.jto.MaterialEntity;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialDAOImpl implements MaterialDAO {

    private static final Logger LOGGER = LogManager.getLogger(MaterialDAOImpl.class);

    @Autowired
    DynamoDBMapper ddbMapper;

    @Autowired
    ObjectMapper objectMapper;

    private final String JSON_PATH_EXPRESSION_MATERIALS = "$.MATERIALS[*]";
    private final String JSON_PATH_EXPRESSION_ITEM_TYPE = "$.[?(@.ITEM_TYPE=='%s')].MATERIALS";
    private final String JSON_PATH_EXPRESSION_TITLE = "$.MATERIALS[?(@.TITLE=='%s')]";

    @Override
    public List<MaterialEntity.Material> getMaterials() {
        PaginatedScanList<MaterialEntity> results = ddbMapper.scan(MaterialEntity.class, new DynamoDBScanExpression());
        List<MaterialEntity.Material> listMaterials = new ArrayList<>();

        for (MaterialEntity eachMaterialEntity : results) {
            try {
                String json = objectMapper.writeValueAsString(eachMaterialEntity);
                LOGGER.info(String.format("MaterialDAOImpl#getMaterials Jackson json: json[%s]", json));
                String jsonParsed = ((JSONArray)JsonPath.read(json, String.format(JSON_PATH_EXPRESSION_MATERIALS))).toJSONString();
                List<MaterialEntity.Material> listMaterialsParsed = objectMapper.readValue(jsonParsed, new TypeReference<List<MaterialEntity.Material>>() {});
                listMaterialsParsed.forEach(eachMaterial -> eachMaterial.setItemType(eachMaterialEntity.getItemType()));
                if (listMaterialsParsed == null || listMaterialsParsed.size() == 0) {
                    LOGGER.warn(String.format("MaterialDAOImpl#getMaterials JSONPath results is null or size 0: jsonPath[%s]", JSON_PATH_EXPRESSION_MATERIALS));
                    continue;
                } else {
                    LOGGER.info(String.format("MaterialDAOImpl#getMaterials JSONPath results nonzero: size[%s]", listMaterialsParsed.size()));
                    listMaterials.addAll(listMaterialsParsed);
                }
            } catch (JsonProcessingException e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterials JsonProcessingException when processing a MaterialEntity: e[%s]", e));
                continue;
            } catch (IOException e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterials IOException when processing a MaterialEntity: e[%s]", e));
                continue;
            }
        }
        return listMaterials;
    }

    @Override
    public List<MaterialEntity.Material> getMaterialsByItemType(String itemType) {
        PaginatedScanList<MaterialEntity> results = ddbMapper.scan(MaterialEntity.class, new DynamoDBScanExpression());

        for (MaterialEntity eachMaterialEntity : results) {
            try {
                String json = objectMapper.writeValueAsString(eachMaterialEntity);
                List<MaterialEntity.Material> listMaterials = JsonPath.read(json, String.format(JSON_PATH_EXPRESSION_ITEM_TYPE));
                if (listMaterials == null || listMaterials.size() == 0) {
                    LOGGER.warn(String.format("MaterialDAOImpl#getMaterials JSONPath results is null or size 0: itemType[%s]", itemType));
                    return null;
                } else {
                    LOGGER.info(String.format("MaterialDAOImpl#getMaterials JSONPath results nonzero: size[%s]", listMaterials.size()));
                    return listMaterials;
                }
            } catch (JsonProcessingException e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterials JSON exception when processing a MaterialEntity: e[%s]", e));
                continue;
            } catch (Exception e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterials Generic exception when processing a MaterialEntity: e[%s]", e));
            }
        }
        LOGGER.warn(String.format("MaterialDAOImpl#getMaterials No results found"));
        return null;
    }

    @Override
    public MaterialEntity.Material getMaterialByName(String name) {

        PaginatedScanList<MaterialEntity> results = ddbMapper.scan(MaterialEntity.class, new DynamoDBScanExpression());

        for (MaterialEntity eachMaterialEntity : results) {
            try {
                String json = objectMapper.writeValueAsString(eachMaterialEntity);
                String resultJson = JsonPath.read(json, String.format(JSON_PATH_EXPRESSION_TITLE, name));
                MaterialEntity.Material material = objectMapper.readValue(resultJson, MaterialEntity.Material.class);
                if (material != null) {
                    LOGGER.info(String.format("MaterialDAOImpl#getMaterialByName Result found for name: name[%s], material[%s]", name, material.toString()));
                    return material;
                }
            } catch (JsonProcessingException e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterialByName JSONException when processing a MaterialEntity: e[%s]", e));
            } catch (IOException e) {
                LOGGER.error(String.format("MaterialDAOImpl#getMaterialByName IOException when processing a MaterialEntity: e[%s]", e));
            }
        }
        LOGGER.warn(String.format("MaterialDAOImpl#getMaterialByName No results found for name: name[%s]", name));
        return null;
    }

}
