package org.pmv.myspring.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.pmv.myspring.dto.RestauranteDTO;

import java.io.IOException;
import java.util.List;

public class RestaurantePageDeserializer extends JsonDeserializer<RestaurantePage> {

    @Override
    public RestaurantePage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        JsonNode contentNode = node.get("content");
        List<RestauranteDTO> content = mapper.readValue(contentNode.traverse(mapper), new TypeReference<List<RestauranteDTO>>() {});

        JsonNode pageableNode = node.get("pageable");
        Pageable pageable = PageRequest.of(
                pageableNode.get("pageNumber").asInt(),
                pageableNode.get("pageSize").asInt()
        );

        long total = node.get("totalElements").asLong();

        return new RestaurantePage(content, pageable, total);
    }
}