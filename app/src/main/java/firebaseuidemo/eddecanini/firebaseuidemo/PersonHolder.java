package firebaseuidemo.eddecanini.firebaseuidemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PersonHolder extends RecyclerView.ViewHolder {

    TextView nameView;
    TextView descriptionView;

    public PersonHolder(View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.tv_name);
        descriptionView = itemView.findViewById(R.id.tv_description);
    }
}
