package duke.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringUtilTest {

    @Test
    void testIsEmpty() {
        Assertions.assertTrue(StringUtil.isEmpty(null));
        Assertions.assertTrue(StringUtil.isEmpty(""));
        Assertions.assertFalse(StringUtil.isEmpty("       "));
        Assertions.assertFalse(StringUtil.isEmpty("   abcd   "));
    }

    @Test
    void testIsBlank() {
        Assertions.assertTrue(StringUtil.isBlank(null));
        Assertions.assertTrue(StringUtil.isBlank(""));
        Assertions.assertTrue(StringUtil.isBlank("       "));
        Assertions.assertFalse(StringUtil.isBlank("   abcd   "));
    }

    @Test
    void testTrim() {
        Assertions.assertEquals("abc", StringUtil.trim("    abc    "));
        Assertions.assertEquals("abc", StringUtil.trim("    abc"));
        Assertions.assertEquals("abc", StringUtil.trim("abc    "));
        Assertions.assertEquals("a b c", StringUtil.trim("   a b c    "));
        Assertions.assertEquals("a b c", StringUtil.trim("a b c"));
        Assertions.assertNotEquals("a b c", StringUtil.trim("   a   b   c    "));
        Assertions.assertEquals("", StringUtil.trim(null));
    }

    @Test
    void testListToString() {
        List<Integer> list1 = List.of(1, 2, 3);
        Assertions.assertEquals("1,2,3", StringUtil.listToString(list1, ","));

        List<Integer> list2 = new ArrayList<>();
        list2.add(null);
        list2.add(null);
        list2.add(null);
        Assertions.assertEquals("null,null,null", StringUtil.listToString(list2, ","));

        List<Integer> list3 = new ArrayList<>();
        Assertions.assertEquals("", StringUtil.listToString(list3, ","));
    }

    @Test
    void testStringToList() {
        List<String> list1 = StringUtil.stringToList("a,b,c", ",");
        Assertions.assertEquals(List.of("a", "b", "c"), list1);

        List<String> list2 = StringUtil.stringToList("a,b,c", null);
        Assertions.assertEquals(Collections.emptyList(), list2);

        List<String> list3 = StringUtil.stringToList(null, ",");
        Assertions.assertEquals(Collections.emptyList(), list3);

        List<String> list4 = StringUtil.stringToList(null, null);
        Assertions.assertEquals(Collections.emptyList(), list4);

        List<String> list5 = StringUtil.stringToList("a", ",");
        Assertions.assertEquals(List.of("a"), list5);
    }
}
