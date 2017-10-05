/**
 * Interface for EliminationApp.java
 *
 * Build a model that represents a game similar to duck-duck-goose.
 *
 * @author Jean-Paul Labadie
 * @since November 6, 2015
 */
public interface Elimination{

    /**
     * Initializes a new "game" of elimination, taking in 2 parameters:
     *    - the initial number of players in the circle
     *    - an "elimination number"
     *
     * The simulation is initialized by creating "players" and putting them into a "circle." One
     * of those players is selected as the initial "current player." The "game" is "played" based on the elimination
     * number. A counter is started as "players" are visited in order through the linked list. When the elimination
     * number is reached, the current player is removed from the game. This signifies the end of one turn. A player is
     * eliminated each turn, until one player is left. This last player is the victor, and is not removed, even if
     * further turns are attempted (its good to be king).
     *
     * From a programming perspective, this game is represented using a circularly linked list, where every node in the
     * list also contains a String representing a player name, which is always "player"+ their number.
     * Always start the elimination game at player1, as there is no player0. The tick begins counting at the current
     * player, so that if the elimination number is "1", the current node is always removed.
     *
     * For example, an elimination game initialized with init(3,3) would contain the players:
     * "player1" -> "player2" -> "player3" -->(to 'player1')
     * On the first tick of this game, "player3" is eliminated. On the second tick "player1" is eliminated.
     * On the third tick, "player2" is found to be the winner. Further calls to tick() will return "player2",
     * but "player2" will never be removed. Calls to getCurrentPlayerName() will now return "player2", and calls to
     * currentIsWinner() will now return the Boolean value true.
     *
     * @param numPlayers the number of players, greater than 0
     * @param elimNumber the count used to determine when eliminations occur
     */
    public void init(int numPlayers, int elimNumber);

    /**
     * @return true only if there is a single player left
     */
    public boolean currentIsWinner();

    /**
     * @return the elimination number, the count used for removing players
     */
    public int getEliminationNumber();

    /**
     * Remember that names are always "player"+number
     * Where numbers start at 1 for the initial player
     *
     * @return the name of the current player, starts at "player1"
     */
    public String getCurrentPlayerName();

    /**
     * Runs one cycle of elimination by starting at the "current player", counting off people while going
     * around the circle (think duck duck goose), and removing the person reached when the count reaches
     * the elimination #.  The new "current person" is the person immediately following the one who was
     * eliminated. Return the name of the person removed from the circle on each tick.
     * The last player standing should not be removed.
     *
     * @return the name of the player removed on this tick, or the name of the winner when one player remains
     */
    public String tick();
}