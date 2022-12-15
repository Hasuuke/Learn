package vn.tdtu.mad.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.Screens.ShopActivity;
import vn.tdtu.mad.learn.database.Items.ShopItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ShopItemListAdapter extends RecyclerView.Adapter<ShopItemListAdapter.ShopItemViewHolder> {
    Context context;
    private final LayoutInflater mInflater;
    private List<ShopItem> mShopItems;
    private final RecyclerViewInterface recyclerViewInterface;


    public ShopItemListAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        mInflater = LayoutInflater.from(context);
        this.recyclerViewInterface =recyclerViewInterface;
        this.context = context;
    }

    @Override
    public ShopItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_shop_item, parent, false);
        return new ShopItemViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(ShopItemViewHolder holder, int position) {
        if (mShopItems != null) {
            ShopItem current = mShopItems.get(position);
            holder.tvName.setText(current.getmName());
            holder.tvAmount.setText(String.valueOf(current.getmAmount())+ " Credits");
            holder.tvOffer.setText(current.getmOffer());

            double result = current.getmAmount() -ShopActivity.result;
            if(result<=0){


                holder.tvMising.setText("Enough Credits");

            }
            else{
                BigDecimal bd = new BigDecimal(result);
                bd.setScale(2, RoundingMode.HALF_UP);
                holder.tvMising.setText( bd.doubleValue() +" Credits missing");
            }


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
            holder.tvName.setText("No Data");
            holder.tvAmount.setText("No Data");
            holder.tvMising.setText("No Data");
            holder.tvOffer.setText("No Data");
        }

        holder.layout.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.anim_rv));

    }

    public void setShopItems(List<ShopItem> shopItems){
        mShopItems = shopItems;
        notifyDataSetChanged();
    }

    public ShopItem getShopItem(int pos){
        return mShopItems.get(pos);
    }


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
        private final Button btnRedemeed;
        private final LinearLayout layout;

        private ShopItemViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvOffer = itemView.findViewById(R.id.tvOffer);
            tvMising = itemView.findViewById(R.id.tvMissing);
            ivShopType = itemView.findViewById(R.id.ivShopType);
            btnRedemeed = itemView.findViewById(R.id.btnRedeemed);
            layout = itemView.findViewById(R.id.shopLayout);


            btnRedemeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface !=null){
                        int pos = getAdapterPosition();
                        if(pos !=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}