package com.adhiambooyier.mytraveldiary.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adhiambooyier.mytraveldiary.common.OpenForTesting
import com.adhiambooyier.mytraveldiary.common.room.DiaryEntryRoomModel

@Entity(
    tableName = "entries"
)
@OpenForTesting
data class DiaryEntryModel(
    @PrimaryKey val id: String,
    @ColumnInfo val createdDate: String,
    @ColumnInfo val location: String,
    @ColumnInfo val travelDate: String,
    @ColumnInfo val notes: String,
    @ColumnInfo val title: String
) : DiaryEntryRoomModel<DiaryEntry> {
    constructor(entry: DiaryEntry) : this(
        id = entry.id,
        createdDate = entry.createdDate,
        location = entry.location,
        travelDate = entry.travelDate,
        notes = entry.notes,
        title = entry.title
    )

    override fun toDomainObject(): DiaryEntry {
        return DiaryEntry(
            id = id,
            createdDate = createdDate,
            location = location,
            travelDate = travelDate,
            notes = notes,
            title = title
        )
    }
}
