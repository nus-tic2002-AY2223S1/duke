package duke.util;

import duke.constant.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.Map;

class DataUtilTest {


    @Test
    void testToString() {
        Map<String, Integer> map = Map.of("k1", 1);
        String s1 = DataUtil.toString(1);
        Assertions.assertEquals( "1", s1);

        String s2 = DataUtil.toString(1.1);
        Assertions.assertEquals( "1.1", s2);

        String s3 = DataUtil.toString(true);
        Assertions.assertEquals( "true", s3);

        String s4 = DataUtil.toString(map);
        Assertions.assertEquals("{k1=1}", s4);

        String s5 = DataUtil.toString(null);
        Assertions.assertEquals("null", s5);
    }

    @Test
    void testToInteger() {
        int i1 = DataUtil.toInteger("1");
        Assertions.assertEquals(1, i1);

        int i2 = DataUtil.toInteger("-1");
        Assertions.assertEquals(-1, i2);

        int i3 = DataUtil.toInteger("1.1");
        Assertions.assertEquals(0, i3);

        int i4 = DataUtil.toInteger(null);
        Assertions.assertEquals(0, i4);

        int i5 = DataUtil.toInteger("abc");
        Assertions.assertEquals(0, i5);
    }

    @Test
    void testToLong() {
        long l1 = DataUtil.toLong("1");
        Assertions.assertEquals(1, l1);

        long l2 = DataUtil.toLong("-1");
        Assertions.assertEquals(-1, l2);

        long l3 = DataUtil.toLong("1.1");
        Assertions.assertEquals(0, l3);

        long l4 = DataUtil.toLong(null);
        Assertions.assertEquals(0, l4);

        long l5 = DataUtil.toLong("abc");
        Assertions.assertEquals(0, l5);
    }
}
