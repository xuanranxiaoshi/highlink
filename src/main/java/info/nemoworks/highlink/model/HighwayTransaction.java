package info.nemoworks.highlink.model;

import javax.swing.plaf.PanelUI;

public interface HighwayTransaction {

    public String getID();

    public String getPASSID();

    default String peekTime() {
        return null;
    }
}
