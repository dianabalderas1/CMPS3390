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
 * Precautions Adapter Driver class for A15
 * @author Diana Balderas
 * @version 1.0
 */
public class PrecautionsAdapter extends RecyclerView.Adapter<PrecautionsAdapter.PrecautionsViewHolder> {
    String data3[], data4[];
    int p_images[];
    Context context;

    /**
     * Every precaution topic is listed based on the amount of information
     * @param ct the context that layout of the precaution topics
     * @param p3 it displays the title of the topic
     * @param p4 it displays the description of the topic
     * @param img it displays the image of the topic
     */
    public PrecautionsAdapter(Context ct, String p3[], String p4[], int img[]) {
        context = ct;
        data3 = p3;
        data4 = p4;
        p_images = img;
    }

    /**
     * The list of precaution topics is laid out in rows
     * @param parent the main view where the topics are laid out
     * @param viewType the type of view that is designed to structure the topics in rows
     * @return the topics are displayed in rows
     */
    @NonNull
    @Override
    public PrecautionsAdapter.PrecautionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.precautions_row, parent,false);
        return new PrecautionsAdapter.PrecautionsViewHolder(view);
    }

    /**
     * The list of rows are hold for the new activity that displays them individually
     * @param holder holds the information stored in the view to recycle the context
     * @param position fixes the height and with of the images, title, and description
     */
    @Override
    public void onBindViewHolder(@NonNull PrecautionsAdapter.PrecautionsViewHolder holder, int position) {
        holder.precautionsText1.setText(data3[position]);
        holder.precautions_descriptionText2.setText(data4[position]);
        holder.precautionsView.setImageResource(p_images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            /**
             * When a row is clicked, a new activity is opened with the information of the topic
             * @param v the design view of the button
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PrecautionActivity.class);
                intent.putExtra("data3", data3[position]);
                intent.putExtra("data4", data4[position]);
                intent.putExtra("precautionsView", p_images[position]);
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
        return p_images.length;
    }

    /**
     * The context is recycled for the other activities that will use precaution topics
     */
    public class PrecautionsViewHolder extends RecyclerView.ViewHolder {

        TextView precautionsText1, precautions_descriptionText2;
        ImageView precautionsView;
        ConstraintLayout mainLayout;

        /**
         * It holds the precaution topics and how it could be displayed
         * @param itemView Each item is viewed based on the information it contains
         */
        public PrecautionsViewHolder(@NonNull View itemView) {
            super(itemView);
            precautionsText1 = itemView.findViewById(R.id.precautions_txt);
            precautions_descriptionText2 = itemView.findViewById(R.id.precautions_description_txt);
            precautionsView = itemView.findViewById(R.id.precautions_imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
