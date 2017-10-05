/**
 * Created by Daniel on 10/26/2016.
 */
public class Player {
    private String name;
    public Player(String name){
        this.name = name;
    }

    public void createName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }
}
