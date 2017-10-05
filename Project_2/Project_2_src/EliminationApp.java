import static java.lang.System.out;

/**
 * Created by Daniel on 10/25/2016.
 */
public class EliminationApp implements Elimination{
    CirLinkedList<Player> game = new CirLinkedList<>();
    private int numPlayers;
    private int elimNumber;

    public void init(int numPlayers, int elimNumber){
        this.numPlayers = numPlayers;
        this.elimNumber = elimNumber;
        game.maxNumNodes = numPlayers;
        for(int i = 1; i <= numPlayers; i++){
            String name = "player" + Integer.toString(i);
            game.addNode(new Node(new Player(name)));
        }
    }
    //returns the last one standing.
    public boolean currentIsWinner(){
        return game.isWinner();
    }

    //retrieves the elimination number to eliminate players.
    public int getEliminationNumber(){
        return this.elimNumber;
    }

    //gets the players name.
    public String getCurrentPlayerName(){
        Node<Player> playerNode = game.getNode(elimNumber);
        Player geoff = playerNode.element;
        //String playerName = geoff.getName();
        return geoff.getName();
    }

    public String toString(){
        String s = "";
        s += game;
        return s;
    }

    //each tick sees if there is a winner or if it needs to remove a player.
    public String tick(){
        int eliNumber = getEliminationNumber();
        Node<Player> eliNode = game.removeNode(eliNumber);
        Player eliPlayer = eliNode.element;
        if(eliPlayer == null){return null;}
        return eliPlayer.getName();
    }

    public static void main(String[] args){
        EliminationApp app = new EliminationApp();
        app.init(10,25);
       /*for(int i=0; i < 10; i++){
            out.println(app);
            out.println("NUM NODES: " + app)
            app.tick();
        }*/

        CirLinkedList c = new CirLinkedList();
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n8 = new Node<>(8);
        Node<Integer> n9= new Node<>(9);
        Node<Integer> n10 = new Node<>(10);

        c.setMax(10);
        out.println(c);
        c.addNode(n1);
        c.addNode(n2);
        c.addNode(n3);
        c.addNode(n4);
        c.addNode(n5);
        c.addNode(n6);
        c.addNode(n7);
        c.addNode(n8);
        c.addNode(n9);
        c.addNode(n10);
        out.println("NUM NODES: " + c.getNumNodes());
        out.println("MAX NODES: " + c.getMaxNumNodes());
        out.println(c);
        c.removeNode(13);
        out.println("NUM NODES: " + c.getNumNodes());
        out.println("MAX NODES: " + c.getMaxNumNodes());
        out.println(c);




    }
}