package firebaseuidemo.eddecanini.firebaseuidemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DatabaseActivity extends AppCompatActivity {

    RecyclerView listDatabase;
    FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        referenceViews();
        queryList();
    }

    private void referenceViews() {
        listDatabase = findViewById(R.id.list_database);
    }

    private void queryList() {
        // Create the query and the FirebaseRecyclerOptions
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("demos")
                .child("firebaseui")
                .orderByChild("name");

        FirebaseRecyclerOptions<Person> options = new FirebaseRecyclerOptions.Builder<Person>()
                .setQuery(query, Person.class)
                .build();

        // Create the RecyclerViewAdapter
        adapter = new FirebaseRecyclerAdapter<Person, PersonHolder>(options) {
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

        listDatabase.setAdapter(adapter);
        listDatabase.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
