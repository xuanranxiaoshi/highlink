package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.entryTransaction.EntryRawTransaction;
import info.nemoworks.highlink.model.pathTransaction.PathTransaction;
import info.nemoworks.highlink.model.exitTransaction.ExitRawTransaction;
import info.nemoworks.highlink.model.gantryTransaction.GantryRawTransaction;
import org.apache.flink.api.common.functions.AggregateFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class PathAggregateFunction implements AggregateFunction<PathTransaction, List<PathTransaction>, List<PathTransaction>> {
    @Override
    public LinkedList<PathTransaction> createAccumulator() {
        return new LinkedList<>();
    }

    @Override
    public List<PathTransaction> add(PathTransaction pathTransaction, List<PathTransaction> pathTransactions) {

        pathTransactions.add(pathTransaction);


        if(pathTransaction instanceof EntryRawTransaction){
            System.out.println(Thread.currentThread().getName()+ " Entry { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.peekTime() +", size: " + pathTransactions.size()  + "}");
        }else if(pathTransaction instanceof GantryRawTransaction){
            System.out.println(Thread.currentThread().getName()+ " Gantry { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.peekTime() +", size: " + pathTransactions.size()  + "}");
        }else if(pathTransaction instanceof ExitRawTransaction){
            System.out.println(Thread.currentThread().getName()+ " Exit { passId: " + pathTransaction.getPASSID() + ", time: " + pathTransaction.peekTime() +", size: " + pathTransactions.size()  + "}");
        }


        return pathTransactions;
    }

    @Override
    public List<PathTransaction> getResult(List<PathTransaction> pathTransactions) {
        // System.out.println("================= Path [" +pathTransactions.get(0).getPASSID() + "] end =====================！");
        return pathTransactions;
    }

    @Override
    public List<PathTransaction> merge(List<PathTransaction> pathTransactions, List<PathTransaction> acc1) {
        System.out.println(Thread.currentThread().getName() + " merge[" + acc1.get(0).getPASSID()+"]:" + acc1.size() + " +" + pathTransactions.size() );
        List<PathTransaction> linkedList = new LinkedList<>();
        linkedList.addAll(pathTransactions);
        linkedList.addAll(acc1);
        return linkedList;
    }
}
