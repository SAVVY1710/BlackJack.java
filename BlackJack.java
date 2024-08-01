/*
 * @author: Sai Avula
 * @since: 7.29.2024
 * @nameofproject: BlackJack.java
 * @purpose: Game of Blackjack
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BlackJack
{
    //field variables
    int botsum;
    int sum;
    String numbers;
    String botnumbers;
    int rand;
    boolean finished;
    Scanner scan;
    int betplace;
    String username;
    Scanner input;
    PrintWriter pw;
    String password;
    // Constructor where I initialize variables
    public BlackJack()
    {
        betplace = 0;
        sum = 0;
        botsum = 0;
        rand = 0;
        finished = false;
        numbers = "";
        botnumbers = "X ";
        scan = new Scanner(System.in);
        username = "";
        password = "";
    }
    //In the main I create an instance of this class and run runIt
    public static void main(String[] args) {
        BlackJack bj = new BlackJack();
        bj.runIt();
    }
    
    //Class called on by the main which runs the program
    public void runIt()
    {
        System.out.print("Would you like to play Blackjack? Yes(1) or No(2): ");
        int choice = scan.nextInt();
        if (choice == 1) 
        {
            System.out.print("Do you already have an account? Yes(1) or No(2): ");
            choice = scan.nextInt();
            if (choice == 1) 
            {
                existingAccountMeth();
            }   
            else if (choice == 2)
            {
                createNewAccountMeth();
            }
        }
        
    }
    public void existingAccountMeth()
    {
        System.out.print("Please enter your username(Less than 9 characters): ");
        username = scan.next();
        blackJack();
    }
    public void createNewAccountMeth()
    {
        System.out.print("Please enter a username(Less than 9 characters): ");
        username = scan.next();

        blackJack();
    }
    //where i put most of the logic
    public void blackJack()
    {
        generatenum();
        while(!finished)
        {
            generatebotnum();
            generatenum();

            if(!finished)
            {
                System.out.println("Here are the dealers cards: " + botnumbers);
                System.out.print("Here are your cards: " + numbers);
                System.out.println("= " + sum);

                System.out.println();

                System.out.print("Would you like to hit(1), or stand(2): ");
                int num = scan.nextInt();
                if(num == 2)
                {
                    finished = true;
                    finishmeth();
                }
            }
        }
    }

    //generates a number for the bot
    public void generatebotnum()
    {
        int rand = (int)(Math.random()*13)+1;
        if(rand == 1)
        {
            botnumbers += "A ";
        }
        else if(rand == 11)
        {
            botnumbers += "J ";
        }
        else if(rand == 12)
        {
            botnumbers += "Q ";
        }
        else if(rand == 13)
        {
            botnumbers += "K ";
        }
        else
        {
            botnumbers += rand + " ";
        }
        botsum += rand;

    }
    //generates a number for the player.
    public void generatenum()
    {
        int rand = (int)(Math.random()*13)+1;
        sum += rand;
        if(rand == 1)
        {
            numbers += "A ";
        }
        else if(rand == 11)
        {
            numbers += "J ";
        }
        else if(rand == 12)
        {
            numbers += "Q ";
        }
        else if(rand == 13)
        {
            numbers += "K ";
        }
        else
        {
            numbers += rand + " ";
        }

        if(sum > 21)
        {
            finishmeth();
            finished = true;
        }

    }
    public void finishmeth()
    {
        generatebotnum();
        botnumbers = botnumbers.substring(2,botnumbers.length());
        System.out.print("Here are the dealers cards: " + botnumbers);
        System.out.println("= " + botsum);
        System.out.print("Here are your cards: " + numbers);
        System.out.println("= " + sum);
        System.out.println();
        if(sum == 21 && !(botsum == 21))
        {
           System.out.println("Congratulations! You Won!"); 
        }

        else if(sum == 21 && (botsum == 21))
        {
           System.out.println("Its a Tie!");
        }

        else if (sum > 21)
        {
            System.out.println("You Lose!");
        }

        else if (botsum > 21 && sum > 21) 
        {
            System.out.println("You Lose!");
        }

        else if (botsum > 21 && sum <= 21) 
        {
            System.out.println("Congratulations! You Won!"); 
        }
        else if (21-sum > 21-botsum)
        {
            System.out.println("You Lose!");
        }

        else if(21-botsum > 21-sum)
        {
            System.out.println("Congratulations! You Won!"); 
        }

    }
    public void tryCatchIt()
    {
        File inFile = new File ("Logindata.txt");
        String inFileName = "Logindata.txt";
        //value = "";
        input = null;
        try
        {
            input = new Scanner ( inFile );
        }
        catch ( FileNotFoundException e )
        {
            System.err.println("Cannot find " + inFileName + " file.");
            System.exit(1);
        }
    }
    public void append(String wd)
    {
        pw = null;
        File outFile = new File("Logindata.txt");
        try
        {
            pw = new PrintWriter( new FileWriter(outFile, true) );
        }
        catch (IOException e)
        {
            System.err.println("Cannot append to " +  " Logindata.txt");
            System.exit(1);
        }
        pw.println(wd);
        pw.close();
    }

}