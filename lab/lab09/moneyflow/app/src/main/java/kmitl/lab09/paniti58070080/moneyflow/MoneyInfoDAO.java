package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MoneyInfoDAO {

    @Insert
    void insertInfo(MoneyInfo data);

    @Query("SELECT * FROM MoneyInfo")
    List<MoneyInfo> queryAll();

    @Query("DELETE FROM MoneyInfo WHERE id LIKE :pos")
    int delete(int pos);

    @Update
    void updateData(MoneyInfo data);
}
