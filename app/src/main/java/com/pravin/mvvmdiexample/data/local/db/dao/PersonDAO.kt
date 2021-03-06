package com.pravin.mvvmdiexample.data.local.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.pravin.mvvmdiexample.data.model.db.Person

/**
 * Created by Pravin Divraniya on 10/5/2017.
 */
@Dao
interface PersonDAO {

    @Query("select * from mPerson")
    fun getAllPerson():List<Person>

    @Insert(onConflict = REPLACE)
    fun insertPerson(person: Person)

    @Update(onConflict = REPLACE)
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Query("select * from mPerson where id = :id")
    fun getPerson(id:Long): Person
}