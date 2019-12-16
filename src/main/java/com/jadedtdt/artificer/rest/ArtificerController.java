package com.jadedtdt.artificer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jadedtdt.artificer.dao.MaterialDAOImpl;
import com.jadedtdt.artificer.jto.MaterialEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@RestController(ArtificerController.ROOT_URI)
@RestController
public class ArtificerController {

    private static final Logger LOGGER = LogManager.getLogger(ArtificerController.class);

    // Defining the base URI
//    public static final String PROJECT_NAME = "artificer";
//    public static final String VERSION = "v1";
//    public static final String ROOT_URI = PROJECT_NAME + '/' + VERSION;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MaterialDAOImpl materialDAOImpl;

    @GetMapping("/materials")
    public List<MaterialEntity.Material> getMaterialsByItemType(@RequestParam(required = false) String itemType) {
        LOGGER.info(String.format("ArtificerController#getMaterials Invoked"));
        LOGGER.info(String.format("ArtificerController#getMaterials Request params: itemType[%s]", itemType));
        try {
            if (Strings.isBlank(itemType)) {
                return materialDAOImpl.getMaterials();
            } else {
                return materialDAOImpl.getMaterialsByItemType(itemType);
            }
        } catch (Exception e) {
            LOGGER.info(String.format("ArtificerController#getMaterials Generic Exception: e[%s]", e.toString()));
        }
        return null;
    }

}
