package br.com.components.map;

import java.util.*;

/**
 * Created by luismoro on 06/06/17.
 */
public class MapMerge2 {

    public Map<String, Object> mergeMap(final Map<String, Object> left, final Map<String, Object> right) {
        Map<String, Object> result = new HashMap<String, Object>();

        for (Map.Entry<String, Object> itemLeft : left.entrySet()) {
            Object mergedItem;

            if (right.containsKey(itemLeft.getKey())) {
                if (itemLeft.getValue() instanceof Map) {
                    mergedItem = mergeMap((Map<String, Object>) itemLeft.getValue(), (Map<String, Object>) right.get(itemLeft.getKey()));
                } else if (itemLeft.getValue() instanceof Collection) {
                    final Set<Object> value = new LinkedHashSet<Object>((Collection<?>) itemLeft.getValue());
                    value.addAll((Collection<?>) right.get(itemLeft.getKey()));
                    mergedItem = value;
                } else {
                    mergedItem = right.get(itemLeft.getKey());
                }
                right.remove(itemLeft.getKey());

            } else {
                mergedItem = itemLeft.getValue();
            }
            result.put(itemLeft.getKey(), mergedItem);
        }
        result.putAll(right);
        return result;
    }

}
