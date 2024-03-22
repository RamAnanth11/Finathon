package com.finathontest.finathon.controller;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.finathontest.finathon.entity.User;
import com.finathontest.finathon.repository.UserRepository;

public class LoginPage extends WebPage {

    @SpringBean
    private UserRepository userRepository;

    public LoginPage() {
        Form<LoginFormModel> form = new Form<>("loginForm", new CompoundPropertyModel<>(new LoginFormModel())) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                LoginFormModel model = getModelObject();

                // Check if username and password are correct
                User user = userRepository.findByUsername(model.getUsername());
                if (user != null && user.getPassword().equals(model.getPassword())) {
                    getSession().setAttribute("user", user);
                    setResponsePage(HomePage.class);
                } else {
                    error("Invalid username or password");
                }
            }
        };

        form.add(new FeedbackPanel("feedback"));
        form.add(new TextField<>("username"));
        form.add(new PasswordTextField("password"));

        add(form);
    }

    private static class LoginFormModel implements Serializable {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
