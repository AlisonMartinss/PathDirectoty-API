package PathCarrer.API.Model.User;

import PathCarrer.API.DTO.UsersDTO.userDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "Users")
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class User implements UserDetails {


    @Id
    private String userName;

    private String worldID;

    private String password;

    private String PictureProfile;

    private String BannerProfile;

    private List<insignia> InsigniaON;

    private List<insignia> InsigniaStorage;

    private MyPathsAdd myPaths;

    private List<messages> Messages;

    public static Object getLogin;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /* ===== Getters & Setters ====== */


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorldID() {
        return worldID;
    }

    public void setWorldID(String worldID) {
        this.worldID = worldID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureProfile() {
        return PictureProfile;
    }

    public void setPictureProfile(String pictureProfile) {
        PictureProfile = pictureProfile;
    }

    public String getBannerProfile() {
        return BannerProfile;
    }

    public void setBannerProfile(String bannerProfile) {
        BannerProfile = bannerProfile;
    }


    public List<insignia> getInsigniaON() {
        return InsigniaON;
    }

    public void setInsigniaON(List<insignia> insigniaON) {
        InsigniaON = insigniaON;
    }

    public List<insignia> getInsigniaStorage() {
        return InsigniaStorage;
    }

    public void setInsigniaStorage(List<insignia> insigniaStorage) {
        InsigniaStorage = insigniaStorage;
    }

    public MyPathsAdd getMyPaths() {
        return myPaths;
    }

    public void setMyPaths(MyPathsAdd myPaths) {
        this.myPaths = myPaths;
    }

    public List<messages> getMessages() {
        return Messages;
    }

    public void setMessages(List<messages> messages) {
        Messages = messages;
    }

    /* ===== UserDetails ==== */


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
