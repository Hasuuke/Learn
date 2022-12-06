package vn.tdtu.mad.learn;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskItem taskItem);

    @Query("DELETE FROM task_items")
    void deleteAll();

    @Query("SELECT * FROM task_items")
    LiveData<List<TaskItem>> getAllTaskItems();
}
