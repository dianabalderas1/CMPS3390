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

/**
 * Symptoms Adapter Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {
    String data1[], data2[];
    int images[];
    Context context;

    /**
     *  Every symptom topic is listed based on the amount of information
     * @param ct the context that layout of the symptom topics
     * @param s1 it displays the title of the topic
     * @param s2 it displays the description of the topic
     * @param img it displays the image of the topic
     */
    public SymptomsAdapter(Context ct, String s1[], String s2[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    /**
     * The list of symptom topics is laid out in rows
     * @param parent the main view where the topics are laid out
     * @param viewType the type of view that is designed to structure the topics in rows
     * @return the topics are displayed in rows
     */
    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.symptoms_row, parent,false);
        return new SymptomsViewHolder(view);
    }


    /**
     * The list of rows are hold for the new activity that displays them individually
     * @param holder holds the information stored in the view to recycle the context
     * @param position fixes the height and with of the images, title, and description
     */
    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
        holder.symptomsText1.setText(data1[position]);
        holder.descriptionText2.setText(data2[position]);
        holder.symptomsView.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            /**
             * When a row is clicked, a new activity is opened with the information of the topic
             * @param v the design view of the button
             */
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

    /**
     * Count the amount of the images in the array
     * @return the images needed to be displayed
     */
    @Override
    public int getItemCount() {
        return images.length;
    }

    /**
     * The context is recycled for the other activities that will use symptom topics
     */
    public class SymptomsViewHolder extends RecyclerView.ViewHolder {

        TextView symptomsText1, descriptionText2;
        ImageView symptomsView;
        ConstraintLayout mainLayout;

        /**
         * It holds the symptom topics and how it could be displayed
         * @param itemView Each item is viewed based on the information it contains
         */
        public SymptomsViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomsText1 = itemView.findViewById(R.id.symptoms_txt);
            descriptionText2 = itemView.findViewById(R.id.description_txt);
            symptomsView = itemView.findViewById(R.id.symptoms_imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
