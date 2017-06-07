package br.com.components.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luismoro on 07/06/17.
 */
public class MapMergeTest {

    private MapMerge mapMerge;

    private Map mapResult;
    private Map mapInsidePart1;
    private Map mapInsidePart2;
    private Map mapInsidePart3;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        mapMerge = new MapMerge();
        objectMapper = new ObjectMapper();

        mapInsidePart1 = new HashMap();
        mapInsidePart1.put("1", "inside1");
        mapInsidePart1.put("2", "inside2");
        mapInsidePart1.put("3", "inside3");
        mapInsidePart1.put("4", "inside4");

        mapInsidePart2 = new HashMap();
        mapInsidePart2.put("1", "inside5");
        mapInsidePart2.put("2", "inside6");
        mapInsidePart2.put("3", "inside7");
        mapInsidePart2.put("4", "inside8");

        mapInsidePart3 = new HashMap();
        mapInsidePart3.put("1", "inside9");
        mapInsidePart3.put("2", "inside10");
        mapInsidePart3.put("3", "inside11");
        mapInsidePart3.put("4", "inside12");

        mapResult = new HashMap();
        mapResult.put("1", mapInsidePart1);
        mapResult.put("2", mapInsidePart2);
        mapResult.put("3", mapInsidePart3);

    }

    public void printMaps (Map map1) throws JsonProcessingException {
        System.out.println("Map = " + objectMapper.writeValueAsString(map1));
    }

    @Test
    public void mergeMapMap1Empty() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        map2.put("1", mapInsidePart1);
        map2.put("2", mapInsidePart2);
        map2.put("3", mapInsidePart3);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged),false);
    }

    private void printMaps(String s) throws JsonProcessingException {
        System.out.println(s);
    }

    @Test
    public void mergeMapMap2Empty() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        map1.put("1", mapInsidePart1);
        map1.put("2", mapInsidePart2);
        map1.put("3", mapInsidePart3);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged),false);
    }

    @Test
    public void mergeMapMap1Bigest() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("1", "inside1");
        mapInsideMap1.put("2", "inside2");
        mapInsideMap1.put("3", "inside3");
        mapInsideMap2.put("4", "inside4");
        map1.put("1", mapInsideMap1);
        map2.put("1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("1", "inside5");
        mapInsideMap1.put("2", "inside6");
        mapInsideMap1.put("3", "inside7");
        mapInsideMap1.put("4", "inside8");
        map1.put("2", mapInsideMap1);
        map2.put("2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap1.put("1", "inside9");
        mapInsideMap2.put("2", "inside10");
        mapInsideMap1.put("3", "inside11");
        mapInsideMap1.put("4", "inside12");
        map1.put("3", mapInsideMap1);
        map2.put("3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged),false);
    }

    @Test
    public void mergeMapMap2Bigest() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("1", "inside1");
        mapInsideMap2.put("2", "inside2");
        mapInsideMap2.put("3", "inside3");
        mapInsideMap2.put("4", "inside4");
        map1.put("1", mapInsideMap1);
        map2.put("1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("1", "inside5");
        mapInsideMap1.put("2", "inside6");
        mapInsideMap2.put("3", "inside7");
        mapInsideMap2.put("4", "inside8");
        map1.put("2", mapInsideMap1);
        map2.put("2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("1", "inside9");
        mapInsideMap2.put("2", "inside10");
        mapInsideMap2.put("3", "inside11");
        mapInsideMap1.put("4", "inside12");
        map1.put("3", mapInsideMap1);
        map2.put("3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);
        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged),false);
    }

    @Test
    public void mergeMapEqualsSize() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("1", "inside1");
        mapInsideMap1.put("2", "inside2");
        mapInsideMap2.put("3", "inside3");
        mapInsideMap2.put("4", "inside4");
        map1.put("1", mapInsideMap1);
        map2.put("1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("1", "inside5");
        mapInsideMap2.put("2", "inside6");
        mapInsideMap1.put("3", "inside7");
        mapInsideMap1.put("4", "inside8");
        map1.put("2", mapInsideMap1);
        map2.put("2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap1.put("1", "inside9");
        mapInsideMap2.put("2", "inside10");
        mapInsideMap1.put("3", "inside11");
        mapInsideMap2.put("4", "inside12");
        map1.put("3", mapInsideMap1);
        map2.put("3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged),false);
    }
}