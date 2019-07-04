package top.androidman.superbutton;

import android.support.annotation.AnyRes;

public final class ResourceId {

    /**
     * The {@code null} resource ID.
     */
    public static final @AnyRes int ID_NULL = 0;

    public static boolean isValid(@AnyRes int id) {
        // With the introduction of packages with IDs > 0x7f, resource IDs can be negative when
        // represented as a signed Java int. Some legacy code assumes -1 is an invalid resource ID,
        // despite the existing documentation.
        return id != -1 && (id & 0xff000000) != 0 && (id & 0x00ff0000) != 0;
    }
}