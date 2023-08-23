package com.adhiambooyier.mytraveldiary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface DiaryEntryDao {

    @Query(
        """
            SELECT *
            FROM entries
            WHERE entries.id = :entryId
            LIMIT 1
        """
    )
    fun getEntryWithId(entryId: Int): Single<DiaryEntry>

    @Query(
        """
            SELECT *
            FROM entries
        """
    )
    fun getEntries(): Single<List<DiaryEntryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(data: List<DiaryEntryModel>)
}
