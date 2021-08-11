import java.util.Scanner;

// User to navigate a simple maze using a specific characters
// user need to find the way out within 100 steps
// A helper class for maze is provided (Maze.java)
public class MazeRunner {

    // Main method with endless loop
    // Initiate helper object (maze.java) and manage user input

    public static void main(String[] args) {
        char move,jump;
        boolean moveStatus,winStatus,pitAhead;
        int moveCount;

        intro();
        Maze myMap = new Maze();
        moveCount=0;

        while(true){
            myMap.printMap();

            // Milestone notification. exit (terminate) if movement >= 100
            movesMessage(moveCount);
            if(moveCount>=100){
                System.out.println("Sorry, but you didn't escape in time- you lose!");
                break;
            }

            // Get direction input from user
            move = usermove();

            // If input other than RLUD, ask user to input again
            if(!(move == 'R' || move == 'L' || move == 'U' || move == 'D'))
                continue;

            // Pit checker. Ask if user wants to jump
            pitAhead = myMap.isThereAPit(Character.toString(move));
            if(pitAhead==true){
                jump = navigatePit();
                if(jump=='Y'){
                    myMap.jumpOverPit(Character.toString(move));
                }
            }

            // Increase movement counter
            moveCount++;

            // Get movement status
            moveStatus = false;
            if(move=='R')
                moveStatus = myMap.canIMoveRight();
            else if(move=='L')
                moveStatus = myMap.canIMoveLeft();
            else if(move=='U')
                moveStatus = myMap.canIMoveUp();
            else
                moveStatus = myMap.canIMoveDown();

            // If movement status is true, make the move
            if(moveStatus == true){
                if(move=='R')
                    myMap.moveRight();
                else if(move=='L')
                    myMap.moveLeft();
                else if(move=='U')
                    myMap.moveUp();
                else if(move=='D')
                    myMap.moveDown();

                winStatus = myMap.didIWin();
                if(winStatus == true){
                    System.out.println("Congratulations, you made it out alive! and you did it in "+moveCount+" moves");
                    break;
                }
            }else{
                System.out.println("Sorry, you’ve hit a wall.");
            }
        }
    }

    // Display welcome message
    public static void intro(){
        System.out.println("Welcome to Maze Runner!");
    }

    // Prompt user for direction. User may enter a string
    // Just take the 1st char as input
    public static char usermove(){
        Scanner input = new Scanner(System.in);
        String moveStr;
        char move;

        System.out.println("Where would you like to move? (R, L, U, D)");
        moveStr = input.next();
        move = Character.toUpperCase(moveStr.charAt(0));
        return move;
    }

    // Movement notification. Show current and remaining move value
    public static void movesMessage(int moves){
        if(moves==50)
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
        else if(moves==75)
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        else if (moves==90)
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        else if (moves==100)
            System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
    }

    // Prompt user for an action. User may enter a string
    // Just take the 1st char as input
    public static char navigatePit(){
        Scanner input = new Scanner(System.in);
        String jumpStr;
        char jump;

        System.out.println("Watch out! There's a pit ahead, jump it?");
        jumpStr = input.next();
        jump = Character.toUpperCase(jumpStr.charAt(0));
        return jump;
    }
}
