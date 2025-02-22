package PathCarrer.API.Lab.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import  PathCarrer.API.Lab.DTO.TestagemDTO;

@Document(collection = "RecadosDeTeste")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecadosDeTeste {
    @Id
    private  String id;
    private String recado;
    private String nome;

    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
