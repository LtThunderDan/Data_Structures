import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Unit tests to be run after step 3 of the project
public class ComparatorTests
{

  /**
    * Each unit test mearnly builds two tracks and checks to see if the
    * comparator can yeild the correct result.
    **/

  @Test
  public void testCanArtistCompare()
  {
    Comparator<MusicTrack> comp = new ArtistComparator();
    MusicTrack a = new MusicTrack("SongA","AlbumA", 1, "ArtistA", 2000, 3);
    MusicTrack b = new MusicTrack("SongB","AlbumB", 2, "ArtistB", 2001, 4);
    Assert.assertTrue("ArtistComparator does not properly sort the songs "+a+" and "+b+".",comp.compare(a,b)<0);
  }

  @Test
  public void testCanChonologicalCompare()
  {
    Comparator<MusicTrack> comp = new ChronologicalComparator();
    MusicTrack a = new MusicTrack("SongA","AlbumA", 1, "ArtistA", 2000, 3);
    MusicTrack b = new MusicTrack("SongB","AlbumB", 2, "ArtistB", 2001, 4);
    Assert.assertTrue("ChronologicalComparator does not properly sort the songs "+a+" and "+b+".",comp.compare(b,a)>0);
  }

  @Test
  public void testCanHotAndNewCompare()
  {
    Comparator<MusicTrack> comp = new HotAndNewComparator();
    MusicTrack a = new MusicTrack("SongA","AlbumA", 1, "ArtistA", 2000, 3);
    MusicTrack b = new MusicTrack("SongB","AlbumB", 2, "ArtistB", 2001, 4);
    Assert.assertTrue("HotAndNewComparator does not properly sort the songs "+a+" and "+b+".",comp.compare(a,b)>0);
  }

}
