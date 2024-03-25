package info.nemoworks.highlink.model;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/25
 * @Copyright：
 */
public class test {
    public static void main(String[] args) {
        person person = new person();
        testPerson(person);
        System.out.println(person.age);
    }

    public static void testPerson(person p){
        p.age ++;
    }
}


class person{
    public int age =10;
}
