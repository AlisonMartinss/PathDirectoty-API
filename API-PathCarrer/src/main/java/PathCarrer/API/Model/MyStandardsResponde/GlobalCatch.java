package PathCarrer.API.Model.MyStandardsResponde;

import PathCarrer.API.ExeptionsClasses.NotFound;
import org.springframework.stereotype.Component;

@Component
public class GlobalCatch<T> {

    public boolean innateVerify (T response){
        if (response.equals("NotFound - Usuario não encontrado!")){
            throw new NotFound("NotFound - Usuario não encontrado!");
        }
        else if (response.equals("Hacker - Fingindo ser outro!")){
            throw new NotFound("Hacker - Fingindo ser outro!");
        }
        return true;
    }
}
