package net.fununity.friendsystem.database;

import net.fununity.misc.databasehandler.DatabaseHandler;

import java.util.Arrays;

/**
 * A class to load the default tables.
 * @author Niko
 * @since 0.0.1
 */
public class FriendsTableLoader {

    private FriendsTableLoader() {
        throw new UnsupportedOperationException("FriendsTable is a utility class.");
    }

    /**
     * Creates the default tables.
     * @since 0.0.1
     */
    public static void loadDefaultTables() {

        String vChar = "VARCHAR(36) NOT NULL";

        DatabaseHandler dbHandler = DatabaseHandler.getInstance();
        if (!dbHandler.doesTableExist(FriendsRequestsDatabase.TABLE))
            dbHandler.createTable(FriendsRequestsDatabase.TABLE, Arrays.asList("requester", "requested"), Arrays.asList(vChar, vChar));

        if (!dbHandler.doesTableExist(FriendsDatabase.TABLE))
            dbHandler.createTable(FriendsDatabase.TABLE, Arrays.asList("uuid", "friend", "date"), Arrays.asList(vChar, vChar, vChar));

    }


}
