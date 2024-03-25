package info.nemoworks.highlink.model.pathTransaction;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @description: 为 json 注解，添加 access 属性
 * @author：jimi
 * @date: 2024/3/13
 * @Copyright：
 */
public class testReplace {
    @Test
    public void test(){
        processJsonPropertyAnnotations("src/main/java/info/nemoworks/highlink/model/gantryTransaction/GantryRawTransaction.java",
                "src/main/java/info/nemoworks/highlink/model/gantryTransaction/GantryRawTransaction.java");
    }

    public static void processJsonPropertyAnnotations(String filename, String filename2) {
        try {
            // 读取Java源代码文件
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // 匹配 @JsonProperty 注解并添加 access 属性
                line = line.replaceAll("@JsonProperty\\((\"[^\"]+\")\\)", "@JsonProperty(value = $1, access = JsonProperty.Access.WRITE_ONLY)");
                stringBuilder.append(line).append("\n");
            }
            reader.close();

            // 将修改后的内容写回到文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(stringBuilder.toString());
            writer.close();

            System.out.println("JsonProperty annotations processed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
