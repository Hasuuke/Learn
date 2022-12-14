package vn.tdtu.mad.learn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import vn.tdtu.mad.learn.Screens.MapsActivity;
import vn.tdtu.mad.learn.database.Items.ShopItem;

import java.util.List;

public class ShopItemListAdapter extends RecyclerView.Adapter<ShopItemListAdapter.ShopItemViewHolder> {
    Context context;
    private final LayoutInflater mInflater;
    private List<ShopItem> mShopItems; // Cached copy of words

    public ShopItemListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context); }

    @Override
    public ShopItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_shop_item, parent, false);
        return new ShopItemViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ShopItemViewHolder holder, int position) {
        if (mShopItems != null) {
            ShopItem current = mShopItems.get(position);
            holder.tvName.setText(current.getmName());
            holder.tvAmount.setText(current.getmAmount() + " Credits");
            holder.tvMising.setText("20 Credits missing");
            holder.tvOffer.setText(current.getmOffer());
            holder.btnRedeemed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MapsActivity.class);
                    context.startActivity(intent);
                }
            });
            switch(current.getmShopType()){
                case MC_DONALDS:
                    holder.ivShopType.setBackgroundResource(R.drawable.mcdonalds);
                    break;
                case BURGER_KING:
                    holder.ivShopType.setBackgroundResource(R.drawable.burger_king);
                    break;
                case FORTNITE:
                    holder.ivShopType.setBackgroundResource(R.drawable.fortnite);
                    break;
                default:
                    break;
            }

        } else {
            // Covers the case of data not being ready yet.
            holder.tvName.setText("No Data");
            holder.tvAmount.setText("No Data");
            holder.tvMising.setText("No Data");
            holder.tvOffer.setText("No Data");
        }
    }

    public void setShopItems(List<ShopItem> shopItems){
        mShopItems = shopItems;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mShopItems != null)
            return mShopItems.size();
        else return 0;
    }

    class ShopItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvOffer;
        private final TextView tvAmount;
        private final TextView tvMising;
        private final ImageView ivShopType;
        private final Button btnRedeemed;
        private ShopItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvOffer = itemView.findViewById(R.id.tvOffer);
            tvMising = itemView.findViewById(R.id.tvMissing);
            ivShopType = itemView.findViewById(R.id.ivShopType);
            btnRedeemed = itemView.findViewById(R.id.btnRedeemed);
        }
    }
}