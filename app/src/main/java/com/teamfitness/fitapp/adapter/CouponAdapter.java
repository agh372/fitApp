package com.teamfitness.fitapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamfitness.fitapp.R;
import com.teamfitness.fitapp.model.Coupon;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {
    private Coupon[] mDataset;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView labelText;
        public TextView costDrop;
        public ImageView coverImage;
        public CouponViewHolder(View v) {
            super(v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CouponAdapter(Coupon[] myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CouponAdapter.CouponViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coupon_items, parent, false);
        CouponViewHolder vh = new CouponViewHolder(view);
        vh.labelText = (TextView) view.findViewById(R.id.label_txt);
        vh.costDrop = (TextView) view.findViewById(R.id.card_desc_value);
        vh.coverImage = (ImageView) view.findViewById(R.id.card_image);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CouponViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.labelText.setText(mDataset[position].getType());
        holder.costDrop.setText("$ "+ mDataset[position].getPriceDiscount());
        Picasso.get().load(getDrawableIdFromFileName(context,mDataset[position].getImageUrl()))
                .error(R.drawable.panda)
                .into(holder.coverImage);

    }

    public static int getDrawableIdFromFileName(Context context, String nameOfDrawable) {
        return context.getResources().getIdentifier(nameOfDrawable, "drawable", context.getPackageName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
