package by.itacademy.user.core.dto;

import by.itacademy.user.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDetailsImpl implements UserDetails {

    private String uuid;
    private String mail;
    private String fio;
    private Collection<? extends GrantedAuthority> role;
    private String status;
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equalsIgnoreCase("ACTIVATED");
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equalsIgnoreCase("ACTIVATED");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equalsIgnoreCase("ACTIVATED");
    }

    @Override
    public boolean isEnabled() {
        return status.equalsIgnoreCase("ACTIVATED");
    }

    public static UserDetails build(UserEntity userEntity) {

        return new UserDetailsImpl()
                .setUuid(userEntity.getUuid())
                .setMail(userEntity.getMail())
                .setFio(userEntity.getFio())
                .setRole(List.of(new SimpleGrantedAuthority(userEntity.getRole())))
                .setStatus(userEntity.getStatus())
                .setPassword(userEntity.getPassword());
    }
}
