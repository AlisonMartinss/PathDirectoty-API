package PathCarrer.API.DTO.ParallelDataDTO;

import PathCarrer.API.Model.User.User;

public record ParallelDataDTO(String userName,String PictureProfile, String BannerProfile) {
    public ParallelDataDTO(User user) {
        this (
          user.getUserName(),
          user.getPictureProfile(),
          user.getBannerProfile());
    }
}
