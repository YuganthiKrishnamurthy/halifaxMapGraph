import java.util.*;

public class HalifaxMapUI {

    public static void main(String[] args) {
        // Define variables to manage user input

        String userCommand = "";
        int userArgument1,userArgument2,userArgument3,userArgument4;
        Scanner userInput = new Scanner( System.in );

        // Define variables to catch the return values of the transformation methods

        boolean booleanOutcome;

        // Define a single map transformation object.

        HalifaxMap map = new HalifaxMap();

        // Process the user input until they provide the command "quit"

        do {
            // Find out what the user wants to do
            userCommand = userInput.next();




            /* Do what the user asked for. */

            if (userCommand.equalsIgnoreCase("newIntersection")) {
                try {

                    System.out.print("Enter x coordinate :");
                    userArgument1 = userInput.nextInt();
                    System.out.print("Enter y coordinate :");
                    userArgument2 = userInput.nextInt();
                    booleanOutcome = map.newIntersection(userArgument1, userArgument2);
                    System.out.println("newIntersection \""+userArgument1+","+userArgument2+"\" outcome " + booleanOutcome );
                }
        catch (Exception e) {
                booleanOutcome=false;
            System.out.println("newIntersection outcome " + booleanOutcome );
            userCommand = userInput.next();
            }

            } else if (userCommand.equalsIgnoreCase("defineRoad")) {
                try {
                    System.out.print("Enter x coordinate of source vertex:");
                    userArgument1 = userInput.nextInt();
                    System.out.print("Enter y coordinate of source vertex:");
                    userArgument2 = userInput.nextInt();
                    System.out.print("Enter x coordinate of destination vertex:");
                    userArgument3 = userInput.nextInt();
                    System.out.print("Enter y coordinate of destination vertex:");
                    userArgument4 = userInput.nextInt();
                    booleanOutcome = map.defineRoad(userArgument1, userArgument2, userArgument3, userArgument4);
                    System.out.println("defineRoad \"" + userArgument1 + "," + userArgument2 + " to " + userArgument3 + "," + userArgument4 + "\" outcome " + booleanOutcome);
                }
                catch (Exception e) {
                    booleanOutcome=false;
                    System.out.println("defineRoad outcome " + booleanOutcome );
                    userCommand = userInput.next();
                }
            } else if (userCommand.equalsIgnoreCase("navigate")) {
                try
                {
                System.out.print("Enter x coordinate of source vertex:");
                userArgument1 = userInput.nextInt();
                System.out.print("Enter y coordinate of source vertex:");
                userArgument2 = userInput.nextInt();
                System.out.print("Enter x coordinate of destination vertex:");
                userArgument3 = userInput.nextInt();
                System.out.print("Enter y coordinate of destination vertex:");
                userArgument4 = userInput.nextInt();
                map.navigate( userArgument1,userArgument2,userArgument3,userArgument4 );
                }
                catch (Exception e) {
                    System.out.println("no path");
                    userCommand = userInput.next();
                }
            } else if (userCommand.equalsIgnoreCase("quit")) {
                System.out.println ("Quit");
            } else {
                System.out.println ("Bad command: " + userCommand);
            }
        } while (!userCommand.equalsIgnoreCase("quit"));

        // The user is done so close the stream of user input before ending.

        userInput.close();
    }
}

