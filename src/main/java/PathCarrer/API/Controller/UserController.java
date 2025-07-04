package PathCarrer.API.Controller;

import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.Update.AddSeeClassDTO;
import PathCarrer.API.DTO.UsersDTO.NoteDTO;
import PathCarrer.API.DTO.UsersDTO.UpdateProfileName;
import PathCarrer.API.DTO.UsersDTO.UserEasyAspects;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.ExeptionsClasses.NotFound;
import PathCarrer.API.Model.MyStandardsResponde.GlobalCatch;
import PathCarrer.API.Service.userProfile.Password;
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
    public ResponseEntity Loby (@RequestParam String username){
        var LobyUser = userProfile.Loby(username);
        return ResponseEntity.ok(LobyUser);
    }


    @PostMapping("/AddPathID")
    @Transactional
    public ResponseEntity AddPath (@RequestBody AddPath AddPath){
        userProfile.AddPath(AddPath);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/RemovePath")
    @Transactional
    public ResponseEntity RemovePath (@RequestBody AddPath path){
        userProfile.RemovePath(path);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/GetPath")
    public ResponseEntity GetPath (@RequestParam String PathID){
        return ResponseEntity.ok(userProfile.GetPath(PathID));
    }
    @PutMapping("/NewName")
    @Transactional
    public ResponseEntity NewName (@RequestBody UpdateProfileName UpdateProfileName){
        userProfile.NewName(UpdateProfileName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/UpdatePictureProfile")
    @Transactional
    public ResponseEntity PictureProfile (@RequestBody UserEasyAspects userEasyAspects){
        userProfile.UpdatePictureProfile(userEasyAspects);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/UpdateDesc")
    @Transactional
    public ResponseEntity UpdateDesc (@RequestBody UserEasyAspects userEasyAspects){
        userProfile.UpdateDesc(userEasyAspects);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/NewPassword")
    @Transactional
    public ResponseEntity NewPassword (@RequestBody Password Password){
        userProfile.NewPassword(Password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/DeleteProfile")
    @Transactional
    public ResponseEntity DeleteProfile (@RequestBody Password Password){
        userProfile.DeleteProfile(Password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/AddSeeClass")
    @Transactional
    public ResponseEntity AddSeeClass (@RequestBody AddSeeClassDTO addSeeClassDTO){
        userProfile.AddSeeClass(addSeeClassDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/RemoveSeeClass")
    @Transactional
    public ResponseEntity RemoveSeeClass (@RequestBody AddSeeClassDTO addSeeClassDTO){
        userProfile.RemoveSeeClass(addSeeClassDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/AddNote")
    @Transactional
    public ResponseEntity AddNote(@RequestBody NoteDTO noteDTO){
        userProfile.AddNote(noteDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/RemoveNote")
    @Transactional
    public ResponseEntity RemoveNote (@RequestBody NoteDTO noteDTO){
        userProfile.RemoveNote(noteDTO);
        return ResponseEntity.ok().build();
    }
}
