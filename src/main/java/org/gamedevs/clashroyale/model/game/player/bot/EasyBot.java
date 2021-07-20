package org.gamedevs.clashroyale.model.game.player.bot;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.Random;

/**
 * easy bot logic
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class EasyBot extends Bot {

    /**
     * Gets main game tools to be able to play
     *
     * @param map           of game
     * @param playerSide    side of player (TOP/DOWN)
     * @param elixir        counter of elixir
     * @param cardGenerator player card generator
     * @param level         level of player
     */
    public EasyBot(Map map, Side playerSide, Elixir elixir, CardGenerator cardGenerator, int level) {
        super(map, playerSide, elixir, cardGenerator, level);
    }

    /**
     * Applies the algorithm of bot! (Easy bot)
     * Decides what to do for next!
     */
    @Override
    protected void algorithm() {
//        pickCard();
    }

    /**
     * pick a random card and put it in a random x,y
     */
    @Override
    protected void pickCard() {
        Thread pickThread = new Thread() {
            @Override
            public void start() {
                Random random = new Random();
                while (true) {

                    DeckContainer deckContainer = new DeckContainer();
                    Card card;
                    do {
                        deckContainer.setDeck(gameDeck.getUnlockCards().getDeck());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (deckContainer.getDeck().size() == 0);
                    card = deckContainer.getRandomCard();
                    float x = random.nextInt(MainConfig.STD_BATTLE_FIELD_WIDTH);
                    float y = random.nextInt(MainConfig.STD_BATTLE_FIELD_HEIGHT / 2);
                    Console.getConsole().printTracingMessage("bot try to put " + card.getCardName() + " in " + x + " , " + y);
                    if (drop(x, y, card))
                        removeCard(card);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        pickThread.start();
    }

}
