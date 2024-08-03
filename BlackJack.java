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
    int numofcoins;
    String[] allStrings;
    int replaceline;
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

        int counter = 0;
        String temp = "";

        tryCatchIt();
        while(input.hasNext())
        {
            counter ++;
        }

        allStrings = new String[counter+1];

        boolean bool = false;
        tryCatchIt();
        while(input.hasNext())
        {
            temp = input.next();
            allStrings[counter] = temp;
            counter ++;
            if(temp.substring(0, temp.indexOf(' ')).equals(username)&& temp.contains(" "))
            {
                bool = true;
            }
        }

        String tocheck = "";
        while(!bool)
        {
            System.out.print("Please enter your username(Less than 9 characters): ");
            username = scan.next();
            tryCatchIt();
            while(input.hasNext())
            {
                temp = input.next();
                allStrings[counter] = temp;
                counter ++;
                if(temp.substring(0, temp.indexOf(' ')).equals(username)&& temp.contains(" "))
                {
                    tocheck = temp;
                    bool = true;
                }
            }
        }

        System.out.print("Enter your password: ");
        password = scan.next();

        tryCatchIt();
        String tocheck1;
        bool = false;
        tocheck1 = tocheck.trim().substring(0, tocheck.lastIndexOf(' '));
        tocheck = tocheck + " ";
        if (tocheck.substring(tocheck.indexOf(' '), tocheck.lastIndexOf(' ')).trim().equals(password))
        {
            bool = true;
        }
        else 
        {
            bool = false;
        }

        while(!bool)
        {
            System.out.println("That password was incorrect. Please try again: ");
            password = scan.next();

            if (tocheck.substring(tocheck.indexOf(' '), tocheck.lastIndexOf(' ')).trim().equals(password))
            {
                bool = true;
            }
            else 
            {
                bool = false;
            }
        }

        numofcoins = Integer.parseInt(tocheck.trim().substring(tocheck.lastIndexOf(' '), tocheck.length()));
        
        blackJack();
    }
    public void createNewAccountMeth()
    {
        System.out.print("Please enter a username(Less than 20 characters): ");
        username = scan.next();
        while(username.length() > 20)
        {
            System.out.println("That was more than 20 characters");
            System.out.print("Please enter a username(Less than 20 characters): ");
            username = scan.next();
        }
        String temp;
        int counter = 0;
        tryCatchIt();
        while(input.hasNextLine())
        {
            temp = input.nextLine();
            counter++;
            while(temp.substring(0, temp.indexOf(' ')).equals(username) && temp.trim().length() != 1)
            {
                System.out.println("This username has already been taken, choose another username please.");
                System.out.print("Please enter a username(Less than 20 characters): ");
                username = scan.next();
            }
        }

        allStrings = new String[counter+1];

        System.out.print("Enter a password: ");
        password = scan.next();

        numofcoins = 200;
        String stringotappend = username + " " + password + " " + numofcoins;
        append(stringotappend);
        
        while(input.hasNextLine())
        {
            counter ++;
            allStrings[counter] = input.nextLine(); 
        }
        replaceline = counter;
        blackJack();
    }
    //where I put most of the logic
    public void blackJack()
    {
        System.out.print("You have " + numofcoins + " tokens. How many would you like to bet? (Enter a number): ");
        betplace = scan.nextInt();
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
           numofcoins += betplace;
           System.out.println("You now have " + numofcoins + " tokens");
        }

        else if(sum == 21 && (botsum == 21))
        {
           System.out.println("Its a Tie!");
           System.out.println("You now have " + numofcoins + " tokens");
        }

        else if (sum > 21)
        {
            System.out.println("You Lose!");
            numofcoins -= betplace;
            System.out.println("You now have " + numofcoins + " tokens");
        }

        else if (botsum > 21 && sum > 21) 
        {
            System.out.println("You Lose!");
            numofcoins -= betplace;
            System.out.println("You now have " + numofcoins + " tokens");
        }

        else if (botsum > 21 && sum <= 21) 
        {
            System.out.println("Congratulations! You Won!");
            numofcoins += betplace;
            System.out.println("You now have " + numofcoins + " tokens");
        }
        else if (21-sum > 21-botsum)
        {
            System.out.println("You Lose!");
            numofcoins -= betplace;
            System.out.println("You now have " + numofcoins + " tokens");
        }

        else if(21-botsum > 21-sum)
        {
            System.out.println("Congratulations! You Won!");
            numofcoins += betplace;
            System.out.println("You now have " + numofcoins + " tokens");
        }
        int counter;
        counter = 0;
        tryCatchIt();
        while(input.hasNextLine())
        {
            allStrings[counter] = input.nextLine();
            counter ++;
        }

        for(int i = 0; i < allStrings.length; i++)
        {
            if (i == 0)
            {
                write(allStrings[i]);
            }
            else if (i == replaceline) 
            {
                String stringotappend = username + " " + password + " " + numofcoins;
                append(stringotappend);
            }
            else 
            {
                append(allStrings[i]);
            }
        }

        System.out.print("Would you like to play again(yes, no): ");
        String choice = scan.next();

        if(choice.equals("yes"))
        {
            betplace = 0;
            sum = 0;
            botsum = 0;
            rand = 0;
            finished = false;
            numbers = "";
            botnumbers = "X ";
            blackJack();
        }
        else
        {
            input.close();
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
    public void write(String wd)
    {
        pw = null;
        File outFile = new File("Logindata.txt");
        try
        {
            pw = new PrintWriter( new FileWriter(outFile, false) );
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