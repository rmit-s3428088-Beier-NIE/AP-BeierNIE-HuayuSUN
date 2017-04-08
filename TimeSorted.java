//Wrote by Huayu Sun, this class is to compare who ranked the first.
import java.util.Comparator;

public class TimeSorted implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		MatchResult mr1 = (MatchResult) o1;
		MatchResult mr2 = (MatchResult) o2;
		if (mr1.getTime() >= mr2.getTime()) {
			return 1;
		}else{
		return -1;}
	}

}
