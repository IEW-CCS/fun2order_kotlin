package com.iew.fun2order.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.iew.fun2order.db.dao.*
import com.iew.fun2order.db.entity.*


/*
@Database(
    entities = [TodoEntity::class, TaskEntry::class],
    version = 1
)*/


@Database(
    entities = [entityFriend::class, entityGroup::class, entityGroup_detail::class, MenuType::class, UserMenu::class
        , Location::class, Product::class,  entityNotification::class, entityUserProfile::class, entityLocalmage::class], version = 3
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun groupdao(): groupDAO
    abstract fun groupdetaildao():group_detailDAO
    abstract fun menutyoedao(): MenuTypeDAO
    abstract fun usermenudao(): UserMenuDAO
    abstract fun locationdao(): LocationDAO
    abstract fun productdao(): ProductDAO
    abstract fun localImagedao(): localImageDAO

    abstract fun notificationdao(): notificationDAO
    abstract fun userprofiledao(): userprofileDAO

   // abstract fun TaskDao(): TaskDao


    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("CREATE TABLE `entityLocalmage` (`id` INTEGER, `name` TEXT UNIQUE NOT NULL,  `desc` TEXT NOT NULL,  `image` BLOB NOT NULL, " + "PRIMARY KEY(`id`))")
                    database.execSQL("CREATE UNIQUE INDEX  index_entityLocalmage_name ON `entityLocalmage`('name')")
                }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "Fun2Order.db")
            .allowMainThreadQueries()
            .addMigrations(MIGRATION_2_3)
            .fallbackToDestructiveMigration()
            .build()
    }
}