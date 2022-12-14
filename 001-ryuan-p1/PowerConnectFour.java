// TO DO: add your implementation and JavaDocs
/**
 * This class is to create more method based on class Column, to realize more
 * actions in the connect-four game. For example, we can drop tokens, remove
 * tokens, who is the current player, make sure if we can win, etc.
 * 
 * 
 * @author RongLian Yuan
 *
 */
public class PowerConnectFour {

    // DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW

    /**
     * The grid to contain tokens. Cells can be empty.
     */
    // underlying array of columns for storage -- you MUST use this for credit!
    // Do NOT change the name or type

    private Column<Token>[] grid;

    /**
     * The fixed number of columns the game grid should have.
     */
    private static final int NUM_COLS = 7;

    /**
     * The minimum number of rows of the grid _for display_.
     */
    private static final int MIN_ROWS = 6;

    /**
     * The two players of the game. playerOne is always the first to make a move
     * when the game starts.
     */
    private static final Token playerOne = Token.RED;
    /**
     * Player two is another player represented by yellow.
     */
    private static final Token playerTwo = Token.YELLOW;

    /**
     * The character used to represent empty cells when the grid is displayed.
     */
    private static final Character empty = Character.valueOf('-');

    /**
     * When grid is displayed, the top row of the grid should always be empty.
     */
    private static final int MARGIN_ROWS = 1;

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

    /**
     * totalSteps is a integers to make sure who is the current player.
     */
    private int totalSteps;

    /**
     * maxSize is the integers to show rows used for later print.
     */
    private int maxSize;

    /**
     * default class to create a grid which is a 2d array with class column.
     */
    @SuppressWarnings("unchecked")
    public PowerConnectFour() {

        grid = new Column[NUM_COLS];

        for (int i = 0; i < NUM_COLS; i++) {
            grid[i] = new Column<Token>();
        }

        totalSteps = 1;

    }

    /**
     * Return the number of columns of the grid.
     * 
     * @return the number of columns of the grid
     */

    public int sizeCol() {

        return NUM_COLS;
    }

    /**
     * Return the size of the rows which used to print as a game board. We need to
     * make sure there is always an empty row print in the whole game.
     * 
     * @return the size of the rows displayed in the game
     */
    public int sizeRow() {

        maxSize = -1;
        for (int i = 0; i < NUM_COLS; i++) {

            if (maxSize < grid[i].size()) {
                maxSize = grid[i].size();
            }
        }
        if (maxSize < MIN_ROWS) {

            maxSize = MIN_ROWS;
        } else {

            maxSize = maxSize + MARGIN_ROWS;
        }

        return maxSize;
    }

    /**
     * If the grid cell is empty, return the symbol of the empty to used for
     * printing.
     * 
     * @return the empty symbols for the game
     */
    public Character getEmptySymbol() {

        return empty;
    }

    /**
     * This method is to get the value of a 2d array with a specific column and row.
     * If that place is empty, return null. It can be used to print the game board
     * while playing.
     * 
     * @param col Locate a specific column of the grid.
     * @param row Locate a specific row of the grid.
     * @return the symbol of this location
     */
    public Token get(int col, int row) {

        Token symbol;
        if (col < 0 || col > NUM_COLS - 1 || row < 0 || row > maxSize) {

            throw new IndexOutOfBoundsException("Col " + col + ", Row " + row + " out of bounds!");
        }

        if (row > grid[col].capacity() - 1) {
            symbol = null;
        } else {
            symbol = grid[col].get(row);
        }
        return symbol;

    }

    /**
     * To return the whole column of an array. It can help us to get more
     * information at that specific column.
     * 
     * @param col Get this column in a grid.
     * @return the whole column
     */
    public Column<Token> getColumn(int col) {

        if (col < 0 || col > 7) {
            throw new IndexOutOfBoundsException("Col " + col + " out of bounds!");
        }

        return grid[col];
    }

    /**
     * To make sure which is the current player.The red player always be the first.
     * 
     * @return the current player of this game.
     */
    public Token currentPlayer() {

        if (totalSteps % 2 == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }

    }

    /**
     * This method is to drop token in the board at the specific column. The token
     * will be located at the bottom of the grid.
     * 
     * @param col to put the token at this column.
     * @return true if drop token successfully and return false if not for some
     *         reason.
     */
    public boolean drop(int col) {

        boolean ifDrop = false;
        if (col < 0 || col > NUM_COLS - 1) {
            return ifDrop;
        } else if (totalSteps % 2 == 1) {
            grid[col].add(playerOne);
            totalSteps++;
            ifDrop = true;
        } else if (totalSteps % 2 == 0) {
            grid[col].add(playerTwo);
            totalSteps++;
            ifDrop = true;
        }
        return ifDrop;

    }

    /**
     * The method is to let player put token at a specific place of index of column
     * and row. Be sure to remove the other elements and shift them after this drop.
     * Also, player cannot drop a floating token.
     * 
     * @param col drop token at this column.
     * @param row drop token at this row.
     * @return true if drop successfully and return false if not.
     */

    public boolean powerDrop(int col, int row) {

        boolean ifPowerDrop = false;
        if (col < 0 || col > NUM_COLS - 1 || grid[col].size() < row) {
            return ifPowerDrop;
        } else if (totalSteps % 2 == 1) {
            grid[col].add(row, playerOne);
            ifPowerDrop = true;
            totalSteps++;
        } else {
            grid[col].add(row, playerTwo);
            ifPowerDrop = true;
            totalSteps++;
        }

        return ifPowerDrop;
    }

    /**
     * Remove the token of the specific column and shift the rest of other tokens.
     * If the token doesn't belong to this player, player cannot remove it.
     * 
     * @param col remove token in this column.
     * @return true if it removes successfully and return false if not.
     */
    public boolean pop(int col) {

        boolean ifPop = false;
        if (totalSteps % 2 == 1) {
            if (col < 0 || col > NUM_COLS - 1 || grid[col].get(0) == null || grid[col].get(0) == playerTwo) {
                return ifPop;
            } else {
                grid[col].delete(0);
                ifPop = true;
                totalSteps++;
            }
        } else if (totalSteps % 2 == 0) {
            if (col < 0 || col > NUM_COLS - 1 || grid[col].get(0) == null || grid[col].get(0) == playerOne) {
                return ifPop;
            } else {
                grid[col].delete(0);
                ifPop = true;
                totalSteps++;
            }
        }

        return ifPop;

    }

    /**
     * Remove tokens from the board at a specific column and row. Shift the rest of
     * other tokens after removing. And if the token doesn't belong to that player,
     * player cannot do this move.
     * 
     * @param col remove token at this column.
     * @param row remove token in this row.
     * @return true if remove successfully and return false if not.
     */
    public boolean powerPop(int col, int row) {

        boolean ifPowerPop = false;
        if (totalSteps % 2 == 1) {
            if (col < 0 || col > NUM_COLS - 1 || grid[col].get(row) == null || grid[col].get(row) == playerTwo) {
                return ifPowerPop;
            } else {
                grid[col].delete(row);
                ifPowerPop = true;
                totalSteps++;
            }
        } else if (totalSteps % 2 == 0) {
            if (col < 0 || col > NUM_COLS - 1 || grid[col].get(row) == null || grid[col].get(row) == playerOne) {
                return ifPowerPop;
            } else {
                grid[col].delete(row);
                ifPowerPop = true;
                totalSteps++;
            }
        }

        return ifPowerPop;
    }

    /**
     * Count how many consecutive tokens at the specific place in a row. I split it
     * to two steps. Check the left direction and the right direction of this row.
     * 
     * @param col    locate the column of the grid
     * @param row    locate the row of the grid
     * @param player make sure the current player
     * @return the number of the consecutive tokens equals to this value of the
     *         specific column and row
     */
    public int countRow(int col, int row, Token player) {

        int consecutiveCountRow = 1;
        if (col < 0 || col > NUM_COLS - 1 || row >= grid[col].capacity() || row < 0 || grid[col].get(row) != player
                || grid[col].get(row) == null) {
            consecutiveCountRow = 0;
            return consecutiveCountRow;
        }
        // this is to check left direction
        for (int i = col; i > 0; i--) {
            if (row < grid[i - 1].capacity() && grid[i].get(row) == player
                    && grid[i].get(row) == grid[i - 1].get(row)) {
                consecutiveCountRow++;
            } else {
                break;
            }
        }
        // this is to check right direction
        for (int i = col; i < NUM_COLS - 1; i++) {
            if (row < grid[i + 1].capacity() && grid[i].get(row) == player
                    && grid[i].get(row) == grid[i + 1].get(row)) {
                consecutiveCountRow++;
            } else {
                break;
            }
        }
        return consecutiveCountRow;
    }

    /**
     * This method is to find the consecutive tokens at the specific place in a
     * column. I split it at two steps. To check to the above direction and the
     * below direction.
     * 
     * @param col    locate the column of the grid
     * @param row    locate the column of the grid
     * @param player make sure the current player
     * @return consecutive tokens in a row with given the specific column and row
     */
    public int countCol(int col, int row, Token player) {

        int consecutiveCountCol = 1;
        if (col < 0 || col > NUM_COLS - 1 || row >= grid[col].capacity() || row < 0 || grid[col].get(row) != player
                || grid[col].get(row) == null) {
            consecutiveCountCol = 0;
            return consecutiveCountCol;
        }

        // to check below direction
        for (int i = row; i > 0; i--) {
            if (grid[col].get(i) == grid[col].get(i - 1) && grid[col].get(i) == player) {
                consecutiveCountCol++;
            } else {
                break;
            }
        }
        // to check above direction
        for (int i = row; i < grid[col].capacity() - 1; i++) {
            if (grid[col].get(i) == grid[col].get(i + 1) && grid[col].get(i) == player) {
                consecutiveCountCol++;
            } else {
                break;
            }
        }

        return consecutiveCountCol;
    }

    /**
     * To find the consecutive tokens in major diagonal order. I split it to two
     * steps. To check it to left,above direction and right,below direction
     * 
     * @param col    locate the column of the grid
     * @param row    locate the column of the grid
     * @param player to make sure the current player.
     * @return the consecutive tokens in major diagonal order
     */
    public int countMajorDiagonal(int col, int row, Token player) {

        int consecutiveCountMajorDiagonal = 1;
        if (col < 0 || col > NUM_COLS - 1 || row >= grid[col].capacity() || row < 0 || grid[col].get(row) == null
                || grid[col].get(row) != player) {
            consecutiveCountMajorDiagonal = 0;
            return consecutiveCountMajorDiagonal;
        }
        // check to left, above direction
        int j = 1;
        for (int i = col; i > 0; i--) {
            if ((col - j) > 0 && (row + j) < grid[col - j].capacity() && grid[col].get(row) == player
                    && grid[col].get(row) == grid[col - j].get(row + j)) {
                j++;
                consecutiveCountMajorDiagonal++;
            } else {
                break;
            }
        }
        // check to right, below direction
        int k = 1;
        for (int i = col; i < NUM_COLS; i++) {
            if ((col + k < NUM_COLS) && (row - k < grid[col + k].capacity()) && (row - k > 0)
                    && grid[col].get(row) == player && grid[col].get(row) == grid[col + k].get(row - k)) {
                k++;
                consecutiveCountMajorDiagonal++;
            } else {
                break;
            }
        }

        return consecutiveCountMajorDiagonal;
    }

    /**
     * This method is to find the consecutive tokens in a minor diagonal order at a
     * specific column and row.I split it to two steps. To check it below,left
     * direction and above,right direction.
     * 
     * @param col    locate the column of the grid
     * @param row    locate the row of the grid
     * @param player make sure the current player
     * @return the consecutive tokens in a minor diagonal order
     */
    public int countMinorDiagonal(int col, int row, Token player) {

        int consecutiveCountMinorDiagonal = 1;
        if (col < 0 || col > NUM_COLS - 1 || row < 0 || row >= grid[col].capacity() || grid[col].get(row) != player
                || grid[col].get(row) == null) {
            consecutiveCountMinorDiagonal = 0;
            return consecutiveCountMinorDiagonal;
        }
        // check it left, below direction
        int j = 1;
        for (int i = col; i > 0; i--) {
            if ((row - j < grid[col - j].capacity()) && (col - j > 0) && (row - j > 0) && grid[col].get(row) == player
                    && grid[col].get(row) == grid[col - j].get(row - j)) {
                consecutiveCountMinorDiagonal++;
                j++;
            } else {
                break;
            }
        }

        // check it right,above direction
        int k = 1;
        for (int i = col; i < NUM_COLS - 1; i++) {
            if ((col + k < NUM_COLS) && (row + k < grid[col + k].capacity()) && grid[col].get(row) == player
                    && grid[col].get(row) == grid[col + k].get(row + k)) {
                consecutiveCountMinorDiagonal++;
                k++;
            } else {
                break;
            }
        }

        return consecutiveCountMinorDiagonal;
    }

    // ******************************************************
    // ******* DO NOT EDIT ANYTHING IN THIS SECTION *******
    // ******* But do read this section! *******
    // ******************************************************

    /**
     * The method that checks whether the specified player has four connected tokens
     * horizontally, vertically, or diagonally. It relies on the methods of
     * countRow(), countCol(), countMajorDiagonal(), and countMinorDiagonal() to
     * work correctly.
     *
     * @param player the token to be checked
     * @return whether the given player has four tokens connected
     */
    public boolean hasFourConnected(Token player) {
        // Check whether the specified player has four tokens either in a row,
        // in a column, or in a diagonal line (major or minor). Return true if
        // so; return false otherwise.

        for (int j = 0; j < sizeCol(); j++) {
            for (int i = 0; i < sizeRow(); i++) {
                if (countRow(j, i, player) >= 4 || countCol(j, i, player) >= 4 || countMajorDiagonal(j, i, player) >= 4
                        || countMinorDiagonal(j, i, player) >= 4) {
                    return true;
                }
            }
        }
        return false;

    }

    // ******************************************************
    // ******* BELOW THIS LINE IS TESTING CODE *******
    // ******* Edit it as much as you'd like! *******
    // ******* Remember to add JavaDoc *******
    // ******************************************************
    /**
     * To play with the connect four game to make sure the method in this class
     * works well.
     * 
     * @param args arguments
     * 
     */
    public static void main(String[] args) {

        // init with an empty grid
        PowerConnectFour myGame = new PowerConnectFour();
        if (myGame.sizeCol() == NUM_COLS && myGame.sizeRow() == MIN_ROWS && myGame.getColumn(2).size() == 0
                && myGame.currentPlayer() == Token.RED && myGame.get(0, 0) == null) {
            System.out.println("Yay 1!");
        }

        // drop
        if (!myGame.drop(10) && myGame.drop(2) && myGame.getColumn(2).size() == 1 && myGame.get(2, 0) == Token.RED
                && myGame.currentPlayer() == Token.YELLOW) {
            System.out.println("Yay 2!");
        }

        // drop, pop, column growing/shrinking, board display changed
        boolean ok = true;
        for (int i = 0; i < 5; i++) {
            ok = ok && myGame.drop(2); // take turns to drop to column 2 for five times
        }
        // System.out.println("===Current Grid===");
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display
        if (ok && myGame.getColumn(2).size() == 6 && myGame.sizeRow() == 7 && myGame.pop(2) && myGame.sizeRow() == 6
                && myGame.get(2, 1) == Token.RED) {
            System.out.println("Yay 3!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display

        // power drop
        if (!myGame.powerDrop(3, 1) && myGame.powerDrop(3, 0) && myGame.powerDrop(2, 2)
                && myGame.getColumn(2).size() == 6 && myGame.get(2, 2) == Token.RED && myGame.get(2, 3) == Token.YELLOW
                && myGame.getColumn(3).size() == 1) {
            System.out.println("Yay 4!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display

        // power pop
        if (!myGame.powerPop(2, 1) && myGame.powerPop(2, 3) && myGame.getColumn(2).size() == 5
                && myGame.get(2, 3).getSymbol() == 'R') {
            System.out.println("Yay 5!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display
        // PowerConnectFourGUI.reportcurrentPlayer(myGame);
        // expected display:
        // | || 0 || 1 || 2 || 3 || 4 || 5 || 6 |
        // | 5 || - || - || - || - || - || - || - |
        // | 4 || - || - || Y || - || - || - || - |
        // | 3 || - || - || R || - || - || - || - |
        // | 2 || - || - || R || - || - || - || - |
        // | 1 || - || - || R || - || - || - || - |
        // | 0 || - || - || Y || Y || - || - || - |
        // Player R's turn

        // counting
        if (myGame.countRow(3, 0, Token.YELLOW) == 2 && myGame.countRow(3, 0, Token.RED) == 0
                && myGame.countCol(2, 3, Token.RED) == 3 && myGame.drop(3) /* one more R */
                && myGame.countMajorDiagonal(3, 1, Token.RED) == 2 /* (3,1) and (2,2) */
                && myGame.countMinorDiagonal(2, 0, Token.YELLOW) == 1) {
            System.out.println("Yay 6!");
        }

    }
}