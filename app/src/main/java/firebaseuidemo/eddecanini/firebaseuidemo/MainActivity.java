package firebaseuidemo.eddecanini.firebaseuidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View lytStorage, lytAuth, lytDatabase, lytFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenceViews();
        addIntents();
    }

    private void referenceViews() {
        lytStorage = findViewById(R.id.lyt_storage);
        lytAuth = findViewById(R.id.lyt_auth);
        lytDatabase = findViewById(R.id.lyt_database);
        lytFirestore = findViewById(R.id.lyt_firestore);
    }

    private void addIntents() {
        lytStorage.setOnClickListener(v -> startActivity(new Intent(this, StorageActivity.class)));
        lytAuth.setOnClickListener(v -> startActivity(new Intent(this, AuthActivity.class)));
        lytDatabase.setOnClickListener(v -> startActivity(new Intent(this, DatabaseActivity.class)));
        lytFirestore.setOnClickListener(v -> startActivity(new Intent(this, FirestoreActivity.class)));
    }

}
