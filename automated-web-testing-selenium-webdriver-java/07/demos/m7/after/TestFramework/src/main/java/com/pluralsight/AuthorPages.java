package com.pluralsight;

public class AuthorPages {

    static String url = "http://www.pluralsight.com/authors";
    static String title = "Authors | Pluralsight";

    public void goTo() {
        Browser.goTo(url);
    }

    public boolean isAt() {
        return Browser.title().equals(title);
    }
}
