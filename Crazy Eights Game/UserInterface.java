/*
 * CS 3354 (Software Engineering) Semester Project
 * Spring 2019
 * Team 3
 * Dr. Marcus
 * Our semester project is the card game Crazy Eights implemented in Java.
 */

import java.util.*;

/**
 * This class takes care of all player interactions with the game.
 * @author Matthew Wethington, Martin Boerwinkle, Jonathan Guidry, Yoseph Wordofa
 */
public class UserInterface
{
    private Player currentplayer;
    private DiscardPile discardpile;
    private Deck gamedeck;
    private Scanner in;
    
    public UserInterface(Deck gamedeck, DiscardPile discardpile, Scanner in)
    {
        this.gamedeck = gamedeck;
        this.discardpile = discardpile;
        this.in = in;
    }
    
    public void SetCurrentPlayer(Player player)
    {
        currentplayer = player;
        displayView();
    }

    public void displayView()
    {
        System.out.println("You Have "+currentplayer.GetCardQuantity()+" Cards!");
        ArrayList<Card> currentHand = currentplayer.GetContents();
        int i = 1;
        for(Card c : currentHand)
        {
            System.out.println("Card " + i + ":");
            System.out.println("Rank: "+c.GetRank());
            System.out.println("Suit: "+c.GetSuit());
            i++;
        }
        System.out.println("Discard Pile Has "+discardpile.size()+" Cards!");
        System.out.println("You Must Play A "+discardpile.GetRequiredRank()+" Or A "+discardpile.GetRequiredSuit()+"!");
        System.out.println("Which Card Do You Want To Play? (Specify Using Card #. Type -1 For Draw)");
        boolean done = false;
        while(!done)
        {
            try
            {
                int reqCard = in.nextInt();
                if(reqCard == -1)
                {
                    currentplayer.PullFromDeck(gamedeck);
                    done = true;
                    continue;
                }
                currentplayer.PlayCard(currentHand.get(reqCard-1), discardpile);
                done = true;
            }catch(Exception e)
            {
                System.out.println(e);
                in.nextLine();//Consume the remaining invalid tokens to prevent an infinite error loop
            }
        }
    }
}
