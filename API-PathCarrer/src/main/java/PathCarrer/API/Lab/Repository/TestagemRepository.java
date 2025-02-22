package PathCarrer.API.Lab.Repository;

import PathCarrer.API.Lab.Model.RecadosDeTeste;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TestagemRepository extends MongoRepository<RecadosDeTeste,String> {
    @Query("{ 'recado':  ?0  }")
    List<RecadosDeTeste> comBaseNoRecado(String idade);

}
