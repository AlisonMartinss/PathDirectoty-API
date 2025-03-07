package PathCarrer.API.DTO.UsersDTO;

import PathCarrer.API.Model.User.MyPathsAdd;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Model.User.messages;

import java.util.List;

public record LobyDTO(String userName, String PictureProfile, String BannerProfile, List<MyPathsAdd> myPaths, List<messages> messages) {


    public LobyDTO(User user) {
        this(   user.getUsername(),
                user.getPictureProfile(),
                user.getBannerProfile(),
                user.getMyPaths(),
                user.getMessages());
    }
}
