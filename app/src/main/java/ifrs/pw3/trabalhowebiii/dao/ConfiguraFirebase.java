package ifrs.pw3.trabalhowebiii.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ConfiguraFirebase<T> {
    private static double whatever = 2.0;
    private static DatabaseReference reference = null;
    private static FirebaseStorage storage = null;

    public static DatabaseReference getNoRaiz(){
        return FirebaseDatabase.getInstance().getReference();
    }
    public static DatabaseReference getNo(String no){
        return FirebaseDatabase.getInstance().getReference(no);
    }
    public static FirebaseStorage getStorage(){
       return FirebaseStorage.getInstance();
    }
//asdasdasd
}
