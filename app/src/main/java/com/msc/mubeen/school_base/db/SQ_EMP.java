package com.msc.mubeen.school_base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQ_EMP extends SQLiteOpenHelper{


    private static String DB_NAME = "crme.db";

    protected static final String U_Table_NAME = "user";
    public static final String U_ID = "id";
    public static final String U_user_name = "USER_NAME";
    public static final String U_password = "password";
    public static final String U_email = "email";
    public static final String U_address = "address";
    public static final String U_number = "NUMBER";
    public static final String U_CNIC = "cnic";
    public static final String U_DEGREE = "degree";
    public static final String U_subject = "subject";
    public static final String U_JOB_ACTIVATION = "aactiveation";

    public static final String N_TABLE_NAME = "news";
    public static final String N_ID = "id";
    public static final String N_TITLE = "tittle";
    public static final String N_DETAILS = "details";
    public static final String N_DATE = "ddate";


        public static final String S_Table_Name = "student";
        public static final String S_ID         = "id";
        public static final String S_TID        = "tid";
        public static final String S_NAME       = "name";
        public static final String S_ADDRESS    = "address";
        public static final String S_PHONE      = "phoneno";
        public static final String S_Class      = "sclass";
        public static final String S_PASSWORd   = "password";



        public static final String R_TABLE_NAME = "student_result";
        public static final String R_ID = "id";
        public static final String R_TID = "tid";
        public static final String R_SID = "sid";
        public static final String R_MARKS = "marks";
        public static final String R_GRADE = "grade";
        public static final String R_DATE = "ddate";


        public static final String F_Table = "fee_t";
        public static final String F_S_NAME = "student";
        public static final String F_id = "id";
        public static final String F_fee_status = "status";
        public static final String F_date = "datee";

    //    public static final String S_Table_Name = "student";
//    public static final String S_ID = "id";
//    public static final String S_TID = "t_id";
//    public static final String S_NAME = "name";
//    public static final String S_ADDRESS = "address";
//    public static final String S_PHONE = "Phoneno";
//    public static final String S_Class = "sclass";


    Context C;

    public SQ_EMP(Context context) {
        super(context,DB_NAME, null, 2);
        this.C = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + U_Table_NAME + "( "+
                U_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + U_user_name + " TEXT ,"
                + U_password + " TEXT ,"
                + U_email + " TEXT ,"
                + U_address + " TEXT ,"
                + U_number + " TEXT ,"
                + U_CNIC + " TEXT ,"
                + U_DEGREE + " TEXT ,"
                + U_subject + " TEXT ,"
                + U_JOB_ACTIVATION + " TEXT "
                + ")"

        );

//        public static final String N_TABLE_NAME = "news";
//        public static final String N_ID = "id";
//        public static final String N_TITLE = "tittle";
//        public static final String N_DETAILS = "details";
//        public static final String N_DATE = "ddate";



        sqLiteDatabase.execSQL("create table  news (id INTEGER PRIMARY KEY AUTOINCREMENT,tittle text,details text,ddate text)");
//        public static final String S_Table_Name = "student";
//        public static final String S_ID = "id";
//        public static final String S_TID = "tid";
//        public static final String S_NAME = "name";
//        public static final String S_ADDRESS = "address";
//        public static final String S_PHONE = "phoneno";
//        public static final String S_Class = "sclass";

        sqLiteDatabase.execSQL("create table student (id integer primary key autoincrement,tid text ,name text ,address text,phoneno text,sclass text,password text)");
        //sqLiteDatabase.execSQL("create table student (id INTEGER PRIMARY KEY AUTOINCREMENT,t_id text,name text ,address text , phone_no text,sclass text,password text)");


//        public static final String R_TABLE_NAME = "student_result";
//        public static final String R_ID = "id";
//        public static final String R_TID = "tid";
//        public static final String R_SID = "sid";
//        public static final String R_MARKS = "marks";
//        public static final String R_GRADE = "grade";
//        public static final String R_DATE = "ddate";

        sqLiteDatabase.execSQL("create table student_result (id integer primary key autoincrement ,tid text,sid text,marks integer,grade text,ddate text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ U_Table_NAME);
    }

    public boolean INSERT_NEW_TEACHER_DATA(String user_name ,
                                           String password ,
                                           String email,
                                           String adress,
                                           String number,
                                           String CNIN,
                                           String degree,
                                           String subject
    )
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(U_user_name,user_name);
        contentValues.put(U_password,password);
        contentValues.put(U_address,adress);
        contentValues.put(U_email,email);
        contentValues.put(U_number,number);
        contentValues.put(U_CNIC,CNIN);
        contentValues.put(U_DEGREE,degree);
        contentValues.put(U_subject,subject);
        contentValues.put(U_JOB_ACTIVATION,"0");


        long res = sqLiteDatabase.insert(U_Table_NAME,null,contentValues);
        Toast.makeText(C, res +"   signup", Toast.LENGTH_SHORT).show();
        if (res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public int Activate_EMP_WITH_ID(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Cnic = "'" + Cnic + "'";

        contentValues.put(U_JOB_ACTIVATION,"1");
        int i = sqLiteDatabase.update(U_Table_NAME,contentValues,U_ID + " = ?" ,new String[]{id});
        Toast.makeText(C, "UPDATE = " + i, Toast.LENGTH_SHORT).show();
        return i;

    }
    public Cursor GET_USER_DETAIL()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME,null);
        return  res;
    }
    public Cursor GET_USER_DETAIL_ID(String id)
    {
       // id = "'"+id+"'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_ID + " = " + id,null);
        return  res;
    }


    public int LOG_IN_GET_ID(String user_name , String password)
    {
        user_name = "'" + user_name + "'";
        password = "'" + password + "'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_user_name + " = '" + user_name + "' AND "  +  U_password + " = '"  + password + "'",null);
        Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_user_name + " = " + user_name + " AND "  +  U_password + " = "  + password + " AND " + U_JOB_ACTIVATION  + " = " + "'0'",null);

       // boolean LOGIN_CONFOERMATION = false;
        int conform = -1;
        while(res.moveToNext())
        {
            String db_user_name = res.getString(res.getColumnIndex(U_user_name));
            String db_password  = res.getString(res.getColumnIndex(U_password));
            if (db_user_name.equals(user_name) && db_password.equals(password));
            {
                conform = Integer.parseInt(res.getString(res.getColumnIndex(U_ID)));
                break;
            }
        }


        if (conform== -1)
        {
            return  -1;
        }
        else
        {
            return conform;
        }
    }


    public int LOG_IN_EMP(String user_name , String password)
    {
        user_name = "'" + user_name + "'";
        password = "'" + password + "'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
       // Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_user_name + " = '" + user_name + "' AND "  +  U_password + " = '"  + password + "'",null);
        Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_user_name + " = " + user_name + " AND "  +  U_password + " = "  + password + " AND " + U_JOB_ACTIVATION  + " = " + "1",null);

        boolean LOGIN_CONFOERMATION = false;

        int login_id = 0;

        while(res.moveToNext())
        {
             String db_user_name = res.getString(res.getColumnIndex(U_user_name));
             String db_password  = res.getString(res.getColumnIndex(U_password));
             if (db_user_name.equals(user_name) && db_password.equals(password));
             {
                LOGIN_CONFOERMATION =true;
                login_id = res.getInt(res.getColumnIndex(U_ID));
                break;
             }
        }


        return login_id;
    }

    public void delete_ALL()
    {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ U_Table_NAME);
    }



    //        public static final String N_TABLE_NAME = "news";
    //        public static final String N_ID = "id";
    //        public static final String N_TITLE = "tittle";
    //        public static final String N_DETAILS = "details";
    //        public static final String N_DATE = "ddate";


    public boolean INSERT_NEWS(String title ,String details)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues c = new ContentValues();
        c.put(N_TITLE,title);
        c.put(N_DETAILS,details);
        c.put(N_DATE,getDateTime());

        long res = sqLiteDatabase.insert(N_TABLE_NAME,null,c);
        if (res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor GET_NEWS_DETAIL()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + N_TABLE_NAME,null);
        return  res;
    }

    public Cursor GET_NEWS_DETAIL_WITH_DATE(String date)
    {
        date = "'"+date+"'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + N_TABLE_NAME + "  where ddate = "+date,null);
        return  res;
    }

//    public static final String S_Table_Name = "student";
//    public static final String S_ID = "id";
//    public static final String S_TID = "t_id";
//    public static final String S_NAME = "name";
//    public static final String S_ADDRESS = "address";
//    public static final String S_PHONE = "Phone No";
//    public static final String S_Class = "class";
//    public static final String S_PASSWORD = "password";

    public boolean INSERT_STUDENT(String teacher_id,String s_NAME,String s_ADDRESS,String s_Class,String s_PASSWORd,String s_PHONE)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_TID,teacher_id);
        contentValues.put(S_ADDRESS,s_ADDRESS);
        contentValues.put(S_PASSWORd,s_PASSWORd);
        contentValues.put(S_Class,s_Class);
        contentValues.put(S_NAME,s_NAME);
        contentValues.put(S_PHONE,s_PHONE);

        Long res = sqLiteDatabase.insert(S_Table_Name,null,contentValues);
        if (res>0)
        {
            return true;
        }
        else
        {
            return false;
        }


    }

    public Cursor GET_STUDENT_DETAIL(String taecher_id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + S_Table_Name + " where " + S_TID + " = " + taecher_id ,null);
        return  res;
    }

//    public static final String S_Table_Name = "student";
//    public static final String S_ID         = "id";
//    public static final String S_TID        = "tid";
//    public static final String S_NAME       = "name";
//    public static final String S_ADDRESS    = "address";
//    public static final String S_PHONE      = "phoneno";
//    public static final String S_Class      = "sclass";
//    public static final String S_PASSWORd   = "password";

    public Cursor GET_STUDENT_DETAIL_WITH_PHONE(String parent_phone_number)
    {
        parent_phone_number = "'" + parent_phone_number + "'";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //Cursor res = sqLiteDatabase.rawQuery("select distinct(name),address,phoneno,sclass,password,tid,id from " + S_Table_Name + " where " + S_PHONE + " = " + parent_phone_number ,null);
        Cursor res = sqLiteDatabase.rawQuery("select * from " + S_Table_Name + " where " + S_PHONE + " = " + parent_phone_number + " group by name",null);

        return  res;
    }

    public int UPDATE_PHONE_NO_PARENT(String PASSWORD, String Phone)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Cnic = "'" + Cnic + "'";

        contentValues.put(S_PASSWORd,PASSWORD);
        int i = sqLiteDatabase.update(S_Table_Name,contentValues,S_PHONE + " = ?" ,new String[]{Phone});
        Toast.makeText(C, "UPDATE = " + i, Toast.LENGTH_SHORT).show();
        return i;

    }
    public int LOG_IN_PARENT(String phone , String password)
    {
        phone = "'" + phone + "'";
        password = "'" + password + "'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // Cursor res = sqLiteDatabase.rawQuery("select * from " + U_Table_NAME + " WHERE " + U_user_name + " = '" + user_name + "' AND "  +  U_password + " = '"  + password + "'",null);
        Cursor res = sqLiteDatabase.rawQuery("select * from " + S_Table_Name + " WHERE " + S_PHONE + " = " + phone + " AND "  +  S_PASSWORd + " = "  + password ,null);

        boolean LOGIN_CONFOERMATION = false;

        int login_id = 0;

        while(res.moveToNext())
        {
            String db_user_name = res.getString(res.getColumnIndex(S_PHONE));
            String db_password  = res.getString(res.getColumnIndex(S_PASSWORd));
            if (db_user_name.equals(phone) && db_password.equals(password));
            {
                LOGIN_CONFOERMATION =true;
                login_id = res.getInt(res.getColumnIndex(S_ID));
                break;
            }
        }


        return login_id;
    }



//        public static final String R_TABLE_NAME = "student_result";
//        public static final String R_ID = "id";
//        public static final String R_TID = "tid";
//        public static final String R_SID = "sid";
//        public static final String R_MARKS = "marks";
//        public static final String R_GRADE = "grade";
//        public static final String R_DATE = "ddate";

    public boolean INSERT_RESULT(String tid,String sid,String marks,String grade)
    {

        SQLiteDatabase sqLiteDatabase =getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(R_TID,tid);
        c.put(R_SID,sid);
        c.put(R_MARKS,marks);
        c.put(R_GRADE,grade);
        c.put(R_DATE,getDateTime());

        String table;
        long res =sqLiteDatabase.insert(R_TABLE_NAME, null, c);

        if (res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor GET_REULT_WITH_TAECHER(String tid,String sid)
    {
        sid = "'" +sid+"'";

        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select " + R_GRADE + " , "
                                                           + R_DATE  + " , "
                                                           + R_TID   + " , "
                                                           + R_MARKS + " , "
                                                           + " ( select " + U_subject + " from " + U_Table_NAME + " where "+ U_ID + " ="+ R_TID +" ) as t_subject "
                                                           + " from " + R_TABLE_NAME + " where " + R_TID + " = " + tid +" AND " + R_SID + " = " + sid,null);
        return  res;

    }
    public Cursor GET_REULT_WITH_PARENT(String sid)
    {
        sid = "'" +sid+"'";
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select " + R_GRADE + " , "
                + R_DATE  + " , "
                + R_TID   + " , "
                + R_MARKS + " , "
                + " ( select " + U_subject + " from " + U_Table_NAME + " where "+ U_ID + " ="+ R_TID +" ) as t_subject "
                + " from " + R_TABLE_NAME + " where " + R_SID + " = " + sid,null);
        return  res;

    }

    public Cursor GET_ERSULUT_WITH_STUDENT(String sid)
    {
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + R_TABLE_NAME + " where " + R_SID + " = " + sid,null);
        return  res;
    }





    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
