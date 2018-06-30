package Controller;

import Dao.RestDao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Data;

@Data
@Named(value = "restController")
@SessionScoped
public class RestController implements Serializable {

    private String UrlImagen;
    private String Resultado;
    private RestDao dao = new RestDao();

    public void consultar() throws Exception{
        try {
            Resultado = dao.consultar(UrlImagen);
        } catch (IOException e) {
            throw e;
        }
    }
    
}
