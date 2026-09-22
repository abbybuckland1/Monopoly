package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class CCCellInfoFormatter implements CellInfoFormatter {
    /**
     * Formats the given Cell object's name into an HTML string with white bold font styling.
     * @param cell the Cell object whose name is to be formatted
     * @return an HTML string representing the formatted cell name
     */
    public String format(Cell cell) {
        return "<html><font color='white'><b>" + cell.getName() + "</b></font></html>";
    }
}
