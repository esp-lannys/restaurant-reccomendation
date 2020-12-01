package spm.project.restaurantrecommendation.dto;
import spm.project.restaurantrecommendation.constraint.FieldMatch;
import spm.project.restaurantrecommendation.constraint.ValidPassword;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Date;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@FieldMatch.List({
        @FieldMatch(first = "password",
                second = "confirmPassword",
                message = "The password fields must match")})
public class UserChangePasswordDto {

    private Long id;

    private String username;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    @ValidPassword
    private String confirmPassword;

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private Timestamp update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        this.update_date = ts;
    }
}
