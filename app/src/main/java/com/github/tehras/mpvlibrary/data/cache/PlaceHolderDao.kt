package com.github.tehras.mpvlibrary.data.cache

import android.arch.persistence.room.*
import com.github.tehras.mpvlibrary.data.models.PlaceHolder
import io.reactivex.Maybe

@Dao
interface PlaceHolderDao {

    @Query("SELECT * FROM Placeholder WHERE id = :position")
    fun get(position: Int): Maybe<PlaceHolder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(o: PlaceHolder): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg l: PlaceHolder)

    @Delete
    fun delete(item: PlaceHolder)

}