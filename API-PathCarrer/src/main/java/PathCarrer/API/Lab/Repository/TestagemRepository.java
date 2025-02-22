package PathCarrer.API.Lab.Repository;

import PathCarrer.API.Lab.Model.RecadosDeTeste;
import PathCarrer.API.Model.Path;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TestagemRepository extends MongoRepository<RecadosDeTeste,String> {
    // Consulta personalizada para buscar usuários com idade maior que um valor
    @Query("{ 'recado':  ?0  }")
    List<RecadosDeTeste> comBaseNoRecadp(String idade);

}
