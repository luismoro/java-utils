package br.com.components.map;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by luismoro on 06/06/17.
 */
public class MapMerge {

    public MapMerge() {
    }

    public Map mergeMap(Map<String, Object> map1, Map map2) {

        Iterator it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Map map2Node = (Map) map2.get(pairs.getKey());
            if (map2Node != null) {
                ((Map) map1.get(pairs.getKey())).putAll(map2Node);
                map2.remove(pairs.getKey());
            }
        }

        Iterator it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pairs = (Map.Entry)it2.next();
            map1.put(pairs.getKey().toString(), pairs.getValue());
        }

        return map1;
    }

}
