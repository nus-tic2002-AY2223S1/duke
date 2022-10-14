package duke.util;

import java.util.Collection;
import java.util.Map;

public class CollectionUtil {

    private CollectionUtil() {}

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }
}
