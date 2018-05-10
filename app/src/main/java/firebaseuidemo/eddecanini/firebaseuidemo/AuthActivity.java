package firebaseuidemo.eddecanini.firebaseuidemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;

    String uid;

    TextView tvAuthStatus;
    Button btnAuthenticate;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build()
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        referenceViews();
        initFirebaseAuth();
    }

    private void referenceViews() {
        tvAuthStatus = findViewById(R.id.tv_auth_status);
        btnAuthenticate = findViewById(R.id.btn_authenticate);

        btnAuthenticate.setOnClickListener(v -> {
            if (uid == null) { // Launch AuthUI Sign In Intent
                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AuthUIGreenTheme)
                        .build(), RC_SIGN_IN);
            } else { // Sign out using AuthUI
                // Initialise Auth again updating uid, text, and button
                AuthUI.getInstance().signOut(this)
                        .addOnCompleteListener(task -> initFirebaseAuth());
            }
        });
    }

    private void initFirebaseAuth() {
        uid = FirebaseAuth.getInstance().getUid();

        if (uid == null) {
            tvAuthStatus.setText("Not Signed In");
            btnAuthenticate.setText("Launch AuthUI");
        } else {
            tvAuthStatus.setText("Authenticated with Uid " + uid);
            btnAuthenticate.setText("Sign Out");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
            // Initialise Auth again updating uid, text, and button
            initFirebaseAuth();
        }

    }
}
