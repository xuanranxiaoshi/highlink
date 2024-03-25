package info.nemoworks.highlink.model.pathTransaction;

import lombok.Data;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @description:
 * @author：jimi
 * @date: 2024/3/11
 * @Copyright：
 */
@Data
public class Person implements Serializable {

    @JsonProperty(value = "NAME", access= JsonProperty.Access.WRITE_ONLY)
    String nAME;

    @JsonProperty("AGE")
    int aGE;

    public Person(){};

    public Person(String name){
        this.nAME = name;
        this.aGE = 19;
    }

}
