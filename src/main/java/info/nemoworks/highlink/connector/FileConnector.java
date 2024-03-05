package info.nemoworks.highlink.connector;

import org.apache.flink.runtime.io.disk.iomanager.BufferFileWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @description:
 * @author：jimi
 * @date: 2024/2/29
 * @Copyright：
 */
public class FileConnector {

    private static Writer instance = null;

    public static Writer getWriter(){
        try {
            if(instance == null){
                synchronized (FileConnector.class){
                    if(instance == null){
                        instance = new BufferedWriter(new FileWriter(Configure.PRINT_FILENAME));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
