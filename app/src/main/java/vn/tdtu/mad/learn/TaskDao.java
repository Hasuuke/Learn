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

    @Query("SELECT * FROM task_items WHERE Solved == :isSolved")
    LiveData<List<TaskItem>> getAllTaskItems(Boolean isSolved);

    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved  ")
    LiveData<List<TaskItem>> getAllMathItems(TaskTypes type, Boolean isSolved );

    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllBiologyItems(TaskTypes type, Boolean isSolved);
    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllEnglishItems(TaskTypes type, Boolean isSolved);
    @Query("SELECT * FROM task_items WHERE Type == :type AND Solved == :isSolved")
    LiveData<List<TaskItem>> getAllGeologyItems(TaskTypes type, Boolean isSolved);
}
