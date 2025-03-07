package PathCarrer.API.Controller;

import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.Service.userProfile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserProfile userProfile;
    @GetMapping("/Getloby")
    public ResponseEntity Loby (@RequestBody  userDTO userDTO){
        var LobyUser = userProfile.Loby(userDTO);
        return ResponseEntity.ok(LobyUser);
    }


    @PostMapping("/AddPath")
    @Transactional
    public ResponseEntity AddPath (@RequestBody AddPath AddPath){
        userProfile.AddPath(AddPath);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/GetPath")
    @Transactional
    public ResponseEntity GetPath (@RequestBody PathUpdate PathUpdate){
        return ResponseEntity.ok(userProfile.GetPath(PathUpdate));
    }

    @PutMapping("/RemovePath")
    @Transactional
    public ResponseEntity RemovePath (@RequestBody AddPath AddPath){
        userProfile.RemovePath(AddPath);
        return ResponseEntity.ok().build();
    }

}
