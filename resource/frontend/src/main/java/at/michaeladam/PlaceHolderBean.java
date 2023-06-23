package at.michaeladam;

import jakarta.inject.Singleton;

@Singleton
public class PlaceHolderBean {

    /**
     * This is a placeholder bean generate a classes directory
     * It is not used in the application.
     */
    public String ping() {
        return "pong";
    }
}
