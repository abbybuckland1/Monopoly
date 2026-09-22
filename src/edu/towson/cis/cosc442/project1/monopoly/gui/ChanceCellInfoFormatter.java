package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class ChanceCellInfoFormatter implements CellInfoFormatter {
    
    public static final String CHANCE_CELL_LABEL = "<html><font color='teal'><b>Chance</b></font></html>";
    
    /**
     * Returns a formatted string representing the label for a Chance cell.
     * @param cell the Cell object to be formatted
     * @return a formatted HTML string label for the Chance cell
     */
    public String format(Cell cell) {
        return CHANCE_CELL_LABEL;
    }
}
