package com.platzi.randomcats.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.platzi.randomcats.core.database.dao.CatDao
import com.platzi.randomcats.core.database.model.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1
)
abstract class CatDatabase: RoomDatabase() {
    abstract val catDao: CatDao
}
