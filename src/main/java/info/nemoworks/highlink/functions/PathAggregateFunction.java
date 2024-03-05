package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.EntryRawTransaction;
import info.nemoworks.highlink.model.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
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
        if(pathTransaction instanceof EntryRawTransaction){
            System.out.println("Entry { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.getTime() +"}");
        }else if(pathTransaction instanceof GantryRawTransaction){
            System.out.println("Gantry { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.getTime() +"}");
        }else if(pathTransaction instanceof ExitRawTransaction){
            System.out.println("Exit { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.getTime() +"}");
        }

        pathTransactions.add(pathTransaction);
        return pathTransactions;
    }

    @Override
    public LinkedList<PathTransaction> getResult(LinkedList<PathTransaction> pathTransactions) {
        System.out.println("================= Path [" +pathTransactions.get(0).getPASSID() + "] end =====================！");
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
