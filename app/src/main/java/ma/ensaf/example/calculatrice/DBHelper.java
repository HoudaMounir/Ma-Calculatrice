package ma.ensaf.example.calculatrice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String databaseName = "users.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
        this.getWritableDatabase();
    }
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE user (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT, " +
                "password TEXT, " +
                "nom TEXT, " +
                "prenom TEXT);";
        db.execSQL(createTableQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    }
    public void addUser(String login, String password, String nom, String prenom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("login", login);
        values.put("password", password);
        values.put("nom", nom);
        values.put("prenom", prenom);

        db.insert("user", null, values);
        db.close();
    }

    public void removeUser(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user", "login=?", new String[]{login});
        db.close();
    }
}
