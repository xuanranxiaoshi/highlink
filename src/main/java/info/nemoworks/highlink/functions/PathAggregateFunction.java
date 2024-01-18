package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.PathTransaction;
import org.apache.flink.api.common.functions.AggregateFunction;

import java.nio.file.Path;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class PathAggregateFunction implements AggregateFunction<PathTransaction, LinkedList<PathTransaction>, LinkedList<PathTransaction>> {
    @Override
    public LinkedList<PathTransaction> createAccumulator() {
        System.out.println("创建累加器！");
        return new LinkedList<>();
    }

    @Override
    public LinkedList<PathTransaction> add(PathTransaction pathTransaction, LinkedList<PathTransaction> pathTransactions) {
        System.out.println("调用add方法, value = { passId: " + pathTransaction.getPASSID() + ", enTime: " + pathTransaction.getENTIME() +"}");
        pathTransactions.add(pathTransaction);
        return pathTransactions;
    }

    @Override
    public LinkedList<PathTransaction> getResult(LinkedList<PathTransaction> pathTransactions) {
        System.out.println("getResult!");
        return pathTransactions;
    }

    @Override
    public LinkedList<PathTransaction> merge(LinkedList<PathTransaction> pathTransactions, LinkedList<PathTransaction> acc1) {
        LinkedList<PathTransaction> linkedList = new LinkedList<>();
        linkedList.addAll(pathTransactions);
        linkedList.addAll(acc1);
        return linkedList;
    }
}
