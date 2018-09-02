package com.anujtayal.test.dagger;


public class GlobalVariables {

    public interface Globals {
        String MAIN_THREAD = "main_thread";
        String NEW_THREAD = "new_thread";
        String UN_AUTHORISED = "un_authorised";
        String AUTHORISED = "authorised";
    }

    public interface ApiKeys {
        String API_KEY = "api-key";
        String EMAIL_ID = "emailId";
        String PASSWORD = "password";
        String SUB_CATEGORY_ID = "subcategoryId";
        String MAGIC_LINK_DATA = "emailData";
        String USER_ID = "userId";
        String LIFECARE_ID = "lifecareId";
    }
}
