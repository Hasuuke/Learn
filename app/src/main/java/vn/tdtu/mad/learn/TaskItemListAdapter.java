package vn.tdtu.mad.learn;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.tdtu.mad.learn.database.Items.TaskItem;



public class TaskItemListAdapter extends RecyclerView.Adapter<TaskItemListAdapter.TaskItemViewHolder> {
    private final LayoutInflater mInflater;
    private List<TaskItem> mTaskItems;
    private  Context context ;
    private final RecyclerViewInterface recyclerViewInterface;
    AppCompatActivity taskActivity ;


    public TaskItemListAdapter(Context context,Activity ac, RecyclerViewInterface recyclerViewInterface) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        taskActivity = (AppCompatActivity) ac;
    }


    @Override
    public TaskItemListAdapter.TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.recyclerview_task_item, parent, false);
        return new TaskItemListAdapter.TaskItemViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(TaskItemListAdapter.TaskItemViewHolder holder, int position) {
        if (mTaskItems != null) {
            TaskItem current = mTaskItems.get(position);
            holder.tvName.setText(current.getmName());
            holder.tvDescription.setText(current.getmDescription());
            holder.tvAmount.setText(current.getmAmount()+ " Credits");

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


    public TaskItem getTaskItem(int position){
        return mTaskItems.get(position);
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
        private final Button btnSolved;
        private TaskItemViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivTaskType = itemView.findViewById(R.id.ivTaskType);
            btnSolved = itemView.findViewById(R.id.btnSolved);

            btnSolved.setOnClickListener(new View.OnClickListener() {
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