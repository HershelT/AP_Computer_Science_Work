/*
 *   A game of TicTacToe to be played between two human players.
 *   
 *   The board is a 2D array of Strings. 
 *   One player is the String literal "X".
 *   The second player is the String literal "O" (the letter O, not the numeral zero).
 *   
 *   The game should continue to be played as long as either:
 *   1. No player has won the game.
 *   2. There is at least one open square left in the game.
 *    
 *   This project adapted from code by I. Frame.
 *
 *   @yourName: Hershel Thomas
 */

public class TicTacToeGame
{
    /*
     * These fields are used to build the GUI.
     * You won't need to modiy them. 
     * They are declared as final because their value is final; it won't ever change.
     * The convention for final constants is all uppercase. Remember Math.PI?
     */
    private final int WINDOW_SIZE;
    private final int LINE_BUFFER;
    private final int TILE_SIZE;
    
    // These are the fields you'll be working with. Added new fiels for Strings and ints for collum and row testing
    private boolean isXsTurn;
    private String[][] board;
    public String pResult = "";
    public String r1Check = "";
    public int oneX;
    public int oneO;
    public TicTacToeGame()
    {
        WINDOW_SIZE = 600;
        LINE_BUFFER = 25;
        TILE_SIZE = WINDOW_SIZE / 3;
        isXsTurn = true;
    }
    private boolean isCellClaimed(int r, int c)
    {
        // to be implemented by you
        //checks if board piece is taken by checking if it is still the default null, returns true if not null
        if (board[r][c] != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasDiagonalsWon()
    {
        //to be implemented by you. 
        //turns each diagonal into a readble String. Uses .charAt() to find how many of X's or O's there are
        //and Then returns true if there are 3 of the same kind cant figure out how to make it more efficient with
        //nested for loops like I did for collums, so one for loop also works.
        String lCheck = "";
        int lX = 0;
        int lO = 0;
        String rCheck = "";
        int rX = 0;
        int rO = 0;
        
        for (int i = 0; i < board[0].length; i++) {
            lCheck += board[i][i];
            rCheck += board[i][(board[0].length - 1) - i];
            if (lCheck.charAt(i) == 'X') {
                lX++;
            } else if (lCheck.charAt(i) == 'O') {
                lO++;
            } 
            if (rCheck.charAt(i) == 'X') {
                rX++;
            } else if (rCheck.charAt(i) == 'O') {
                rO++;
            }
        }
        if (lX == 3 || rX == 3 || lO == 3 || rO == 3) {
            System.out.println("Game Over! " + board[1][1] + " Diagonal Win");
            return true;
        }
        return false;    
    }

    private boolean hasRowsWon()
    {
        //to be implemented by you.
        //uses a nested for loop to create each row as a string, and then use a counter to test if there are 3 of the same
        //charcters in the String and then if there are return true and tell who won the game.
        for (int i = 0; i < board.length; i++) {
            oneX = 0;
            oneO = 0;
            r1Check = "";
            for (int j = 0; j < board[0].length; j++) {
                r1Check += board[i][j];
                if (r1Check.charAt(j) == 'X') {
                    oneX++;
                } else if (r1Check.charAt(j) == 'O') {
                    oneO++;
                }
                if (oneX == 3 || oneO == 3) {
                    System.out.println("Game Over! " + r1Check.charAt(0) + " Row Win");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasColsWon()
    {
        //to be implemented by you. 
        //checks collums using same was as rows by nested for loop
        for (int i = 0; i < board.length; i++) {
            oneX = 0;
            oneO = 0;
            r1Check = "";
            for (int j = 0; j < board[0].length; j++) {
                r1Check += board[j][i];
                if (r1Check.charAt(j) == 'X') {
                    oneX++;
                } else if (r1Check.charAt(j) == 'O') {
                    oneO++;
                }
                if (oneX == 3 || oneO == 3) {
                    System.out.println("Game Over! " + r1Check.charAt(0) + " Column Win");
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isGameOver()
    {
        //to be implemented by you. 
        //isGameOver() should call other methods in the class to determine if the game is over. 
        if (hasColsWon() || hasRowsWon() || hasDiagonalsWon() || isBoardFull()) {return true;}
        return false;
    }
    //This will require looping, so you may want to save this for later in Module 6.
    private boolean isBoardFull()
    {
        //to be implemented by you. 
        //runs a for nested for loop and uses a counter to check if all 9 spots are claimed
        int counter = 0;
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0; j < board[0].length; j++) {
                if (isCellClaimed(i, j)) {
                    counter++;
                }
            }
        }
        if (counter == 9) {
            System.out.println("Tie!");
            return true;
        } else {
            return false;
        }
    }
    public void printBoard() {
        int spot = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.println("" + spot + ": " + board[i][j]);
                spot++;
            }
        }
    }
    //////////////////////DO NOT MODIFY ANYTHING BELOW THIS COMMENT!!!!////////////////////////
    //////////////////////SERIOUSLY, DO NOT CHANGE ANYTHING AFTER THIS COMMENT.////////////////
    public void playAGame()
    {
        drawBoard();
        while (!isGameOver())
        {
            if (mouseClicked())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int square = determineSquare(x, y);
                int[] coords = getSquareRowColumn(square);
                if (!isCellClaimed(coords[0], coords[1]))
                {
                    if(isXsTurn)
                        drawShape("X", square);
                    else
                        drawShape("O", square);
                    isXsTurn = !isXsTurn;
                }
            }
        }
    }



    private void drawBoard()
    {
        StdDraw.setCanvasSize(WINDOW_SIZE, WINDOW_SIZE);
        StdDraw.setScale(0, WINDOW_SIZE);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(WINDOW_SIZE / 3, LINE_BUFFER, WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER);
        StdDraw.line(2*WINDOW_SIZE / 3, LINE_BUFFER, 2*WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER);     
        StdDraw.line(LINE_BUFFER, WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER, WINDOW_SIZE / 3);
        StdDraw.line(LINE_BUFFER, 2*WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER, 2*WINDOW_SIZE / 3);
        StdDraw.show();
        board = new String[3][3];
    }

    private int determineSquare(double x, double y)
    {
        if (y > 2*WINDOW_SIZE / 3)
        {
            if (x < WINDOW_SIZE / 3)
                return 1;
            else if (x < 2*WINDOW_SIZE / 3)
                return 2;
            else
                return 3;
        }
        else if (y > WINDOW_SIZE / 3)
        {
            if (x < WINDOW_SIZE / 3)
                return 4;
            else if (x < 2*WINDOW_SIZE / 3)
                return 5;
            else 
                return 6;
        }
        else
        {
            if (x < WINDOW_SIZE / 3)
                return 7;
            else if (x < 2*WINDOW_SIZE / 3)
                return 8;
            else
                return 9;
        }
    }

    private int[] getSquareRowColumn(int square)
    {
        int[] coords = new int[2];
        switch (square){
            case 1: 
                coords[0] = 0; coords[1] = 0; break;
            case 2:
                coords[0] = 0; coords[1] = 1; break;
            case 3: 
                coords[0] = 0; coords[1] = 2; break;
            case 4:
                coords[0] = 1; coords[1] = 0; break;
            case 5:
                coords[0] = 1; coords[1] = 1; break;
            case 6:
                coords[0] = 1; coords[1] = 2; break;
            case 7:
                coords[0] = 2; coords[1] = 0; break;
            case 8: 
                coords[0] = 2; coords[1] = 1; break;
            case 9: 
                coords[0] = 2; coords[1] = 2; break;
            default:
                break;
        }
        return coords;
    }

    private void drawShape(String letter, int square)
    {
        double x, y;
        if (square == 1 || square == 4 || square == 7)
            x = WINDOW_SIZE / 2 - TILE_SIZE;
        else if (square == 2 || square == 5 || square == 8)
            x = WINDOW_SIZE / 2;
        else
            x = WINDOW_SIZE / 2 + TILE_SIZE;
        if (square <= 3)
            y = WINDOW_SIZE - TILE_SIZE / 2;
        else if (square <= 6)
            y = WINDOW_SIZE / 2;
        else
            y = TILE_SIZE / 2;

        if (letter.equalsIgnoreCase("X"))
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x - TILE_SIZE / 2 + LINE_BUFFER, y - TILE_SIZE / 2 + LINE_BUFFER, x + TILE_SIZE / 2 - LINE_BUFFER, y + TILE_SIZE / 2 - LINE_BUFFER);
            StdDraw.line(x - TILE_SIZE / 2 + LINE_BUFFER, y + TILE_SIZE / 2 - LINE_BUFFER, x + TILE_SIZE / 2 - LINE_BUFFER, y - TILE_SIZE / 2 + LINE_BUFFER);
            addSymbolToBoard("X", square);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x, y, TILE_SIZE / 2 - LINE_BUFFER);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(x, y, TILE_SIZE / 2 - 1.2*LINE_BUFFER);
            addSymbolToBoard("O", square);
        }
        StdDraw.show();
    }

    private void addSymbolToBoard(String letter, int square)
    {
        switch (square){
            case 1: 
                board[0][0] = letter; break;
            case 2:
                board[0][1] = letter; break;
            case 3: 
                board[0][2] = letter; break;
            case 4:
                board[1][0] = letter; break;
            case 5:
                board[1][1] = letter; break;
            case 6:
                board[1][2] = letter; break;
            case 7:
                board[2][0] = letter; break;
            case 8: 
                board[2][1] = letter; break;
            case 9: 
                board[2][2] = letter; break;
            default:
                break;
        }
    }

    private void waitForStart()
    {
        while (!mouseClicked())
        {}
    }

    private boolean mouseClicked()
    {
        if (StdDraw.mousePressed())
        {
            while (StdDraw.mousePressed())
            {}
            return true;
        }
        return false;
    }
}
