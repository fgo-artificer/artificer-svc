package com.jadedtdt.artificer.jto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName="artificer-materials")
public class MaterialEntity {

    @DynamoDBHashKey(attributeName = "ITEM_TYPE")
    @JsonProperty("ITEM_TYPE")
    String itemType;
    @DynamoDBAttribute(attributeName = "MATERIALS")
    @JsonProperty("MATERIALS")
    List<MaterialEntity.Material> listMaterials;

    public MaterialEntity() {
        this.itemType = null;
        this.listMaterials = null;
    }

    public MaterialEntity(String itemType, List<MaterialEntity.Material> listMaterials) {
        this.itemType = itemType;
        this.listMaterials = listMaterials;
    }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public List<MaterialEntity.Material> getListMaterials() { return listMaterials; }
    public void setListMaterials(List<MaterialEntity.Material> listMaterials) { this.listMaterials = listMaterials; }

    @DynamoDBDocument
    public static class Material {

        @DynamoDBAttribute(attributeName = "IMAGE_URL")
        @JsonProperty("IMAGE_URL")
        private String imageUrl;
        //No DynamoDBAttribute because we are pretty much flattening the MaterialEntity
        @JsonProperty("ITEM_TYPE")
        private String itemType;
        @DynamoDBAttribute(attributeName = "POSITION")
        @JsonProperty("POSITION")
        private String position;
        @DynamoDBAttribute(attributeName = "TITLE")
        @JsonProperty("TITLE")
        private String title;

        public Material() {
            this.imageUrl = null;
            this.itemType = null;
            this.position = null;
            this.title = null;
        }

        public Material(String imageUrl, String itemType, String position, String title) {
//        public Material(String imageUrl, String position, String title) {
            this.imageUrl = imageUrl;
            this.itemType = itemType;
            this.position = position;
            this.title = title;
        }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

        public String getItemType() { return itemType; }
        @JsonProperty("ITEM_TYPE")
        public void setItemType(String itemType) { this.itemType = itemType; }

        public String getPosition() { return position; }
        public void setPosition(String position) { this.position = position; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

    }
}
