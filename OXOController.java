import OXOExceptions.*;

import java.util.Locale;

class OXOController
{
    private OXOModel gameModel;
    private int currentPlayer = 0;
    private int turnsTaken = 0;

    public OXOController(OXOModel model)
    {
        gameModel = model;
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(currentPlayer));
    }

    private boolean rowChecker(OXOPlayer player)
    {
        int count;
        for (int rowInd = 0; rowInd < gameModel.getNumberOfRows(); rowInd++) {
            count = 0;
            for (int colInd = 0; colInd < gameModel.getNumberOfColumns(); colInd++) {
                if (gameModel.getCellOwner(rowInd, colInd) == player) {
                    count++;
                }
                else {
                    count = 0;
                }
                if (count >= gameModel.getWinThreshold()) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean colChecker(OXOPlayer player)
    {
        int count;
        for (int colInd = 0; colInd < gameModel.getNumberOfColumns(); colInd++)
        {
            count = 0;
            for (int rowInd = 0; rowInd < gameModel.getNumberOfRows(); rowInd++)
            {
                if (gameModel.getCellOwner(rowInd, colInd) == player) {
                    count++;
                }
                else {
                    count = 0;
                }
                if (count >= gameModel.getWinThreshold()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean diagChecker(OXOPlayer player)
    {
        int count;
        for (int topRow = 0; topRow < gameModel.getNumberOfRows(); topRow++) {
            for (int topCol = 0; topCol < gameModel.getNumberOfColumns(); topCol++) {
                count = 0;
                int colInd = topCol, rowInd = topRow;
                while (colInd < gameModel.getNumberOfColumns() && rowInd < gameModel.getNumberOfRows()) {
                    if (gameModel.getCellOwner(rowInd, colInd) == player) {
                        count++;
                    }
                    else {
                        count = 0;
                    }
                    if (count >= gameModel.getWinThreshold()) {
                        return true;
                    }
                    colInd++;
                    rowInd++;
                }
                count = 0;
                colInd = topCol;
                rowInd = topRow;
                while (colInd >= 0 && rowInd < gameModel.getNumberOfRows()) {
                    if (gameModel.getCellOwner(rowInd, colInd) == player) {
                        count++;
                    }
                    else {
                        count = 0;
                    }
                    if (count >= gameModel.getWinThreshold()) {
                        return true;
                    }
                    colInd--;
                    rowInd++;
                }

            }
        }
        return false;
    }

    private void winChecker()
    {
        for (int i = 0; i < gameModel.getNumberOfPlayers(); i++) {
            if (colChecker(gameModel.getPlayerByNumber(i)) || rowChecker(gameModel.getPlayerByNumber(i)) || diagChecker(gameModel.getPlayerByNumber(i))) {
                gameModel.setWinner(gameModel.getPlayerByNumber(i));
                return;
            }
        }
    }

    private boolean isDraw()
    {
        if (turnsTaken >= gameModel.getNumberOfColumns() * gameModel.getNumberOfRows()) {
            return true;
        }
        return false;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        /* Command length is not 2 */
        if (command.length() != 2) {
            throw new InvalidIdentifierLengthException(command.length());
        }
        /* 1st char isn't a letter */
        if (!Character.isLetter(command.charAt(0))) {
            throw new InvalidIdentifierCharacterException(RowOrColumn.ROW, command.charAt(0));
        }
        /* 2nd char isn't a number */
        if (!Character.isDigit(command.charAt(1))) {
            throw new InvalidIdentifierCharacterException(RowOrColumn.COLUMN, command.charAt(1));
        }

        command = command.toLowerCase();

        int rowNum = command.charAt(0) - 'a';
        int colNum = command.charAt(1) - '0' - 1;

        if (rowNum < 0 || rowNum >= gameModel.getNumberOfRows()) {
            throw new OutsideCellRangeException(RowOrColumn.ROW, rowNum);
        }
        if (colNum < 0 || colNum >= gameModel.getNumberOfColumns()) {
            throw new OutsideCellRangeException(RowOrColumn.COLUMN, colNum);
        }
        if (gameModel.getCellOwner(rowNum, colNum) != null) {
            throw new CellAlreadyTakenException();
        }
        gameModel.setCellOwner(rowNum, colNum, gameModel.getCurrentPlayer());
        currentPlayer = (currentPlayer + 1) % gameModel.getNumberOfPlayers();
        this.turnsTaken++;
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(currentPlayer));
        winChecker();
        if (isDraw()) {
            gameModel.setGameDrawn();
        }
    }
}

