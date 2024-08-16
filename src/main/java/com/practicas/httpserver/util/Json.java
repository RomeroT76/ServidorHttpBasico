package com.practicas.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json 
{
    private static ObjectMapper oMapper = defaulObjectMapper();

    private static ObjectMapper defaulObjectMapper() 
    {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonSrc) throws JsonMappingException, JsonProcessingException, IOException
    {
        return oMapper.readTree(jsonSrc);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException, IllegalArgumentException
    {
        return oMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object obj) 
    {
        return oMapper.valueToTree(obj);
    }

    private static String generateJson(Object obj, boolean formated) throws JsonProcessingException
    {
        ObjectWriter ow = oMapper.writer();
        if (formated) {
            ow = ow.with(SerializationFeature.INDENT_OUTPUT);
        }
        return ow.writeValueAsString(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException
    {
        return generateJson(node, false);
    }

    public static String stringifyFormatted(JsonNode node) throws JsonProcessingException
    {
        return generateJson(node, true);
    }
}
