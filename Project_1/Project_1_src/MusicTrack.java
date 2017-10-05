import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Arrays;

/*
 *  This class encapsulates the data for a track of music, published in an
 *  album.  You can generate a set of these music tracks using the static method
 *  generateMusicLibrary().  You should NOT modify this file.
 */
public class MusicTrack
{
  /* sets up our instance variables. */
  private String name, album, artist;
  private int year, track, rating;

  // simple constructor allows you to provide all of the track details.
  public MusicTrack(String name, String album, int track, String artist, int year, int rating)
  {
    this.name = name;
    this.album = album;
    this.track = track;
    this.artist = artist;
    this.year = year;
    this.rating = rating;
  }

  /*
   * Set up accessors only for each of the track details.  There are no
   * mutators, making this class immutable.
   */

  public String getTrackName() { return name; }
  public String getAlbumName() { return album; }
  public String getArtistName() { return artist; }
  public int getTrackNumber() { return track; }
  public int getYear() { return year; }
  public int getRating() { return rating; }

  // To string puts together a string representation. The stars at the end
  // represent the rating.
  public String toString()
  {
    String stars = "";
    for(int n = 0; n < rating; n++) stars += "*";
    return ""+track+". "+name+" - " + album + "("+year+") - " + artist + " " + stars+"\n";
  }

  /*
   * From here on we set up static methods help build a randomly generated music
   * library.  call generateMusicLibrary() to use this code.
   */

  // Simple band name generator requires first and second band name parts.
  private static List<String> artistNameA = Arrays.asList("Greese", "Ninja","Saturday Morning","Robot",
      "The Dastardly","The Last of the", "The");
  private static List<String> artistNameB = Arrays.asList("Monkeys","Firefighters","Sundance","Barbarians",
      "Kangaroos","Pirates","Fellows");
  // Simple song name generator require first and second song name parts.
  private static List<String> songNameA = Arrays.asList("Never say", "Hail to", "My Forever Love,",
      "You took my", "Give up", "This is our", "Remember me by", "Dreaming of");
  private static List<String> songNameB = Arrays.asList("Tonight", "Freedom", "Hump", "the Alamo", "the Big Cheese",
      "Sleep", "Exile", "Mama");
  // Randomly creates a song name using a random number generator.
  private static String randomSong(Random rng)
  {
    int a = rng.nextInt(songNameA.size());
    int b = rng.nextInt(songNameB.size());
    return songNameA.get(a) + " " + songNameB.get(b);
  }

  // This generates a list of randomly generated music tracks containing a
  // set of artists who each generate a set of albums with a set of songs.
  // the list is returned in a random order.  Do not modify these methods, or
  // your unit tests fill fail.
  public static List<MusicTrack> generateMusicLibrary() { return generateMusicLibrary(0); }

  // This version of the method takes a random seed, allowing you to generate
  // consistant random results.
  public static List<MusicTrack> generateMusicLibrary(int seed)
  {
    // start our result list.
    List<MusicTrack> library = new ArrayList<MusicTrack>();
    // Build a random number generator based on the given seed.
    Random rng = new Random(seed);

    // Copy the artist lists and shuffle them.  This ensures we can't get the
    // same artist parts more than once.
    List<String> artistA = new ArrayList<String>(artistNameA);
    Collections.shuffle(artistA, rng);
    List<String> artistB = new ArrayList<String>(artistNameB);
    Collections.shuffle(artistB, rng);

    // Make 2 to 5 artists
    int num_artists = rng.nextInt(3) + 2;
    for(int artist_i = 0; artist_i < num_artists; artist_i++)
    {
      // Determine the starting year for the artist.
      int year = 1980 + rng.nextInt(30);
      // Build their random name based on the shuffled artist part lists.
      String artist_name = artistA.get(artist_i) + " " + artistB.get(artist_i);

      // make between one and 3 albums for this artist.
      int num_albums = rng.nextInt(3) + 1;
      for(int album_i = 0; album_i < num_albums; album_i++)
      {
        // Song names are good enough for album names
        String album_name = randomSong(rng);

        // make between 3 and 8 songs per album.
        int num_songs = rng.nextInt(6) + 3;
        for(int song_i = 0; song_i < num_songs; song_i++)
        {
          String song_name = randomSong(rng);

          // each song gets a rating between 1 and 5
          int rating = 1 + rng.nextInt(5);

          // build the song!
          library.add(new MusicTrack(song_name, album_name, song_i+1, artist_name, year, rating));
        }
        // wait up to 19 years before releasing a new album
        year += rng.nextInt(20);
        // don't make any albums that aren't finished yet.
        if(year > 2016) break;
      }
    }
    // Shuffle them up so we can't relly on recieving them in any order.
    Collections.shuffle(library, rng);

    // and we are done.
    return library;
  }

}
