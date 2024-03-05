package info.nemoworks.highlink.model.mapper;

import info.nemoworks.highlink.connector.FileConnector;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author：jimi
 * @date: 2024/2/29
 * @Copyright：
 */
public class TestFileWriter {

    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new testThread(i*5, latch)).start();
        }
        latch.await();
        FileConnector.getWriter().close();
    }
}


class testThread implements  Runnable{

    private int start;
    private CountDownLatch count;

    public testThread(int i, CountDownLatch count){
        this.start = i;
        this.count = count;
    }

    @Override
    public void run() {
        Writer writer = FileConnector.getWriter();
        for(int i = 0; i < 5; i ++){
            try {
                System.out.println("thread " + Thread.currentThread() + ": " + (start + i));
                writer.write("thread " + Thread.currentThread() + ": " + (start + i) + "\n");
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        count.countDown();
    }
}