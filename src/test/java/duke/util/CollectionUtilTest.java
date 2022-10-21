package duke.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CollectionUtilTest {

    @Test
    void testIsCollectionEmpty() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        Assertions.assertFalse(CollectionUtil.isEmpty(list1));

        List<Integer> list2 = Arrays.asList(null, null, null);
        Assertions.assertFalse(CollectionUtil.isEmpty(list2));

        List<Integer> list3 = new ArrayList<>();
        Assertions.assertTrue(CollectionUtil.isEmpty(list3));

        List<Integer> list4 = null;
        Assertions.assertTrue(CollectionUtil.isEmpty(list4));
    }

    @Test
    void testIsMapEmpty() {
        Map<String, Integer> map1 = Map.of(
                "k1", 1,
                "k2", 2,
                "k3", 3
        );
        Assertions.assertFalse(CollectionUtil.isEmpty(map1));

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("k1", null);
        map2.put("k2", null);
        map2.put("k3", null);
        Assertions.assertFalse(CollectionUtil.isEmpty(map2));

        Map<String, Integer> map3 = new HashMap<>();
        Assertions.assertTrue(CollectionUtil.isEmpty(map3));

        Map<String, Integer> map4 = null;
        Assertions.assertTrue(CollectionUtil.isEmpty(map4));
    }
}
