import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.moviereview.Menu;
import com.moviereview.service.UsuarioService;

import com.moviereview.service.FilmeService;

public class MenuTest {

    @Test
    public void testeOpcaoListarFilmes() {

        FilmeService filmeService = mock(FilmeService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);

        Menu menu = new Menu(filmeService, usuarioService);

        menu.processarOpcao(1);

        verify(filmeService).listarFilmes();
    }

    @Test
    public void testeOpcaoSair() {

        FilmeService filmeService = mock(FilmeService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);

        Menu menu = new Menu(filmeService, usuarioService);

        menu.processarOpcao(0);

        // Se chegou aqui sem exceção, o teste passou
    }

    @Test
    public void testeOpcaoInvalida() {

        FilmeService filmeService = mock(FilmeService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);

        Menu menu = new Menu(filmeService, usuarioService);

        try {
            menu.processarOpcao(99);
        } catch (AssertionError e) {
            return; // comportamento esperado
        }

        throw new AssertionError("Era esperado lançar AssertionError");
    }
}