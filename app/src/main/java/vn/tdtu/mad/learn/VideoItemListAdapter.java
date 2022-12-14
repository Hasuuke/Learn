package vn.tdtu.mad.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import vn.tdtu.mad.learn.database.Items.ShopItem;
import vn.tdtu.mad.learn.database.Items.TaskItem;
import vn.tdtu.mad.learn.database.Items.VideoItem;

import java.util.List;

public class VideoItemListAdapter extends RecyclerView.Adapter<VideoItemListAdapter.VideoItemViewHolder>{
    private final LayoutInflater mInflater;
    private List<VideoItem> mVideoItems;
    private final RecyclerViewInterface recyclerViewInterface;

    public VideoItemListAdapter(Context context, RecyclerViewInterface recyclerViewInterface) {
        mInflater = LayoutInflater.from(context);
        this.recyclerViewInterface =recyclerViewInterface;
    }

    @Override
    public VideoItemListAdapter.VideoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_video_item, parent, false);
        return new VideoItemListAdapter.VideoItemViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(VideoItemListAdapter.VideoItemViewHolder holder, int position) {
        if (mVideoItems != null) {
            VideoItem current = mVideoItems.get(position);
            holder.tvName.setText(current.getName());
            holder.tvDuration.setText(String.valueOf(current.getDuration()));
            holder.tvAuthor.setText(current.getAuthor());
            holder.rbRating.setMax(5);
            holder.rbRating.setRating(current.getRating());

            switch(current.getType()){
                case "Maths":
                    holder.ivVideoType.setBackgroundResource(R.drawable.maths_rv);
                    break;
                case "Biology":
                    holder.ivVideoType.setBackgroundResource(R.drawable.biology_rv);
                    break;
                case "Geology":
                    holder.ivVideoType.setBackgroundResource(R.drawable.geology_rv);
                    break;
                case "English":
                    holder.ivVideoType.setBackgroundResource(R.drawable.english_rv);
                    break;
                default:
                    break;
            }

        } else {
            holder.tvName.setText("No Data");

        }
    }

    public void setVideoItems(List<VideoItem> videoItems){
        mVideoItems = videoItems;
        notifyDataSetChanged();
    }
    public VideoItem getVideoItem(int position){

        return mVideoItems.get(position);
    }




    @Override
    public int getItemCount() {
        if (mVideoItems != null)
            return mVideoItems.size();
        else return 0;
    }

    class VideoItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvDuration;
        private final TextView tvAuthor;
        private final RatingBar rbRating;
        private final ImageView ivVideoType;



        private VideoItemViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            rbRating = itemView.findViewById(R.id.rbRating);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            ivVideoType = itemView.findViewById(R.id.ivVideoType);

            itemView.setOnClickListener(new View.OnClickListener() {
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
