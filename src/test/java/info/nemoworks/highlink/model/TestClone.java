package info.nemoworks.highlink.model;

import lombok.Data;

/**
 * @description:
 * @author：jimi
 * @date: 2024/5/12
 * @Copyright：
 */

@Data
class Address {
    private String add;
}

@Data
class Student implements Cloneable{
    private int number;

    private String name;
    private Address addr;

    @Override
    public Object clone() {
        Student stu = null;
        try{
            stu = (Student)super.clone();   //浅复制
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
public class TestClone {

    public static void main(String args[]) {

        Address addr = new Address();
        addr.setAdd("杭州市");
        Student stu1 = new Student();
        stu1.setNumber(123);
        stu1.setAddr(addr);
        stu1.setName("张三");

        Student stu2 = (Student)stu1.clone();
        stu2.setName("李四");

        System.out.println("学生1:" + stu1.getNumber() + ", name:" + stu1.getName() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ", name:" + stu2.getName() + ",地址:" + stu2.getAddr().getAdd());

        addr.setAdd("西湖区");

        System.out.println("学生1:" + stu1.getNumber() + ", name:" + stu1.getName() + ",地址:" + stu1.getAddr().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + ", name:" + stu2.getName() + ",地址:" + stu2.getAddr().getAdd());
    }
}