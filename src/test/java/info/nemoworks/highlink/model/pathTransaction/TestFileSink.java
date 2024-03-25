//package info.nemoworks.highlink.model.pathTransaction;
//
//
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.common.serialization.Encoder;
//import org.apache.flink.connector.file.sink.FileSink;
//import org.apache.flink.core.fs.Path;
//import org.apache.flink.runtime.state.filesystem.FsStateBackend;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.functions.sink.filesystem.OutputFileConfig;
//
//import java.io.*;
//import java.util.Arrays;
//
///**
// * @description:
// * @author：jimi
// * @date: 2024/3/11
// * @Copyright：
// */
//public class TestFileSink {
//
//    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.enableCheckpointing();
//        env.setStateBackend(new FsStateBackend("file:///D://data"));
//
//        String[] names = {"张三", "李四", "王五", "老六"};
//        //2.source
//        DataStreamSource<String> lines = env.fromCollection(Arrays.asList(names));
//
//        SingleOutputStreamOperator<Person> line2 = (SingleOutputStreamOperator<Person>) lines.map(new MapFunction<String, Person>() {
//            @Override
//            public Person map(String s) throws Exception {
//                return new Person(s);
//            }
//        });
//
//        //3.sink
//        //设置sink的前缀和后缀
//        //文件的头和文件扩展名
//        //prefix-xxx-.txt
//        OutputFileConfig config = OutputFileConfig
//                .builder()
//                .withPartPrefix("person")
//                .withPartSuffix(".txt")
//                .build();
//
//        //设置sink的路径
//        String outputPath = "file:///D://data";
//
//        final FileSink<Person> sink = FileSink
//                .forRowFormat(new Path(outputPath), new Encoder<Person>() {
//                    @Override
//                    public void encode(Person person, OutputStream outputStream) throws IOException {
//                        String res = "[ name: " + person.getName() + ", age: " + person.getAge() +"]\n";
//                        outputStream.write(res.getBytes());
//                    }
//                })
//                .withOutputFileConfig(config)
//                .build();
//
//        line2.sinkTo(sink).setParallelism(1);
//
//        env.execute();
//    }
//}
