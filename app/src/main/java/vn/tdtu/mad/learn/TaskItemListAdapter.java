package vn.tdtu.mad.learn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TaskItemListAdapter extends RecyclerView.Adapter<TaskItemListAdapter.TaskItemViewHolder> {

    private final LayoutInflater mInflater;
    private List<TaskItem> mTaskItems; // Cached copy of words

    public TaskItemListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TaskItemListAdapter.TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_task_item, parent, false);
        return new TaskItemListAdapter.TaskItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskItemListAdapter.TaskItemViewHolder holder, int position) {
        if (mTaskItems != null) {
            TaskItem current = mTaskItems.get(position);
            holder.tvName.setText(current.getmName());
            holder.tvDescription.setText(current.getmDescription());
            holder.tvAmount.setText(String.valueOf(current.getmAmount()));

            switch(current.getmType()){
                case MATHS:
                    holder.ivTaskType.setBackgroundResource(R.drawable.maths_rv);
                    break;
                case ENGLISH:
                    holder.ivTaskType.setBackgroundResource(R.drawable.english_rv);
                    break;
                case GEOLOGY:
                    holder.ivTaskType.setBackgroundResource(R.drawable.geology_rv);
                    break;
                case BIOLOGY:
                    holder.ivTaskType.setBackgroundResource(R.drawable.biology_rv);
                    break;
                default:
                    break;
            }

        } else {
            holder.tvName.setText("No Data");
            holder.tvAmount.setText("No Data");
            holder.tvDescription.setText("No Data");
        }
    }

    public void setTaskItems(List<TaskItem> taskItems){
        mTaskItems = taskItems;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mTaskItems != null)
            return mTaskItems.size();
        else return 0;
    }


    class TaskItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvDescription;
        private final TextView tvAmount;
        private final ImageView ivTaskType;

        private TaskItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivTaskType = itemView.findViewById(R.id.ivTaskType);
        }
    }
}