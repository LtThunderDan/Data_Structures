import java.util.Comparator;

/**
 * Created by Daniel on 10/4/2016.
 */
public class ChronologicalComparator<T extends MusicTrack> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        int cv = 0;
        if(o1.getYear()>o2.getYear()){
            cv = 1;
        }
        else if(o1.getYear()<o2.getYear()){
            cv = -1;
        }
        else if(o1.getYear()==o2.getYear()){
            if(o1.getAlbumName().compareToIgnoreCase(o2.getAlbumName())>0){
                cv = 1;
            }
            else if(o1.getAlbumName().compareToIgnoreCase(o2.getAlbumName())<0){
                cv = -1;
            }
            else if(o1.getAlbumName().compareToIgnoreCase(o2.getAlbumName())==0){
                if(o1.getTrackNumber()>o2.getTrackNumber()){
                    cv = 1;
                }
                else if(o1.getTrackNumber()<o2.getTrackNumber()){
                    cv = -1;
                }
                else if(o1.getTrackNumber()== o2.getTrackNumber()){
                    cv = 0;
                }
            }
        }
        return cv;
    }
}
