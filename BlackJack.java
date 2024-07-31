/*
 * @author: Sai Avula
 * @since: 7.29.2024
 * @nameofproject: BlackJack.java
 * @purpose: Game of Blackjack
 */

import java.util.Scanner;

public class BlackJack
{
    int botsum;
    int sum;
    String numbers;
    String botnumbers;
    int rand;
    boolean finished;
    Scanner scan;
    public BlackJack()
    {
        numbers = "";
        botnumbers = "";
        scan = new Scanner(System.in);
    }
    public static void main(String[] args) {
        BlackJack bj = new BlackJack();
        bj.runIt();
    }
    public void runIt()
    {
        System.out.print("Would you like to play Blackjack? Yes(1) or No(2): ");
        int choice = scan.nextInt();
        if (choice == 1) 
        {
            blackJack();
        }
    }
    public void blackJack()
    {
        botnumbers = "X ";
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
           System.out.println("Good Job you Won!"); 
        }

        if(sum == 21 && (botsum == 21))
        {
           System.out.println("Its a Tie!");
        }

        if (sum > 21)
        {
            System.out.println("You Lose!");
        }

    }

}