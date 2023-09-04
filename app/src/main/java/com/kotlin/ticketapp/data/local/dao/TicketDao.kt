package com.kotlin.ticketapp.data.local.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.ticketapp.data.local.entities.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(client: Client)

    @Query(
        """
            SELECT * 
            FROM Clients
            WHERE clientId=:id
            LIMIT 1
        """
    )
    suspend fun find(id : Int) : Client

    @Delete
    suspend fun delete(client: Client)

    @Query("SELECT * FROM Clients")
    fun getAll(): Flow<List<Client>>
}