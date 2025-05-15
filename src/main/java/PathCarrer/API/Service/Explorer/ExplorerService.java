package PathCarrer.API.Service.Explorer;


import PathCarrer.API.Model.ExplorerModels.Explorer;
import PathCarrer.API.Model.Path.Path;
import PathCarrer.API.Model.User.User;
import PathCarrer.API.Repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Classe destinada a atender demandas de busca por Paths.
 *
 * <p>
 * This class is responsible for handling Path search operations.
 * </p>
 *
 * <p><strong>Operações disponíveis / Available operations:</strong></p>
 *
 * <ul>
 *   <li><strong>DefaultExplorer:</strong> Retorna todos os Paths criados. <br>
 *       Returns all created Paths.</li>
 *   <li><strong>CategoryExplorer:</strong> Retorna Paths filtrados por categoria. <br>
 *       Returns Paths filtered by category.</li>
 *   <li><strong>MyPathsAuthor:</strong> Retorna os Paths criado pelo autor. <br>
 *  *    Returns Paths created by author.</li>
 * </ul>
 *
 * <p>
 * Esta classe facilita a navegação e descoberta de conteúdo dentro da plataforma.
 * </p>
 *
 * <p>
 * This class helps users navigate and discover content within the platform.
 * </p>
 */

@Service
public class ExplorerService {
    @Autowired
    private PathRepository pathRepository;

    public List<Explorer> DefaultExplorer (){
        return pathRepository.defautExplorer();
    }

    public List<Explorer> CategoryExplorer (String category){
        if (Objects.equals(category, "Todos")){
            return pathRepository.defautExplorer();
        }
        return pathRepository.CategoryExplorer(category);
    }

    public List<Path> MyPathsAuthor (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usuarioLogado = (User) auth.getPrincipal();

        return pathRepository.findByAuthor(usuarioLogado.getWorldID());
    }



}
