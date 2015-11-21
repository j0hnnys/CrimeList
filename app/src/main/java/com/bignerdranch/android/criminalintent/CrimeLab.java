package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.criminalintent.database.CrimeBaseHelper;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable.Cols.UUID;

/**
 * Singleton class that holds all the data
 * for every crime.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>();
    }

    public Crime getCrime(UUID id) {
        return null;
    }

    public void updateCrime(Crime crime) {
        // get values you want to update
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        // **we use '?' instead of 'WHERE' to prevent SQL injection attacks
        mDatabase.update(CrimeTable.NAME,       // name of table you wish to update
                values,                         // the data (ContentValues) you want to update to the table
                CrimeTable.Cols.UUID + " = ?",  // specifies a 'WHERE' clause
                new String[] { uuidString });   // values for the arguments in an SQL 'WHERE' clause
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        // the first argument specifies the table you want to insert into
        // the second argument is hardly used (don't bother with it)
        // the third argument are is the data you want to insert
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    /**
     * Imports the info about a Crime object into a ContentValues object
     * @param crime the crime to import information from
     * @return a ContentValues object with the information of a crime
     */
    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();

        // In this case, keys = column names
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle().toString());
        values.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }
}
