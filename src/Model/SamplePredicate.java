package Model;
import java.util.function.*;

public class SamplePredicate<T> implements Predicate<T>{
    T varc1;
    public boolean test(T varc){
        if(varc1.equals(varc)){
            return false;
        }
        return true;
    }
}