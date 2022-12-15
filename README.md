# Data-Structure-II-Java-project1
Power Connect Four Game  


The end product will be a program showing steps to play a Connect Four game (picture shown on the right, Image source:
https://en.wikipedia.org/wiki/File:Connect_Four.gif). Connect Four is a game where players win by placing four of their
pieces in a row, either horizontally, vertically, or diagonally. The board is a vertical plane with a limited number of
columns; playing a piece in a column will drop to the lowest available space. Players take turns dropping pieces into
columns, trying to get four in a row before their opponent does. In this project, we will implement a Power Connect Four
game and hence follow slightly revised rules as detailed below.  


Moves. Four types of moves are supported in our Power Connect Four game:  
1. Drop. The player places a piece into a column; the piece always falls to the lowest open space.  
2. Pop. If a player has a piece at the bottom (row 0) of a column, the player can remove it. All other pieces in that
column would go down by one row as the result. A player cannot pop from a column if the bottom piece does not
belong to that player.  
3. PowerDrop. The player can specify one column and one row to insert a piece. If originally that location had a
piece, that piece and all pieces above it must be shifted to make space. We are not allowed to insert a "floating"
piece which has a blank spot under it.  
4. PowerPop. Similar to the pop move, if a player has a piece at a given (column, row), the player can remove it. All
pieces above the removed piece would be shifted down to make sure there is no open space between pieces of the
same column. A player cannot power pop from a cell if that cell is empty or does not have a piece belonging to
that player.  


Players, turns, and winners.   
We will always have two players with red and yellow pieces respectively. The red player
always goes first at the start of the game. The two players must take turns to make valid moves to continue the game. The
player who first get four pieces in a row (either horizontally, vertically, or diagonally) is the winner. It is possible that one
move will results in both players having a 4-in-a-row at the same time. In that case, the win goes to the player who makes
the move. If your move makes the other player having a 4-in-a-row but not yourself, you will unfortunately lose.  

Implementation/Classes:  

This project will be built using a number of classes representing the component pieces of the table we described in the
previous section. Here we provide a description of these classes. Template files are provided for each class in the project
package and these contain further comments and additional details.  
• Token (Token.java): The implementation of game pieces. This class is provided to you and you should NOT
change the file.  
• Column (Column.java): The implementation of a dynamic array list. We use this as columns of the game board.
You will implement this class as a generic class to practice that concept.  
• PowerConnectFour (PowerConnectFour.java): The implementation of the Power Connect Four game that can
check moves, update board for valid moves, maintain player’s turn, and count the connected pieces in a row.
• PowerConnectFourGUI (PowerConnectFourGUI.java): A GUI class to play the game and/or test your
implementation. This class is provided to you and you should NOT change the file.  

Requirements:  


An overview of the requirements are listed below, please see the grading rubric for more details.  
• Implementing the classes - You will need to implement required classes and fill the provided template files.  
• JavaDocs - You are required to write JavaDoc comments for all the required classes and methods. Check  
provided classes for example JavaDoc comments.  
• Big-O - Template files provided to you contains instructions on the REQUIRED Big-O runtime for many
methods. Your implementation of those methods should NOT have a higher Big-O.  
