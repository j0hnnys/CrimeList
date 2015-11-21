package com.bignerdranch.android.criminalintent.database;

/**
 * Created by Johnny on 10/28/15.
 */

/**
 * This class describes the schema of the database
 */
public class CrimeDbSchema {

    // This inner class only exists to define the String constants
    //  needed to describe the moving pieces of your table definition.
    public static final class CrimeTable {
        public static final String NAME = "crimes";


        // Describes the columns of the table
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }



}
