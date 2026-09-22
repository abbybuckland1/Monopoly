package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class FreeParkingCellInfoFormatter implements CellInfoFormatter {
    
    public static final String FP_CELL_LABEL = "<html><b>Free Parking</b></html>";
    
    /**
     * Returns a formatted string representing the Free Parking cell information.
     * @param cell the Cell object to format
     * @return a string label for the Free Parking cell
     */
    public String format(Cell cell) {
        return FP_CELL_LABEL;
    }
}
