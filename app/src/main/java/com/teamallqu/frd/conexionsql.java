package com.teamallqu.frd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class conexionsql extends SQLiteOpenHelper {
    public conexionsql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(utilidades.CREAR_TABLAFICHA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionantigua, int versionnueva) {
    db.execSQL("DROP TABLE IF EXISTS FICHA");
    onCreate(db);
    }
}
