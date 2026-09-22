package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.util.Hashtable;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class InfoFormatter {
    static Hashtable<Class<?>, CellInfoFormatter> cellInfoFormatters = null;
    
    static {
        if (cellInfoFormatters == null) {
            cellInfoFormatters = new Hashtable<Class<?>, CellInfoFormatter>();
            addFormatters();
        }
    }
    
    /**
     * Initializes and populates the cellInfoFormatters map with formatters for each specific Cell subclass.
     */
    private static void addFormatters() {
        cellInfoFormatters.put(
                PropertyCell.class, new PropertyCellInfoFormatter());
        cellInfoFormatters.put(
                GoCell.class, new GoCellInfoFormatter());
        cellInfoFormatters.put(
        		JailCell.class, new JailCellInfoFormatter());
        cellInfoFormatters.put(
        		GoToJailCell.class, new GotoJailCellInfoFormatter());
        cellInfoFormatters.put(
        		FreeParkingCell.class, new FreeParkingCellInfoFormatter());
        cellInfoFormatters.put(
                RailRoadCell.class, new RRCellInfoFormatter());
        cellInfoFormatters.put(
                UtilityCell.class, new UtilCellInfoFormatter());
        cellInfoFormatters.put(
                CardCell.class, new CCCellInfoFormatter());
    }

    /**
     * Returns a formatted string describing the given Cell using its corresponding CellInfoFormatter.
     * @param cell the Cell instance to be formatted
     * @return a string containing the formatted information of the provided Cell
     */
    public static String cellInfo(Cell cell) {
        CellInfoFormatter formatter =
                (CellInfoFormatter) cellInfoFormatters.get(cell.getClass());
        return formatter.format(cell);
    }

}
