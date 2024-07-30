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
        int rand = (int)(Math.random()*13)+1;
        if(rand == 1)
        {
        }
        while(!finished)
        {

        }
    }

}