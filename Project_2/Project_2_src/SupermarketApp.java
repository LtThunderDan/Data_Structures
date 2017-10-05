import java.util.Random;
import java.util.Scanner;

/**
 * Created by Daniel on 10/26/2016.
 */
public class SupermarketApp {
    public static void main(String[] args){
        int numClerks;
        int lineNum;
        Scanner scan = new Scanner(System.in);
        String userInput;
        Random randomNum = new Random();

        System.out.println("Welcome! Press 'c' to continue, or 'q' to quit." );
        while(true){
            userInput = scan.next();

            if(userInput.equalsIgnoreCase("c")) {
                System.out.println("\nEntering Market");
                System.out.println("\nHow many clerks? ");
                numClerks = scan.nextInt();
                System.out.println("\nYou will have: " + numClerks + " clerks.");
                System.out.println("\nHow many customers are allowed in a line?");
                lineNum = scan.nextInt();
                System.out.println("\nYour lines are " + lineNum + " customers long.");

                System.out.println("Let's generate the market!");
                SupermarketCheckouts theMarket = new SupermarketCheckouts();
                theMarket.buildMarket(numClerks, lineNum);
                System.out.println("Here is your market:\n ");
                System.out.println(theMarket);

                while(true){
                    System.out.println("Now, you can add a customer 'a', Tick through 't', display the market 'd' or quit 'q'.");
                    userInput = scan.next();
                    if(userInput.equals("a")){
                        theMarket.addCustomer((randomNum.nextInt(5)+1));
                    }
                    else if(userInput.equals("t")){
                        theMarket.tick();
                    }
                    else if(userInput.equals("d")){
                        System.out.println(theMarket);
                    }
                    else if(userInput.equals("q")){
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid character.");
                        continue;
                    }
                }
                System.out.println("Your final market looks like: \n");
                System.out.println(theMarket);
            }
        break;
        }
    }
}
