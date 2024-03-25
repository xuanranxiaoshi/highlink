package info.nemoworks.highlink.model.pathTransaction;

import info.nemoworks.highlink.model.HighwayTransaction;

/**
 * @description:
 * @author：jimi
 * @date: 2024/1/17
 * @Copyright：
 */
public interface PathTransaction extends HighwayTransaction {
    public String getPASSID();

    public String peekTime();
}
