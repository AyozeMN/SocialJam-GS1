package gs1.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos extends SQLiteOpenHelper {

    public static final String USERS_TABLE = "USERS_TABLE";
    public static final String COLUMN_USER_USUARIO = "USER_USUARIO";
    public static final String COLUMN_USER_NOMBRE = "USER_NOMBRE";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASS = "USER_PASS";
    public static final String COLUMN_USER_ADMIN = "USER_ADMIN";
    public static final String COLUMN_ID = "ID";

    public BaseDeDatos(@Nullable Context context) {
        super(context, "socialjam.db", null, 1);
    }

    //Se crea la primera vez que la bbdd es accedida, tiene que haber codigo para crear la nueva bbdd
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE " + USERS_TABLE + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_USER_USUARIO + " TEXT, "
                        + COLUMN_USER_NOMBRE + " TEXT, "
                        + COLUMN_USER_EMAIL + " TEXT, "
                        + COLUMN_USER_PASS + " TEXT, "
                        + COLUMN_USER_ADMIN + " BOOL)";

        db.execSQL(createTableStatement);
    }

    //Se llama si el num version de la bbdd cambia, previene la rotura de la bbdd si los usuarios anteriores cambian
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_USUARIO, user.getUsuario());
        cv.put(COLUMN_USER_NOMBRE, user.getNombre());
        cv.put(COLUMN_USER_EMAIL, user.getEmail());
        cv.put(COLUMN_USER_PASS, user.getPass());
        cv.put(COLUMN_USER_ADMIN, user.isAdmin());

        long insert = db.insert(USERS_TABLE, null, cv);
        return insert != -1;
    }

    public void deleteOne(User user) {
        //Busca el registro y si lo encuentra lo borra
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USERS_TABLE + " WHERE " + COLUMN_ID + " = " + user.getId();

        Cursor cursor = db.rawQuery(queryString, null);
    }

    public Cursor checkLogin(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM " + USERS_TABLE
                        + " WHERE " + COLUMN_USER_USUARIO + " = '" + user.toLowerCase() +"' AND "
                        + COLUMN_USER_PASS + " = '" + pass +"'",
                null
        );

        //return c.getCount() > 0;
        return c;
    }

    public List<User> getEveryone() {
        List<User> returnList = new ArrayList<>();

        //obtener los usuarios
        String queryString = "SELECT * FROM " + USERS_TABLE;

        //Solo leer para q no se bloquee la bbdd (cuando escribes se bloquea)
        SQLiteDatabase db = this.getReadableDatabase();

        //tmb se puede usar db.execSQL(); void
        //cursor rawQuery
        Cursor cursor = db.rawQuery(queryString, null);

        //controlar que se ejecuto la orden query
        if (cursor.moveToFirst()) {
            //crea objetos de usuario en la lista mientras haya
            do {
                int userID = cursor.getInt(0);
                String userUsuario = cursor.getString(1);
                String userNombre = cursor.getString(2);
                String userEmail = cursor.getString(3);
                String userPass = cursor.getString(4);
                boolean userAdmin = cursor.getInt(5) == 1;

                User newUser = new User(userID, userUsuario, userNombre, userEmail, userPass, userAdmin);
                returnList.add(newUser);

            } while (cursor.moveToNext());
        }  //Fallo no pone nada


        //Cerramos conexiones a la bbdd
        cursor.close();
        db.close();

        return returnList;
    }

    public List<Marcador> getMarcadores(String type) {
        List<Marcador> returnList = new ArrayList<>();
        String tabla = type + "_TABLA";
        //Obtener los usuarios
        String queryString = "SELECT * FROM " + tabla;

        //Solo leer para q no se bloquee la bbdd (cuando escribes se bloquea)
        SQLiteDatabase db = this.getReadableDatabase();

        //tmb se puede usar db.execSQL(); void
        //cursor rawQuery
        Cursor cursor = db.rawQuery(queryString, null);

        //controlar que se ejecuto la orden query
        if (cursor.moveToFirst()) {
            //crea objetos de usuario en la lista mientras haya
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                double lat = cursor.getDouble(2);
                double lng = cursor.getDouble(3);
                String titulo = cursor.getString(4);
                String nivel = cursor.getString(5);

                Marcador newMarcador = new Marcador(id, nombre, lat, lng, titulo, nivel);
                returnList.add(newMarcador);

            } while (cursor.moveToNext());
        }  //Fallo no pone nada

        //Cerramos conexiones a la bbdd
        cursor.close();
        db.close();

        return returnList;
    }

    public List<Marcador> getEverything(){
        List<Marcador> returnEverything = new ArrayList<>();

        String queryString = "SELECT * " +
                "FROM BIBLIOTECAS_TABLA";/* +
                "CROSS JOIN CINES_TABLA";/* +
                "CROSS JOIN COMIDARAPIDA_TABLA" +
                "CROSS JOIN CORREOS_TABLA, GASOLINERAS_TABLA, GIMNASIOS_TABLA, " +
                "MUSEOS_TABLA, PLAYAS_TABLA, TIENDAS_TABLA";*/

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                double lat = cursor.getDouble(2);
                double lng = cursor.getDouble(3);
                String titulo = cursor.getString(4);
                String nivel = cursor.getString(5);

                Marcador newMarcador = new Marcador(id, nombre, lat, lng, titulo, nivel);
                returnEverything.add(newMarcador);
            } while (cursor.moveToNext());
        }

        //Cerramos conexiones a la bbdd
        cursor.close();
        db.close();

        return returnEverything;
    }

}
