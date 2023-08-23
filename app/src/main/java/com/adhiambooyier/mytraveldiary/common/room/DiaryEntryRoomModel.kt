package com.adhiambooyier.mytraveldiary.common.room

interface DiaryEntryRoomModel<DomainObject> {

    fun toDomainObject(): DomainObject
}
