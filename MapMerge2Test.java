package br.com.components.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by luismoro on 07/06/17.
 */
public class MapMerge2Test {

    private MapMerge2 mapMerge;

    private Map mapResult;
    private Map mapInsidePart1;
    private Map mapInsidePart2;
    private Map mapInsidePart3;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        mapMerge = new MapMerge2();
        objectMapper = new ObjectMapper();

        mapInsidePart1 = new HashMap();
        mapInsidePart1.put("name1", "value1");
        mapInsidePart1.put("name2", "value2");
        mapInsidePart1.put("name3", "value3");
        mapInsidePart1.put("name4", "value4");

        mapInsidePart2 = new HashMap();
        mapInsidePart2.put("name1", "value5");
        mapInsidePart2.put("name2", "value6");
        mapInsidePart2.put("name3", "value7");
        mapInsidePart2.put("name4", "value8");

        mapInsidePart3 = new HashMap();
        mapInsidePart3.put("name1", "value9");
        mapInsidePart3.put("name2", "value10");
        mapInsidePart3.put("name3", "value11");
        mapInsidePart3.put("name4", "value12");

        mapResult = new HashMap();
        mapResult.put("name1", mapInsidePart1);
        mapResult.put("name2", mapInsidePart2);
        mapResult.put("name3", mapInsidePart3);

    }

    public void printMaps(Map map1) throws JsonProcessingException {
        System.out.println("Map = " + objectMapper.writeValueAsString(map1));
    }

    @Test
    public void mergeMapMap1Empty() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        map2.put("name1", mapInsidePart1);
        map2.put("name2", mapInsidePart2);
        map2.put("name3", mapInsidePart3);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged), false);
    }

    private void printMaps(String s) throws JsonProcessingException {
        System.out.println(s);
    }

    @Test
    public void mergeMapMap2Empty() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        map1.put("name1", mapInsidePart1);
        map1.put("name2", mapInsidePart2);
        map1.put("name3", mapInsidePart3);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged), false);
    }

    @Test
    public void mergeMapMap1Bigest() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("name1", "value1");
        mapInsideMap1.put("name2", "value2");
        mapInsideMap1.put("name3", "value3");
        mapInsideMap2.put("name4", "value4");
        map1.put("name1", mapInsideMap1);
        map2.put("name1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("name1", "value5");
        mapInsideMap1.put("name2", "value6");
        mapInsideMap1.put("name3", "value7");
        mapInsideMap1.put("name4", "value8");
        map1.put("name2", mapInsideMap1);
        map2.put("name2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap1.put("name1", "value9");
        mapInsideMap2.put("name2", "value10");
        mapInsideMap1.put("name3", "value11");
        mapInsideMap1.put("name4", "value12");
        map1.put("name3", mapInsideMap1);
        map2.put("name3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);


//        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged), false);
        assertThat(mapMerged, equalTo(mapResult));
    }

    @Test
    public void mergeMapMap2Bigest() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("name1", "value1");
        mapInsideMap2.put("name2", "value2");
        mapInsideMap2.put("name3", "value3");
        mapInsideMap2.put("name4", "value4");
        map1.put("name1", mapInsideMap1);
        map2.put("name1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("name1", "value5");
        mapInsideMap1.put("name2", "value6");
        mapInsideMap2.put("name3", "value7");
        mapInsideMap2.put("name4", "value8");
        map1.put("name2", mapInsideMap1);
        map2.put("name2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("name1", "value9");
        mapInsideMap2.put("name2", "value10");
        mapInsideMap2.put("name3", "value11");
        mapInsideMap1.put("name4", "value12");
        map1.put("name3", mapInsideMap1);
        map2.put("name3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);
        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged), false);
    }

    @Test
    public void mergeMapEqualsSize() throws Exception {
        Map map1 = new HashMap();
        Map map2 = new HashMap();

        Map mapInsideMap1 = new HashMap();
        Map mapInsideMap2 = new HashMap();
        mapInsideMap1.put("name1", "value1");
        mapInsideMap1.put("name2", "value2");
        mapInsideMap2.put("name3", "value3");
        mapInsideMap2.put("name4", "value4");
        map1.put("name1", mapInsideMap1);
        map2.put("name1", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap2.put("name1", "value5");
        mapInsideMap2.put("name2", "value6");
        mapInsideMap1.put("name3", "value7");
        mapInsideMap1.put("name4", "value8");
        map1.put("name2", mapInsideMap1);
        map2.put("name2", mapInsideMap2);

        mapInsideMap1 = new HashMap();
        mapInsideMap2 = new HashMap();
        mapInsideMap1.put("name1", "value9");
        mapInsideMap2.put("name2", "value10");
        mapInsideMap1.put("name3", "value11");
        mapInsideMap2.put("name4", "value12");
        map1.put("name3", mapInsideMap1);
        map2.put("name3", mapInsideMap2);

        printMaps(map1);
        printMaps(map2);

        Map mapMerged = mapMerge.mergeMap(map1, map2);

        printMaps(mapMerged);

        JSONAssert.assertEquals((objectMapper.writeValueAsString(mapResult)), objectMapper.writeValueAsString(mapMerged), false);
    }

    @Test
    public void shouldMergeObjectsInsideObjects() {
        // given
        final Map<String, Object> left = ImmutableMap.<String, Object>builder().
                put("myObject", ImmutableMap.<String, Object>builder().
                        put("name1", "value1").
                        put("name2", "value2").
                        build()
                ).
                build();
        final Map<String, Object> right = ImmutableMap.<String, Object>builder().
                put("myObject", ImmutableMap.<String, Object>builder().
                        put("name3", "value3").
                        put("name4", "value4").
                        build()
                ).
                build();
        final Map<String, Object> expected = ImmutableMap.<String, Object>builder().
                put("myObject", ImmutableMap.<String, Object>builder().
                        put("name1", "value1").
                        put("name2", "value2").
                        put("name3", "value3").
                        put("name4", "value4").
                        build()
                ).
                build();

        // when
        final Map<String, Object> result = mapMerge.mergeMap(new HashMap<String, Object>(left), new HashMap<String, Object>(right));

        // when
        assertThat(expected, equalTo(result));
    }

    @Test
    public void shouldMergeListsInsideLists() {
        // given
        final Map<String, Object> left = ImmutableMap.<String, Object>builder().
                put("myList", ImmutableSet.builder().
                        add("value1").
                        add("value2").
                        build()
                ).
                build();
        final Map<String, Object> right = ImmutableMap.<String, Object>builder().
                put("myList", ImmutableSet.builder().
                        add("value3").
                        add("value4").
                        build()
                ).
                build();
        final Map<String, Object> expected = ImmutableMap.<String, Object>builder().
                put("myList", ImmutableSet.builder().
                        add("value1").
                        add("value2").
                        add("value3").
                        add("value4").
                        build()
                ).
                build();

        // when
        final Map<String, Object> result = mapMerge.mergeMap(new HashMap<String, Object>(left), new HashMap<String, Object>(right));

        // when
        assertThat(result, equalTo(expected));
    }

    @Test
    public void shouldMergeObjectsInsideObjectsWithSameItem() {
        // given
        final Map<String, Object> left = ImmutableMap.<String, Object>builder().
                put("myObject", new HashMap<String, Object>(ImmutableMap.<String, Object>builder().
                        put("name1", "value1").
                        put("name2", "value2").
                        put("name3", "value3").
                        put("name4", "old value").
                        build())
                ).
                build();
        final Map<String, Object> right = ImmutableMap.<String, Object>builder().
                put("myObject", new HashMap<String, Object>(ImmutableMap.<String, Object>builder().
                        put("name2", "new value").
                        put("name3", "value3").
                        put("name5", "value5").
                        build())
                ).
                build();
        final Map<String, Object> expected = ImmutableMap.<String, Object>builder().
                put("myObject", new HashMap<String, Object>(ImmutableMap.<String, Object>builder().
                        put("name1", "value1").
                        put("name2", "new value").
                        put("name3", "value3").
                        put("name4", "old value").
                        put("name5", "value5").
                        build())
                ).
                build();

        // when
        final Map<String, Object> result = mapMerge.mergeMap(new HashMap<String, Object>(left), new HashMap<String, Object>(right));

        // when
        assertThat(expected, equalTo(result));
    }

}
