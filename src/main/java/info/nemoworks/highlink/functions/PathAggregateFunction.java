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
        return new LinkedList<>();
    }

    @Override
    public LinkedList<PathTransaction> add(PathTransaction pathTransaction, LinkedList<PathTransaction> pathTransactions) {
        System.out.println("{ passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.getTime() +"}");
        pathTransactions.add(pathTransaction);
        return pathTransactions;
    }

    @Override
    public LinkedList<PathTransaction> getResult(LinkedList<PathTransaction> pathTransactions) {
        System.out.println("================= Path end =====================！");
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
