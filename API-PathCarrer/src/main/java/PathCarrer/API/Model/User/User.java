package PathCarrer.API.Model.User;

import PathCarrer.API.Model.Path.Path;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Document(collection = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String worldID;

    @Indexed(unique = true)
    private String userName;

    private String password;

    private String PictureProfile;

    private String BannerProfile;

    private String Desc;

    private List<insignia> InsigniaON;

    private List<insignia> InsigniaStorage;

    private HashMap<String,MyPathsAdd> myPaths;

    private List<messages> Messages;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.myPaths = new HashMap<>();
    }

    public void AddMyPaths(Path newPath) {
        var newAddPath = new MyPathsAdd();
        newAddPath.MyPathsAddC(newPath.getModulos().size());
        this.myPaths.put(newPath.getId(),newAddPath);
    }

    /* ===== Getters & Setters ====== */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorldID() {
        return worldID;
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

    public List<insignia> getInsigniaStorage() {
        return InsigniaStorage;
    }

    public String getUserName() {
        return userName;
    }

    public HashMap<String,MyPathsAdd> getMyPaths() {
        return myPaths;
    }

    public List<messages> getMessages() {
        return Messages;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
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
