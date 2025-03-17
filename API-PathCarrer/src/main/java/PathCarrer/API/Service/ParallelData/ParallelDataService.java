package PathCarrer.API.Service.ParallelData;


import PathCarrer.API.DTO.ParallelDataDTO.ParallelDataDTO;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParallelDataService {
    @Autowired
    private UserRepository userRepository;

    public ParallelDataDTO GetProfilePics (String worldID){
        var User = userRepository.findByWorldID(worldID);
        return new ParallelDataDTO(User);
    }
}
