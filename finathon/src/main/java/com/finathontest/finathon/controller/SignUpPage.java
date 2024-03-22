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

public class SignUpPage extends WebPage {

    @SpringBean
    private UserRepository userRepository;

    public SignUpPage() {
        Form<SignUpFormModel> form = new Form<>("signUpForm", new CompoundPropertyModel<>(new SignUpFormModel())) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                SignUpFormModel model = getModelObject();

                // Check if username already exists
                User existingUser = userRepository.findByUsername(model.getUsername());
                if (existingUser != null) {
                    error("Username already exists. Please choose a different username.");
                    return;
                }

                // Create a new user
                User newUser = new User();
                newUser.setUsername(model.getUsername());
                newUser.setPassword(model.getPassword());

                // Save the new user to the database
                userRepository.save(newUser);

                // Redirect to login page after successful sign up
                setResponsePage(LoginPage.class);
            }
        };

        form.add(new FeedbackPanel("feedback"));
        form.add(new TextField<>("username"));
        form.add(new PasswordTextField("password"));

        add(form);
    }

    private static class SignUpFormModel implements Serializable {
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
