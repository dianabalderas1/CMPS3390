package dbalderas1.a15;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;

    public SymptomsAdapter(Context ct, String s1[], String s2[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.symptoms_row, parent,false);
        return new SymptomsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        holder.symptomsText1.setText(data1[position]);
        holder.descriptionText2.setText(data2[position]);
        holder.symptomsView.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SymptomActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("symptomsView", images[position]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class SymptomsViewHolder extends RecyclerView.ViewHolder {

        TextView symptomsText1, descriptionText2;
        ImageView symptomsView;
        ConstraintLayout mainLayout;

        public SymptomsViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomsText1 = itemView.findViewById(R.id.symptoms_txt);
            descriptionText2 = itemView.findViewById(R.id.description_txt);
            symptomsView = itemView.findViewById(R.id.symptoms_imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
