package info.nemoworks.highlink.model;

import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/13
 * @Copyright：
 */
public class ClearDataTest {

    @Test
    public void testStringLen(){

        String splitownerdisfeegroup = "G000237006000820|G000237006003420|G000237006002620";
        String splitownerfeegroup = "15|58|90";
        String splitownerpayfeegroup = "300|1112|1719";
        String splitownergroup = "315|1170|1809";

        String[] disGroup = splitownerdisfeegroup.split("\\|");
        String[] feeGroup = splitownerfeegroup.split("\\|");
        String[] payFeeGroup = splitownerpayfeegroup.split("\\|");
        String[] ownerGroup = splitownergroup.split("\\|");


        // 检查数组长度是否相同
        System.out.println("[Clear] group have the different length: ");
        System.out.println("unit["+ ownerGroup.length +"] " + splitownergroup);
        System.out.println("disGroup["+ disGroup.length +"] " + splitownerdisfeegroup);
        System.out.println("feeGroup["+ feeGroup.length +"] " + splitownerfeegroup);
        System.out.println("payFeeGroup["+ payFeeGroup.length +"] " + splitownerpayfeegroup);

    }
}
