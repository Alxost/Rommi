package org.rommi;

import org.rommi.gameUtils.Card;
import org.rommi.gameUtils.RommiGame;
import org.rommi.gameUtils.Row;
import org.rommi.gameUtils.RuleChecker;

import java.util.ArrayList;

public class RommiRobot {
    GameController gameController;
    RommiGame rommiGame;
    RuleChecker ruleChecker;

    boolean out;

    RommiRobot(GameController gameController, RommiGame rommiGame){
        this.gameController = gameController;
        this.rommiGame = rommiGame;
        this.out = false;
        this.ruleChecker = new RuleChecker();
    }
    public void move(){
        boolean madeMove = false;
        ArrayList<Row> possibleRows = calcValidRows();
        if (!possibleRows.isEmpty()){
            Row rowToPlay = possibleRows.getFirst();
            gameController.createNewRow(rowToPlay.getRowContent());
            out = true;
            madeMove = true;
        }
        if(out){
            madeMove = addCardsToExistingRows();
        }
        if(!madeMove){
            gameController.drawCards(1,gameController.getActivePlayer());
        }
    }

    public boolean addCardsToExistingRows(){
        for (Card card: gameController.getActivePlayer().getHand().getRowContent()){
            for(Row row : gameController.getPlayedRows()){
                if (ruleChecker.isValidAddition(card, row)){
                    row.addCard(card);
                    card.setOwner(row);
                    gameController.getActivePlayer().getHand().removeCard(card);
                    addCardsToExistingRows();
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Row> calcValidRows(){
        return subRows(gameController.getActivePlayer().getHand().getRowContent());
    }

    public void calcSubRow(ArrayList<Card> hand,
                                  ArrayList<Row> result,
                                  ArrayList<Card> subset,
                                  int index)
    {
        // Add the current subset to the result list
        if (subset.size() >= 3) {
            Row row = new Row(subset, false);
            //System.out.println(row);
            if(ruleChecker.validateRow(row, true)){
                result.add(row);
            }
        }
        // Generate subsets by recursively including and
        // excluding elements
        for (int i = index; i < hand.size(); i++) {
            // Include the current element in the subset
            subset.add(hand.get(i));

            // Recursively generate subsets with the current
            // element included
            calcSubRow(hand, result, subset, i + 1);

            // Exclude the current element from the subset
            // (backtracking)
            subset.remove(subset.size() - 1);
        }
    }

    public ArrayList<Row> subRows(ArrayList<Card> A)
    {
        ArrayList<Card> subset = new ArrayList<>();
        ArrayList<Row > result = new ArrayList<>();

        int index = 0;
        calcSubRow(A, result, subset, index);

        return result;
    }
}
