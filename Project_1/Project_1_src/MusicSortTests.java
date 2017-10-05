import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Unit tests to be run after step 4 of the project
public class MusicSortTests
{

  // We will create a set of objects capable of verifying if the lists are
  // sorted in different ways.  This interface allows several ordering types
  // to be built and used via some helper method that checks in terms of
  // this interface.
  public interface OrderValidator<T>
  {
    boolean validateOrder(List<T> list);
  }

  // Validates the ArtistComparator.
  public static class ArtistOrderValidator implements OrderValidator<MusicTrack>
  {
    public boolean validateOrder(List<MusicTrack> list)
    {
      // iterate through each pair in the list
      for(int i = 0; i < list.size()-1; i++)
      {
        MusicTrack a = list.get(i);
        MusicTrack b = list.get(i+1);
        // fail if there is a lower artist above a higher
        if(a.getArtistName().compareTo(b.getArtistName()) > 0) return false;
        // if the artists are also not equal, continue, as this pair is fine.
        if(a.getArtistName().compareTo(b.getArtistName()) < 0) continue;
        // repeat for ablum and track number.
        if(a.getAlbumName().compareTo(b.getAlbumName()) > 0) return false;
        if(a.getAlbumName().compareTo(b.getAlbumName()) < 0) continue;
        if(a.getTrackNumber() > b.getTrackNumber()) return false;
      }
      // if no pairs failed us, we must be properly sorted.
      return true;
    }
  }

  // Validates HotAndNewComparator.
  public static class HotOrderValidator implements OrderValidator<MusicTrack>
  {
    public boolean validateOrder(List<MusicTrack> list)
    {
      for(int i = 0; i < list.size()-1; i++)
      {
        MusicTrack a = list.get(i);
        MusicTrack b = list.get(i+1);
        if(a.getRating() < b.getRating()) return false;
        if(a.getRating() > b.getRating()) continue;
        if(a.getYear() < b.getYear()) return false;
        if(a.getYear() > b.getYear()) continue;
        if(a.getArtistName().compareTo(b.getArtistName()) > 0) return false;
        if(a.getArtistName().compareTo(b.getArtistName()) < 0) continue;
        if(a.getAlbumName().compareTo(b.getAlbumName()) > 0) return false;
        if(a.getAlbumName().compareTo(b.getAlbumName()) < 0) continue;
        if(a.getTrackNumber() > b.getTrackNumber()) return false;
      }
      return true;
    }
  }

  // Validates ChronologicalComparator.
  public static class ChronologicalOrderValidator implements OrderValidator<MusicTrack>
  {
    public boolean validateOrder(List<MusicTrack> list)
    {
      for(int i = 0; i < list.size()-1; i++)
      {
        MusicTrack a = list.get(i);
        MusicTrack b = list.get(i+1);
        if(a.getYear() > b.getYear()) return false;
        if(a.getYear() < b.getYear()) continue;
        if(a.getAlbumName().compareTo(b.getAlbumName()) > 0) return false;
        if(a.getAlbumName().compareTo(b.getAlbumName()) < 0) continue;
        if(a.getTrackNumber() > b.getTrackNumber()) return false;
      }
      return true;
    }
  }

  // The lists need to be split over multiple lines to make sense of the errors.
  private static <T> String multilineListString(List<T> list)
  {
    String result = "[[\n";
    for(int i = 0; i < list.size(); i++)
    {
      result += list.get(i)+"\n";
    }
    result += "]\n";
    return result;
  }

  // Helper method to sort a list using student sorter and comparator, and then
  // validate the results with a validator.
  public static <T> void testComparatorSorter(List<T> list, ISorter sorter, Comparator<T> comp, OrderValidator validator, int expected_steps)
  {
    String sorter_name = sorter.getClass().getName();
    String comparator_name = comp.getClass().getName();

    List<T> original_list = new ArrayList(list);

    int steps = sorter.sort(list, comp);

    Assert.assertTrue(sorter_name + " with "+ comparator_name + " should accurately sort the list " + multilineListString(original_list) + " into order but instead sorted it into " + multilineListString(list), validator.validateOrder(list));
    Assert.assertEquals(sorter_name+" with " + comparator_name + " should be able to sort the list " + multilineListString(original_list) + " in " + expected_steps + " steps, but instead it took "+ steps +" steps.",expected_steps,steps);
  }

  /**
    * Each test generates a set of music tracks and then checks a different
    * sorter/comparator combination.  Feel free to add new tests here to
    * validate the combinations you use in part 5.
    */

  @Test
  public void testCanBubbleSortTracksByArtist()
  {
    ISorter sorter = new BubbleSorter();
    List<MusicTrack> library = MusicTrack.generateMusicLibrary();
    Comparator<MusicTrack> comp = new ArtistComparator();
    OrderValidator<MusicTrack> validator = new ArtistOrderValidator();
    testComparatorSorter(library, sorter, comp, validator, 461);
  }

  @Test
  public void testCanSelectionSortTracksChronological()
  {
    ISorter sorter = new SelectionSorter();
    List<MusicTrack> library = MusicTrack.generateMusicLibrary();
    Comparator<MusicTrack> comp = new ChronologicalComparator();
    OrderValidator<MusicTrack> validator = new ChronologicalOrderValidator();
    testComparatorSorter(library, sorter, comp, validator, 38);
  }

  @Test
  public void testCanInsertionSortTracksHot()
  {
    ISorter sorter = new InsertionSorter();
    List<MusicTrack> library = MusicTrack.generateMusicLibrary();
    Comparator<MusicTrack> comp = new HotAndNewComparator();
    OrderValidator<MusicTrack> validator = new HotOrderValidator();
    testComparatorSorter(library, sorter, comp, validator, 408);
  }
}
