package info.nemoworks.highlink.functions;

import info.nemoworks.highlink.model.gantryTransaction.GantryCpcTransaction;
import org.apache.flink.api.common.functions.AggregateFunction;
import java.util.LinkedList;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public class CpcAggregateFunction implements AggregateFunction<GantryCpcTransaction, LinkedList<GantryCpcTransaction>, LinkedList<GantryCpcTransaction>> {
    @Override
    public LinkedList<GantryCpcTransaction> createAccumulator() {
        System.out.println("创建累加器！");
        return new LinkedList<GantryCpcTransaction>();
    }

    @Override
    public LinkedList<GantryCpcTransaction> add(GantryCpcTransaction gantryCpcTransaction, LinkedList<GantryCpcTransaction> gantryCpcTransactions) {
        System.out.println("调用add方法, value = { passId: " + gantryCpcTransaction.getPASSID() + ", enTime: " + gantryCpcTransaction.getENTIME());
        gantryCpcTransactions.add(gantryCpcTransaction);
        return gantryCpcTransactions;
    }

    @Override
    public LinkedList<GantryCpcTransaction> getResult(LinkedList<GantryCpcTransaction> gantryCpcTransactions) {
        System.out.println("调用getResult 方法");
        return gantryCpcTransactions;
    }

    @Override
    public LinkedList<GantryCpcTransaction> merge(LinkedList<GantryCpcTransaction> gantryCpcTransactions, LinkedList<GantryCpcTransaction> acc1) {
        LinkedList<GantryCpcTransaction> mergedList = new LinkedList<>();
        mergedList.addAll(gantryCpcTransactions);
        mergedList.addAll(acc1);
        return mergedList;
    }
}
