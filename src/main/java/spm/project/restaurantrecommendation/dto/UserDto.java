package spm.project.restaurantrecommendation.dto;

import spm.project.restaurantrecommendation.constraint.FieldMatch;
import spm.project.restaurantrecommendation.constraint.ValidPassword;

import javax.validation.constraints.NotEmpty;

@FieldMatch.List({
        @FieldMatch(first = "password",
                second = "confirmPassword",
                message = "The password fields must match")})
public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    @ValidPassword
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
