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
    private String UrlImagenAnalisado;
    private String Resultado;

    public void consultar() throws Exception {
        RestDao dao;
        try {
            dao = new RestDao();
            setUrlImagenAnalisado(getUrlImagen());
            Resultado = dao.consultar(getUrlImagen());
            setUrlImagen(null);
        } catch (IOException e) {
            throw e;
        }
    }

}
