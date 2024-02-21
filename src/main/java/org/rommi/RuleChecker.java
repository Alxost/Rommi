package org.rommi;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

public class RuleChecker {
    public boolean validateRow(Row row){
        return isEqualValueRow(row) || isConsecutiveRow(row);
    }
    public boolean matchingColorRow(Row row){
        Card firstCard = row.getRowContent().get(0);
        return row.getRowContent().stream().allMatch(x-> x.getColor().equals(firstCard.getColor()));
    }
    public boolean isConsecutiveRow(Row row){
        for(int i= 0; i< row.getSize()-1;i++){
            if(row.getRowContent().get(i).getValue() != row.getRowContent().get(i+1).getValue()-1){
                return false;
            }
        }
        return matchingColorRow(row);
    }
    public boolean isEqualValueRow(Row row){
        int firstValue  = row.getRowContent().get(0).getValue();
        boolean equalValues = row.getRowContent().stream().allMatch(x->x.getValue() == firstValue);
        return equalValues && noEqualColors(row);
    }
    public boolean noEqualColors(Row row){
        Set<Color> colors = row.getRowContent().stream().map(Card::getColor).collect(Collectors.toSet());
        return colors.size()==row.getSize();
    }
    public boolean isValidAddition(Card card, Row row){
        Row newRow = new Row(row.getRowContent(),false);
        newRow.addCard(card);
        return validateRow(newRow);
    }
}
