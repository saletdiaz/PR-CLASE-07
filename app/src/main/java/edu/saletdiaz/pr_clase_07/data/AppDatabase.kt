package edu.saletdiaz.pr_clase_07.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.saletdiaz.pr_clase_07.model.Comic
import edu.saletdiaz.pr_clase_07.model.Editorial
import edu.saletdiaz.pr_clase_07.ui.components.AppScaffold

@Database(
    entities = [Editorial::class, Comic::class],
    version = 1,
    exportSchema =true
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun editorialsDAO():EditorialsDAO
    abstract fun comicsDAO(): ComicsDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Editorials.db"
                ).fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}