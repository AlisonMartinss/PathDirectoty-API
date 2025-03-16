package PathCarrer.API.Service.userProfile;


import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.GetsPathByUserDTO.PathGetDTO;
import PathCarrer.API.DTO.UsersDTO.LobyDTO;
import PathCarrer.API.DTO.UsersDTO.UpdateProfileName;
import PathCarrer.API.DTO.UsersDTO.UserEasyAspects;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.MyPathsAdd;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 *   MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO USUARIO E SUA CONTA.
 *
 *   - Loby: Trazer a tona as informções a respeito da sua conta.
 *   - AddPath: Adiciona Path.
 *   - RemovePath: Remove Path da lista de path do usuario.
 *   - GetPath: Traz informações sobre o path quando selecionado.
 *
 *      PENDENTES:
 *      - Editar informações do pergil: Foto do perfil, banner, e desc e anotações
 * **/
@Service
public class UserProfile {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LobyDTO Loby(userDTO userDTO){
        var user = userRepository.findByID(userDTO.userName());
        for (int i = 0; i < user.getMyPaths().size(); i++){
            if (pathRepository.findPath(user.getMyPaths().get(i).getPathID()) == null){
                user.getMyPaths().remove(i);
            }
        }
        userRepository.save(user);

        return new LobyDTO(user);
    }

    public void AddPath (AddPath AddPath){
        var user = userRepository.findByID(AddPath.userName());
        var Path = pathRepository.findPath(AddPath.PathID());
        var newPath = new MyPathsAdd();
        newPath.AddPath(Path);
        user.AddMyPaths(newPath);
        userRepository.save(user);
    }

    public void RemovePath (AddPath AddPath){
        var User  = userRepository.findByID(AddPath.userName());
        for (int i = 0; i < User.getMyPaths().size(); i++){
            if (Objects.equals(User.getMyPaths().get(i).getPathID(), AddPath.PathID())){
                User.getMyPaths().remove(i);
                userRepository.save(User);
                break;
            }
        }
    }
    public PathGetDTO GetPath (String pathID){
        var Path = pathRepository.findPath(pathID);
        var Author = userRepository.findByID(Path.getIdAuthor());
        return new PathGetDTO(Path,Author);
    }

    public void NewName (UpdateProfileName userDTO){

        var User = userRepository.findByID(userDTO.userName());
        var newUser = userRepository.findByID(userDTO.newUsername());
        var CopyUser = User;

        if (newUser == null){
            List<Path> pathByAuthor = pathRepository.findByAuthor(userDTO.userName());

            for (int i = 0; i < pathByAuthor.size(); i++){
                pathByAuthor.get(i).setIdAuthor(userDTO.newUsername());
                pathRepository.save(pathByAuthor.get(i));
            }

            userRepository.delete(User);
            CopyUser.setUserName(userDTO.newUsername());
            userRepository.save(CopyUser);
        }
    }

    public void UpdatePictureProfile(UserEasyAspects UserEasyAspects){
        var User = userRepository.findByID(UserEasyAspects.userName());
        if (UserEasyAspects.PictureProfile() != null && !UserEasyAspects.PictureProfile().trim().isEmpty()){
        User.setPictureProfile(UserEasyAspects.PictureProfile());}
        if (UserEasyAspects.BannerProfile() != null && !UserEasyAspects.BannerProfile().trim().isEmpty()){
            User.setBannerProfile(UserEasyAspects.BannerProfile());
        }
        userRepository.save(User);
    }

    public void UpdateDesc (UserEasyAspects userEasyAspects){
        var User = userRepository.findByID(userEasyAspects.userName());
        User.setDesc(userEasyAspects.desc());
        userRepository.save(User);

    }
    public void NewPassword (Password password){
        var User = userRepository.findByID(password.userName());

        if (passwordEncoder.matches(password.curretPassword(),User.getPassword())){
            User.setPassword(passwordEncoder.encode(password.newPassWord()));
            userRepository.save(User);
        }
        else {
            System.out.println("NÃO IGUAIS");
        }
    }


    public void DeleteProfile(Password password) {
        var User = userRepository.findByID(password.userName());

        if (passwordEncoder.matches(password.curretPassword(),User.getPassword())){
            userRepository.delete(User);
        }
    }
}
