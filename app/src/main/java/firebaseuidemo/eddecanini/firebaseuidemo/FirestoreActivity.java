package firebaseuidemo.eddecanini.firebaseuidemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreActivity extends AppCompatActivity {

    RecyclerView listFirestore;
    FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);

        referenceViews();
        queryList();
    }

    private void referenceViews() {
        listFirestore = findViewById(R.id.list_firestore);
    }

    private void queryList() {
        // Create the query and the FirestoreRecyclerOptions
        Query query = FirebaseFirestore.getInstance()
                .collection("demos")
                .document("firebaseui")
                .collection("people")
                .orderBy("name");

        FirestoreRecyclerOptions<Person> options = new FirestoreRecyclerOptions.Builder<Person>()
                .setQuery(query, Person.class)
                .build();

        // Create the RecyclerViewAdapter
        adapter = new FirestoreRecyclerAdapter<Person, PersonHolder>(options) {
            @NonNull
            @Override
            public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_name_and_description, parent, false);

                return new PersonHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PersonHolder holder, int position, @NonNull Person model) {
                holder.nameView.setText(model.getName());
                holder.descriptionView.setText(model.getDescription());
            }
        };

        listFirestore.setAdapter(adapter);
        listFirestore.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
