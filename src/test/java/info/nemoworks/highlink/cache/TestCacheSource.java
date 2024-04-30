package info.nemoworks.highlink.cache;

import info.nemoworks.highlink.dao.JedisCacheDaoImp;
import info.nemoworks.highlink.dataflow.SplitDataFlowDev;
import info.nemoworks.highlink.utils.SimpleContainer;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.resps.ScanResult;

import java.util.List;

/**
 * @description: 测试多省缓存数据的定时读取功能
 * @author：jimi
 * @date: 2024/4/29
 * @Copyright：
 */
public class TestCacheSource {
//
//    @Test
//    /**
//     * 根据 key 的匹配，提取所有符合的 key 的 value
//     */
//    public void getAllValues() {
//        JedisCacheDaoImp dao = (JedisCacheDaoImp) SimpleContainer.getCachePool().getDaoImp();
//        String baseKey = SplitDataFlowDev.B2_PREFIX + SplitDataFlowDev.F2_PREFIX;
//        // 加入数据
//        for (int i = 0; i < 100; i++) {
//            dao.set(baseKey + i, "value" + i);
//        }
//        // 模糊查询
//        String pattern = SplitDataFlowDev.B2_PREFIX + SplitDataFlowDev.F2_PREFIX + "*";
//
//        // 初始游标为0，表示开始迭代
//        String cursor = "0";
//        Integer count = 10;
//
//
//        do {
//            // 调用 SCAN 命令进行迭代
//            ScanResult<String> scanResult = dao.scan(cursor, pattern, count);
//
//            // 获取迭代结果
//            cursor = scanResult.getCursor(); // 获取下一次迭代的游标位置
//            for (String key : scanResult.getResult()) {
//                // 处理迭代结果，这里可以打印、操作等
//                System.out.print(key);
//                System.out.println("=>" + dao.get(key));
//                dao.del(key);
//            }
//            System.out.println("========== cursor = " + cursor + "==========");
//        } while (!cursor.equals("0")); // 当游标为 0 时表示迭代结束
//    }
//
//    @Test
//    public void testPattern(){
//        JedisCacheDaoImp dao = (JedisCacheDaoImp) SimpleContainer.getCachePool().getDaoImp();
//        String baseKey1 = SplitDataFlowDev.B2_PREFIX + SplitDataFlowDev.F2_PREFIX;
//        String baseKey2 = SplitDataFlowDev.B4_PREFIX + SplitDataFlowDev.F2_PREFIX;
//        // 加入数据
//        for (int i = 0; i < 50; i++) {
//            dao.set(baseKey1 + i, "value" + i);
//        }
//        for (int i = 0; i < 50; i++) {
//            dao.set(baseKey2 + i, "value" + i);
//        }
//
//        // 模糊查询
//        String pattern = "B*:" + SplitDataFlowDev.F2_PREFIX + "*";
//
//        // 初始游标为0，表示开始迭代
//        String cursor = "0";
//        Integer count = 10;
//
//
//        do {
//            // 调用 SCAN 命令进行迭代
//            ScanResult<String> scanResult = dao.scan(cursor, pattern, count);
//
//            // 获取迭代结果
//            cursor = scanResult.getCursor(); // 获取下一次迭代的游标位置
//            for (String key : scanResult.getResult()) {
//                // 处理迭代结果，这里可以打印、操作等
//                System.out.print(key);
//                System.out.println("=>" + dao.get(key));
//                dao.del(key);
//            }
//            System.out.println("========== cursor = " + cursor + "==========");
//        } while (!cursor.equals("0")); // 当游标为 0 时表示迭代结束
//    }
}
