package vn.tdtu.mad.learn;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.tdtu.mad.learn.Screens.CaptureImage;
import vn.tdtu.mad.learn.Screens.CreditsActivity;
import vn.tdtu.mad.learn.Screens.TaskActivity;


public class TaskItemListAdapter extends RecyclerView.Adapter<TaskItemListAdapter.TaskItemViewHolder> {
    private final LayoutInflater mInflater;
    private List<TaskItem> mTaskItems; // Cached copy of words
    private  Context context ;
    AppCompatActivity taskActivity ;
    public TaskItemListAdapter(Context context,Activity ac) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        taskActivity = (AppCompatActivity) ac;
    }


    @Override
    public TaskItemListAdapter.TaskItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.recyclerview_task_item, parent, false);
        return new TaskItemListAdapter.TaskItemViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(TaskItemListAdapter.TaskItemViewHolder holder, int position) {
        if (mTaskItems != null) {
            TaskItem current = mTaskItems.get(position);
            holder.tvName.setText(current.getmName());
            holder.tvDescription.setText(current.getmDescription());
            holder.tvAmount.setText(current.getmAmount()+ " Credits");
            holder.btnSolved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CaptureImage.class);
                    context.startActivity(intent);
                }
            });

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

    public interface RecyclerviewClickListener{
        void onClick(View v, int position);
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
        private TaskItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            ivTaskType = itemView.findViewById(R.id.ivTaskType);
            btnSolved = itemView.findViewById(R.id.btnSolved);
        }


    }
}