package dbalderas1.a10;

import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Database Driver class for A10
 * @author Diana Balderas
 * @version 1.0
 */
public class Database {

    /**
     * Connection to the database
     * @param db the database to save data from the application
     * @param selectedCollection the data selected to be saved on the database
     * @param item the values on the list
     */
    public static void add(FirebaseFirestore db, String selectedCollection, ListItem item) {
        Map<String, Object> listItem = new HashMap<>();
        listItem.put("item", item);

        db.collection(selectedCollection)
                .add(listItem)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    /**
                     * After success, the data is added to the database
                     * @param documentReference the data is documented based on the user's input
                     */
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("DATABASE", "Item Added: " + documentReference);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    /**
                     * After failure, the data is not added to the database
                     * @param e the data is not documented based on the user's input
                     */
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("DATABASE", "Failed to add item: " + Arrays.toString(e.getStackTrace()));
                    }
                });
            }

    /**
     * Item and description is added and saved to the database
     * @param db the database to save data from the application
     * @param selectedCollection the data selected to be saved in the database
     * @param items the values on the list
     * @param itemsAdapter the values are adapted in the application and database
     */
            public static void getList(FirebaseFirestore db, String selectedCollection,
                                       ArrayList<ListItem> items, ArrayAdapter<ListItem> itemsAdapter) {
                db.collection(selectedCollection)
                        .orderBy("item.dttm")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            /**
                             * Data is completed when the user's input their response
                             * @param task after the items are listed it is documented
                             */
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    for(QueryDocumentSnapshot doc : task.getResult()) {
                                        long dttm = doc.getLong("item.dttm");
                                        String item = doc.getString("item.item");
                                        items.add(new ListItem(dttm, item));
                                    }
                                    itemsAdapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            /**
                             *  After failure, the data is not added to the database
                             * @param e the data is not documented based on the user's input
                             */
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("DATABASE", "Failed to get list: " + Arrays.toString(e.getStackTrace()));
                            }
                        });


    }

    /**
     * Remove the item from the application and database
     * @param db the database to save data from the application
     * @param selectedCollection the data selected to be saved in the database
     * @param items the values on the list
     * @param itemsAdapter the values are adapted in the application and database
     * @param removedItem removes the item from the database and application
     */
    public static void removeItem(FirebaseFirestore db, String selectedCollection
            , ArrayList<ListItem> items, ArrayAdapter<ListItem> itemsAdapter, ListItem removedItem) {
        db.collection(selectedCollection).whereEqualTo("item.dttm", removedItem.getDttm())
                .whereEqualTo("item.item", removedItem.getItem())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot doc : queryDocumentSnapshots) {
                            db.collection(selectedCollection).document(doc.getId()).delete();
                        }
                    }
                });
    }
}
