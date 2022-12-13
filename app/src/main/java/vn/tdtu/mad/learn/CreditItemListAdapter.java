package vn.tdtu.mad.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.database.Items.CreditItem;


import java.util.List;

public class CreditItemListAdapter extends RecyclerView.Adapter<CreditItemListAdapter.CreditItemViewHolder> {
    private final LayoutInflater mInflater;
    private List<CreditItem> mCreditList;


    public CreditItemListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CreditItemListAdapter.CreditItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_credit_item, parent, false);
        return new CreditItemListAdapter.CreditItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CreditItemListAdapter.CreditItemViewHolder holder, int position) {
        if (mCreditList != null) {
            CreditItem current = mCreditList.get(position);
            holder.tvName.setText(current.getName());
            switch(current.getType()){
                case "MC_DONALDS":
                    holder.ivType.setBackgroundResource(R.drawable.mcdonalds);
                    holder.tvAmount.setText(String.valueOf("-" +current.getAmount())+ " Credits");

                    break;
                case "BURGER_KING":
                    holder.ivType.setBackgroundResource(R.drawable.burger_king);
                    holder.tvAmount.setText(String.valueOf("-" +current.getAmount())+ " Credits");

                    break;
                case "FORTNITE":
                    holder.ivType.setBackgroundResource(R.drawable.fortnite);
                    holder.tvAmount.setText(String.valueOf("-" +current.getAmount())+ " Credits");

                    break;
                case "MATHS":
                    holder.ivType.setBackgroundResource(R.drawable.maths_rv);
                    holder.tvAmount.setText(String.valueOf("+" +current.getAmount())+ " Credits");


                    break;
                case "ENGLISH":
                    holder.ivType.setBackgroundResource(R.drawable.english_rv);
                    holder.tvAmount.setText(String.valueOf("+" +current.getAmount())+ " Credits");


                    break;
                case "GEOLOGY":
                    holder.ivType.setBackgroundResource(R.drawable.geology_rv);
                    holder.tvAmount.setText(String.valueOf("+" +current.getAmount())+ " Credits");


                    break;
                case "BIOLOGY":
                    holder.ivType.setBackgroundResource(R.drawable.biology_rv);
                    holder.tvAmount.setText(String.valueOf("+" +current.getAmount())+ " Credits");

                    break;
                default:
                    break;
            }

        } else {
            // Covers the case of data not being ready yet.
            holder.tvName.setText("No Data");
            holder.tvAmount.setText("No Data");

        }
    }

    public void setCreditItems(List<CreditItem> shopItems){
        mCreditList = shopItems;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCreditList != null)
            return mCreditList.size();
        else return 0;
    }

    class CreditItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvAmount;
        private final ImageView ivType;

        private CreditItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivType = (ImageView) itemView.findViewById(R.id.ivCreditType);
        }
    }
}
