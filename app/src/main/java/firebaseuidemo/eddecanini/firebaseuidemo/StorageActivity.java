package firebaseuidemo.eddecanini.firebaseuidemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class StorageActivity extends AppCompatActivity {

    TextView tvLoadStatus;
    ImageView ivPanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        referenceViews();
        loadImage();
    }

    private void referenceViews() {
        tvLoadStatus = findViewById(R.id.tv_load_status);
        ivPanda = findViewById(R.id.iv_panda);
    }

    private void loadImage() {
        // Load the StorageReference into the ImageView
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference pandaRef = storage.getReference()
                .child("demos").child("FirebaseUI").child("panda.png");

        GlideApp.with(this)
                .load(pandaRef)
                .placeholder(R.drawable.loading)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        tvLoadStatus.setText("Error loading image: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        tvLoadStatus.setText("Image loaded in onCreate");
                        return false;
                    }
                })
                .into(ivPanda);
    }

}
