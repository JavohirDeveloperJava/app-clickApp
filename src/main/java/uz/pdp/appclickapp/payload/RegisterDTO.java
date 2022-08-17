package uz.pdp.appclickapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {
    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
