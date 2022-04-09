package com.karetolabs.cinemapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Favorite::class
    ],
    version = 2,
    exportSchema = true
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao


    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "MovieDatabase"
                )
                    .addCallback(MovieDatabaseCallback())
                    .addMigrations(Migration1To2())
                    .build()
                INSTANCE = instance
                instance
            }
        }


        private class MovieDatabaseCallback() : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.favoriteDao())
                    }
                }
            }
        }

        private suspend fun populateDatabase(favoriteDao: FavoriteDao) {
            val favorite = Favorite(
                id = 1,
                title = "Matrix",
                urlImage = "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg",
                summary = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam scelerisque, leo sed feugiat auctor, lacus neque fringilla nibh, a blandit lacus dolor et arcu. Nam viverra magna vel quam faucibus, vitae dictum diam molestie. Integer sed augue ut nulla interdum mattis. Fusce sodales, dolor eg",
                year = "1999",
                genre = "Sci-Fi",
                duration = "120"
            )
            favoriteDao.insert(favorite)
        }

    }
}