package kmitl.lab09.paniti58070080.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MoneyInfoDAO {

    @Insert
    void insertInfo(MoneyInfo data);

    @Query("SELECT * FROM MoneyInfo")
    List<MoneyInfo> queryAll();

}
