package spring.scheduler.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    @Nullable //when user first registers in frontend it's null
    private Long id;
    
    @Size(min=2, message = "i18n.not_lt2")
    private String firstName;
    
    @NotEmpty(message = "i18n.empty")
    private String lastName;
    
    @Email(message = "i18n.emailWrong")
    @NotEmpty(message = "i18n.empty")
    private String email;
    
    @Pattern(regexp = "^\\+?380\\d{9}$", message = "i18n.phone")
    private String telNumber;
    
    @NotEmpty(message = "i18n.empty")
    private String password;
    
    @Nullable
    private String role;
    
    @Nullable
    private String serviceType;
}