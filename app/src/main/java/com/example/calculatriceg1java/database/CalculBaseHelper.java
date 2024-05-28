package com.example.calculatriceg1java.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class CalculBaseHelper extends DatabaseHelper {
    public CalculBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, version);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS " + CalculDao.tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                CalculDao.premierElement + " INTEGER NOT NULL," +
                CalculDao.deuxiemeElement + " INTEGER NOT NULL," +
                CalculDao.symbol + " VARCHAR(5) not null," +
                CalculDao.resultat + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return "DROP TABLE "+CalculDao.tableName;
    }
}
