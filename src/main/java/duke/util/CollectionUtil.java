package duke.util;

import java.util.Collection;
import java.util.Map;

/**
 * Util class which used to process collection class.
 *
 * @author Dex
 * @date 2022/10/25
 */
public class CollectionUtil {

    private CollectionUtil() {}

    /**
     * Returns a boolean to check if the given collection is empty.
     *
     * @param collection: Collection instance.
     * @return Boolean value define the collection is empty.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * Returns a boolean to check if the given map is empty.
     *
     * @param map: Map instance.
     * @return Boolean value define the map is empty.
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }
}
