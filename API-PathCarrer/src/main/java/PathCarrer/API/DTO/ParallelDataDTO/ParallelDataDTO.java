package PathCarrer.API.DTO.ParallelDataDTO;

import PathCarrer.API.Model.User.User;

public record ParallelDataDTO(String PictureProfile, String BannerProfile) {
    public ParallelDataDTO(User user) {
        this (
          user.getPictureProfile(),
          user.getBannerProfile());
    }
}
