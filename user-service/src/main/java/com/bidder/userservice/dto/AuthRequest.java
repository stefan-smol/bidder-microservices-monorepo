package com.bidder.userservice.dto;


public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder pattern
    public static AuthRequestBuilder builder() {
        return new AuthRequestBuilder();
    }

    public static class AuthRequestBuilder {
        private String username;
        private String password;

        AuthRequestBuilder() {
        }

		public AuthRequestBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthRequest build() {
            return new AuthRequest(username, password);
        }
    }
}