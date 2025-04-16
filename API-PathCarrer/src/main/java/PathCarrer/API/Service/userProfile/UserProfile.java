package PathCarrer.API.Service.userProfile;


import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.GetsPathByUserDTO.PathGetDTO;
import PathCarrer.API.DTO.Update.AddSeeClassDTO;
import PathCarrer.API.DTO.UsersDTO.LobyDTO;
import PathCarrer.API.DTO.UsersDTO.NoteDTO;
import PathCarrer.API.DTO.UsersDTO.UpdateProfileName;
import PathCarrer.API.DTO.UsersDTO.UserEasyAspects;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *   MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO USUARIO E SUA CONTA.
 *
 *   - Loby: Trazer a tona as informções a respeito da sua conta.
 *   - AddPathID: Adiciona Path.
 *   - RemovePath: Remove Path da lista de path do usuario.
 *   - GetPath: Traz informações sobre o path quando selecionado.
 *   - NewName: Atualiza nome de usuario.
 *   - UpdatePictureProfile: Atualização do foto de perfil e/ou banner.
 *   - UpdateDesc: Atualiza a descrição.
 *   - NewPassword: Atualzia a senha.
 *   - DeleteProfile: Deleta perfil.
 *   - AddSeeClass: Certifica que user viu x aula.
 *   - RemoveSeeClass: Certifica que user desvio a aula.
 *   - AddNote: Adiciona Nota.
 *   - RemoveNote: Remove Nota
 *
 **/
@Service
public class UserProfile {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LobyDTO Loby(String userName){
        var user = userRepository.findByuserName(userName);

        for (String chave : user.getMyPaths().keySet()) {
            if (pathRepository.findPath(chave) == null){
                user.getMyPaths().remove(chave);
            }
        }

        userRepository.save(user);
        return new LobyDTO(user);
    }

    public void AddPath (AddPath AddPath){
        var user = userRepository.findByuserName(AddPath.userName());
        var path = pathRepository.findPath(AddPath.PathID());
        path.UpdatePathCount(true);
        user.AddMyPaths(path);
        userRepository.save(user);
        pathRepository.save(path);
    }

    public void RemovePath (AddPath AddPath){
        var User  = userRepository.findByuserName(AddPath.userName());
        User.getMyPaths().remove(AddPath.PathID());
        var path = pathRepository.findPath(AddPath.PathID());
        path.UpdatePathCount(false);
        userRepository.save(User);
        pathRepository.save(path);
    }

    public PathGetDTO GetPath (String pathID){
        var Path = pathRepository.findPath(pathID);
        var Author = userRepository.findByWorldID(Path.getIdAuthor().toString());
        return new PathGetDTO(Path,Author);
    }

    public void NewName (UpdateProfileName userDTO){
        var User = userRepository.findByuserName(userDTO.userName());
        System.out.println(User);

        if (userRepository.findByuserName(userDTO.newUsername()) == null){
            var CopyUser = User;

            userRepository.delete(User);
            CopyUser.setUserName(userDTO.newUsername());
            userRepository.save(CopyUser);
        }
    }

    public void UpdatePictureProfile(UserEasyAspects UserEasyAspects){
        var User = userRepository.findByuserName(UserEasyAspects.userName());
        if (UserEasyAspects.PictureProfile() != null && !UserEasyAspects.PictureProfile().trim().isEmpty()){
            User.setPictureProfile(UserEasyAspects.PictureProfile());}
        if (UserEasyAspects.BannerProfile() != null && !UserEasyAspects.BannerProfile().trim().isEmpty()){
            User.setBannerProfile(UserEasyAspects.BannerProfile());
        }
        userRepository.save(User);
    }

    public void UpdateDesc (UserEasyAspects userEasyAspects){
        var User = userRepository.findByuserName(userEasyAspects.userName());
        User.setDesc(userEasyAspects.desc());
        userRepository.save(User);
    }

    public void NewPassword (Password password){
        var User = userRepository.findByuserName(password.userName());

        if (passwordEncoder.matches(password.curretPassword(),User.getPassword())){
            User.setPassword(passwordEncoder.encode(password.newPassWord()));
            userRepository.save(User);
        }
    }

    public void DeleteProfile(Password password) {
        var User = userRepository.findByuserName(password.userName());
        if (passwordEncoder.matches(password.curretPassword(),User.getPassword())){
            var PathByAuthor = pathRepository.findByAuthor(User.getWorldID());
            pathRepository.deleteAll(PathByAuthor);
            userRepository.delete(User);
        }
    }

    public void AddSeeClass (AddSeeClassDTO addSeeClassDTO){
       var User = userRepository.findByuserName(addSeeClassDTO.UserName());
       User.getMyPaths().get(addSeeClassDTO.PathID()).AddSeeClass(true,addSeeClassDTO.IDclass(),addSeeClassDTO.indexModule());
       userRepository.save(User);
    }

    public void RemoveSeeClass (AddSeeClassDTO addSeeClassDTO){
        var User = userRepository.findByuserName(addSeeClassDTO.UserName());
        User.getMyPaths().get(addSeeClassDTO.PathID()).AddSeeClass(false,addSeeClassDTO.IDclass(),addSeeClassDTO.indexModule());
        userRepository.save(User);
    }

    public void AddNote (NoteDTO noteDTO){
        var User = userRepository.findByuserName(noteDTO.UserName());
        User.UpdateNewNote(noteDTO.message());
        userRepository.save(User);
    }

    public void RemoveNote (NoteDTO noteDTO){
        var User = userRepository.findByuserName(noteDTO.UserName());
        User.DeleteNote(noteDTO.key());
        userRepository.save(User);
    }
}