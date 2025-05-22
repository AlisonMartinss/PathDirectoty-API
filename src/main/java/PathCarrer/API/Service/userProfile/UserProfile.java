package PathCarrer.API.Service.userProfile;


import PathCarrer.API.DTO.GetsPathByUserDTO.AddPath;
import PathCarrer.API.DTO.GetsPathByUserDTO.PathGetDTO;
import PathCarrer.API.DTO.Update.AddSeeClassDTO;
import PathCarrer.API.DTO.UsersDTO.LobyDTO;
import PathCarrer.API.DTO.UsersDTO.NoteDTO;
import PathCarrer.API.DTO.UsersDTO.UpdateProfileName;
import PathCarrer.API.DTO.UsersDTO.UserEasyAspects;
import PathCarrer.API.ExeptionsClasses.*;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import PathCarrer.API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por gerenciar as operações relacionadas ao usuário e sua conta.
 * <br>
 * Class responsible for managing operations related to the user and their account.
 *
 * <p><strong>Funções principais / Main functions:</strong></p>
 *
 * <ul>
 *   <li><strong>loby:</strong> Retorna as informações da conta do usuário. <br> Returns the user's account information.</li>
 *   <li><strong>addPathID:</strong> Adiciona um novo path à lista do usuário. <br> Adds a new path to the user's list.</li>
 *   <li><strong>removePath:</strong> Remove um path da lista do usuário. <br> Removes a path from the user's list.</li>
 *   <li><strong>getPath:</strong> Retorna informações detalhadas de um path selecionado. <br> Returns detailed info of a selected path.</li>
 *   <li><strong>newName:</strong> Atualiza o nome de usuário. <br> Updates the user's name.</li>
 *   <li><strong>updatePictureProfile:</strong> Atualiza a foto de perfil e/ou banner do usuário. <br> Updates the user's profile picture and/or banner.</li>
 *   <li><strong>updateDesc:</strong> Atualiza a descrição do perfil. <br> Updates the user's profile description.</li>
 *   <li><strong>newPassword:</strong> Altera a senha da conta. <br> Changes the user's password.</li>
 *   <li><strong>deleteProfile:</strong> Exclui permanentemente o perfil do usuário. <br> Permanently deletes the user's profile.</li>
 *   <li><strong>addSeeClass:</strong> Registra que o usuário assistiu determinada aula. <br> Marks that the user has watched a specific class.</li>
 *   <li><strong>removeSeeClass:</strong> Remove o registro de que o usuário assistiu determinada aula. <br> Unmarks a previously watched class.</li>
 *   <li><strong>addNote:</strong> Adiciona uma nota ao conteúdo. <br> Adds a note to the content.</li>
 *   <li><strong>removeNote:</strong> Remove uma nota previamente adicionada. <br> Removes a previously added note.</li>
 * </ul>
 *
 * <p>
 * Essa classe centraliza as operações de atualização, visualização e controle do perfil do usuário. <br>
 * This class centralizes all operations for updating, viewing, and managing the user's profile.
 * </p>
 */

@Service
public class UserProfile {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LobyDTO Loby(String userName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(userName);

        if (User == null) {
              throw new NotFound("Loby - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        for (String chave : User.getMyPaths().keySet()) {
          if (pathRepository.findPathByID(chave) == null){
          User.getMyPaths().remove(chave);
          }
        }

        userRepository.save(User);
        return new LobyDTO(User);
    }

    public void AddPath (AddPath AddPath){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(AddPath.userName());

        if (User == null) {
            throw new NotFound("AddPath - Usuario não encontrado!");
        }

        var path = pathRepository.findPathByID(AddPath.PathID());
        if (path == null) {
            throw new NotFound("AddPath - Path Não Encontrado!");
        }
        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        path.UpdatePathCount(true);
        User.AddMyPaths(path);
        userRepository.save(User);
        pathRepository.save(path);
    }

    public void RemovePath (AddPath AddPath){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(AddPath.userName());

        if (User == null) {
            throw new NotFound("RemovePath - Usuario não encontrado!");
        }

        var path = pathRepository.findPathByID(AddPath.PathID());

        if (path == null) {
            throw new NotFound("RemovePath - Path Não Encontrado!");
        }
        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        User.getMyPaths().remove(AddPath.PathID());
        path.UpdatePathCount(false);

        userRepository.save(User);
        pathRepository.save(path);
    }

    public PathGetDTO GetPath (String pathID){
        var Path = pathRepository.findPathByID(pathID);
        if (Path == null) {
            throw new NotFound("GetPath - Path Não Encontrado!");
        }

        var Author = userRepository.findByWorldID(Path.getIdAuthor().toString());
        if (Author == null) {
            throw new NotFound("GetPath - Author não encontrado!");
        }

        return new PathGetDTO(Path,Author);
    }

    public void NewName (UpdateProfileName userDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(userDTO.userName());

        if (User == null) {
            throw new NotFound("NewName - Usuario não encontrado!");
        }
        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (userRepository.findByuserName(userDTO.newUsername()) != null){
            throw new GenericErro("nome de usuario nao disponivel");
        }

        var CopyUser = User;

        userRepository.delete(User);
        CopyUser.setUserName(userDTO.newUsername());
        userRepository.save(CopyUser);
    }

    public void UpdatePictureProfile(UserEasyAspects UserEasyAspects){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(UserEasyAspects.userName());

        if (User == null) {
            throw new NotFound("UpdatePictureProfile - Usuario não encontrado!");
        }
        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (UserEasyAspects.PictureProfile() != null && !UserEasyAspects.PictureProfile().trim().isEmpty()){
            User.setPictureProfile(UserEasyAspects.PictureProfile());
        }
        if (UserEasyAspects.BannerProfile() != null && !UserEasyAspects.BannerProfile().trim().isEmpty()){
            User.setBannerProfile(UserEasyAspects.BannerProfile());
        }

        userRepository.save(User);
    }

    public void UpdateDesc (UserEasyAspects userEasyAspects){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(userEasyAspects.userName());

        if (User == null) {
            throw new NotFound("UpdateDesc - Usuario não encontrado!");
        }
        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (userEasyAspects.desc() == null || userEasyAspects.desc().trim().isEmpty()) {
            throw new InconsistentParameter("InconsistentParameter/UpdateDesc - Desc");
        }

        User.setDesc(userEasyAspects.desc());

        userRepository.save(User);
    }

    public void NewPassword (Password password){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(password.userName());

        if (User == null) {
            throw new NotFound("NewPassword - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (!(passwordEncoder.matches(password.curretPassword(),User.getPassword()))){
            throw new InconsistentParameter("As senhas que deveriam ser iguais não são.");
        }

        User.setPassword(passwordEncoder.encode(password.newPassWord()));
        userRepository.save(User);
    }

    public void DeleteProfile(Password password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(password.userName());

        if (User == null) {

            throw new NotFound("DeleteProfile - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (!(passwordEncoder.matches(password.curretPassword(),User.getPassword()))){
            throw new UserAspectsUnexpected("DeleteProfile - Senha incorreta !");
        }

        var PathByAuthor = pathRepository.findByAuthor(User.getWorldID());
        if (!(PathByAuthor.isEmpty())){
            pathRepository.deleteAll(PathByAuthor);
        }
        userRepository.delete(User);
    }

    public void AddSeeClass (AddSeeClassDTO addSeeClassDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(addSeeClassDTO.UserName());

        if (User == null) {
            throw new NotFound("AddSeeClass - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {
            User.getMyPaths().get(addSeeClassDTO.PathID()).AddSeeClass(true,addSeeClassDTO.IDclass(),addSeeClassDTO.indexModule());
        } catch (NullPointerException exception) {
            throw new UserAspectsUnexpected("AddSeeClass - Erro na tentativa de visualizar Aula!");
        }
        userRepository.save(User);
    }

    public void RemoveSeeClass (AddSeeClassDTO addSeeClassDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(addSeeClassDTO.UserName());

        if (User == null) {
            throw new NotFound("RemoveSeeClass - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }
        try {
            User.getMyPaths().get(addSeeClassDTO.PathID()).AddSeeClass(false,addSeeClassDTO.IDclass(),addSeeClassDTO.indexModule());
        }catch (NullPointerException exception) {
            throw new UserAspectsUnexpected("RemoveSeeClass - Erro na tentativa de {!visualizar} Aula!");
        }

        userRepository.save(User);
    }

    public void AddNote (NoteDTO noteDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(noteDTO.UserName());

        if (User == null) {
            throw new NotFound("AddNote - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        if (noteDTO.message() == null || noteDTO.message().trim().isEmpty()){
            throw new InconsistentParameter("AddNote - Parametro Iconsitente com que se espera");
        }

        User.UpdateNewNote(noteDTO.message());

        userRepository.save(User);
    }

    public void RemoveNote (NoteDTO noteDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        var User = userRepository.findByuserName(noteDTO.UserName());

        if (User == null) {
            throw new NotFound("RemoveNote - Usuario não encontrado!");
        }

        if (!usuarioLogado.getWorldID().equals(User.getWorldID())) {
            throw new Hackers("fingindo ser outro");
        }

        try {User.DeleteNote(noteDTO.key());}
        catch (NullPointerException erro){
            throw new UserAspectsUnexpected("RemoveNote - Erro achar key - Note");
        }

        userRepository.save(User);
    }
}