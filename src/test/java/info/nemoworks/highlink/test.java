package info.nemoworks.highlink;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/30
 * @Copyright：
 */
public class test {
    @Test
    public void test(){
        String format = DateFormatUtils.format(1704495939999l, "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(format);
    }
}
