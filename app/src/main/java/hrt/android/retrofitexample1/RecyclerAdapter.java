package hrt.android.retrofitexample1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>
{
    String[] heroes;
    String[] images;
    Context ct;

    public RecyclerAdapter(Context ct,String[] heroes, String[] images) {
        this.heroes = heroes;
        this.images = images;
        this.ct = ct;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,
                false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }


    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText(heroes[position]);
        Glide.with(ct).load(images[position]).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return heroes.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvtv);
            imageView = itemView.findViewById(R.id.iviv);
        }
    }
}
