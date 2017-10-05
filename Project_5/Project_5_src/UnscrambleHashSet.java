import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Daniel on 12/8/2016.
 */
public class UnscrambleHashSet {
    public static void main(String args[]) throws IOException {
        // create a hash set slightly larger than what we need so we may index properly
        HashSet<String> hashSet = new HashSet<>(178800);

        BufferedReader reader = new BufferedReader(new FileReader("src\\wordlist.txt"));
        //Obtain a line
        String line;

        //While there are lines to read...
        while ((line = reader.readLine()) != null) {
                //add line to set
                hashSet.add(line);
            }
        //close reader so we don't lose stuff in memory.
        reader.close();

        //Lets look for the words:
        System.out.println("Queue: " + hashSet.has("Queue".toUpperCase()));
        System.out.println("Navient: " + hashSet.has("Navient".toUpperCase()));
        System.out.println("Aa: " + hashSet.has("Aa".toUpperCase()));
        System.out.println("Possum: " + hashSet.has("Possum".toUpperCase()));
        System.out.println("Phoney: " + hashSet.has("Phoney".toUpperCase()));
        System.out.println("Bb: " + hashSet.has("Bb".toUpperCase()));
        System.out.println("Werd: " + hashSet.has("Werd".toUpperCase()));
        System.out.println("Titi: " + hashSet.has("Titi".toUpperCase()));
        System.out.println("ZZZ: " + hashSet.has("zzz".toUpperCase()));
    }
}
