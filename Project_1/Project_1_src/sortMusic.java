import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Daniel on 10/4/2016.
 */
public class sortMusic {
    public static void main(String[] args){
        List<MusicTrack> library = MusicTrack.generateMusicLibrary();

        ISorter b = new BubbleSorter();
        ISorter s = new SelectionSorter();
        ISorter i = new InsertionSorter();

        Comparator<MusicTrack> han = new HotAndNewComparator();
        Comparator<MusicTrack> ac = new ArtistComparator();
        Comparator<MusicTrack> cc = new ChronologicalComparator();

        
        b.sort(library, han);
        System.out.println(library);

        s.sort(library, ac);
        System.out.println(library);

        i.sort(library, cc);
        System.out.println(library);
    }
}
