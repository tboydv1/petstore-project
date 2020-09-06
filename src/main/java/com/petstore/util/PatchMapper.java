package com.petstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.petstore.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatchMapper {

     static final ObjectMapper objectMapper = new ObjectMapper();

    public static Pet applyPatchToCustomer(JsonPatch patch, Pet targetPet) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPet, JsonNode.class));
        return objectMapper.treeToValue(patched, Pet.class);
    }

}
