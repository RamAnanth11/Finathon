package com.finathontest.finathon.controller;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends WebPage {

    public HomePage() {
        add(new Label("welcomeMessage", "Hello, Welcome!"));
    }
}
