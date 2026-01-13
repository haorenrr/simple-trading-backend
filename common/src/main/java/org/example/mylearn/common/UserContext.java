package org.example.mylearn.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;

public class UserContext implements AutoCloseable{
    private static final ThreadLocal<String> CURRENT_USER_ID = new ThreadLocal<>();
    Logger logger = LoggerFactory.getLogger(UserContext.class);

    public UserContext(String userId) {
        CURRENT_USER_ID.set(userId);
    }

    public static String getUserId(){
        return CURRENT_USER_ID.get();
    }

    /**
     * Get current user id, or throw exception if no user.
     */
    public static String getRequiredUserId() {
        String userId = getUserId();
        if (userId == null) {
            throw new InvalidPropertyException(UserContext.class, "userId", "No UserId! Need to signin first.");
        }
        return userId;
    }

    @Override
    public void close() {
        try {
            logger.trace("calling UserContext.close()");
            CURRENT_USER_ID.remove();
        }catch (Exception e){
            logger.warn("Clean ThreadLocal exception:", e);
        }
    }
}
