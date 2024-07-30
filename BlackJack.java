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
        System.out.println("Would you like to play Blackjack?(yes, no)(1,2)");
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
        sum += rand;

    }

}