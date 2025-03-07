package PathCarrer.API.Service.userProfile;


import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.GetsPathByUserDTO.PathGetDTO;
import PathCarrer.API.DTO.Update.PathUpdate;
import PathCarrer.API.DTO.UsersDTO.LobyDTO;
import PathCarrer.API.DTO.UsersDTO.userDTO;
import PathCarrer.API.Model.User.MyPathsAdd;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

/**
 *   MOTIVAÇÃO: CLASSE DESTIANADA A FAZER AS OPERAÇÕES QUE TANGEM A RELAÇÃO USUARIO E SUA CONTA.
 *
 *   - Loby: Trazer a tona as informções a respeito da sua conta.
 *   - AddPath: Adiciona Path.
 *   - RemovePath: Remove Path.
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

    public LobyDTO Loby(userDTO userDTO){
        var user = userRepository.findByID(userDTO.userName());
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
            }
        }
    }
    public PathGetDTO GetPath (PathUpdate pathUpdate){
        var Path = pathRepository.findPath(pathUpdate.PathID());
        return new PathGetDTO(Path);
    }





}
