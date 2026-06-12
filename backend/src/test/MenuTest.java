import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.moviereview.Menu;
import com.moviereview.service.AvaliacaoService;
import com.moviereview.service.FilmeService;
import com.moviereview.service.UsuarioService;

public class MenuTest {

    @Test
    public void testeOpcaoListarFilmes() {

        FilmeService filmeService = mock(FilmeService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);
        AvaliacaoService avaliacaoService = mock(AvaliacaoService.class);

        Menu menu = new Menu(filmeService, usuarioService,avaliacaoService);

        menu.processarOpcao(1);

        verify(filmeService).listarFilmes();
    }

    @Test
    public void testeOpcaoSair() {

        FilmeService filmeService = mock(FilmeService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);
        AvaliacaoService avaliacaoService = mock(AvaliacaoService.class);

        Menu menu = new Menu(filmeService, usuarioService,avaliacaoService);


        menu.processarOpcao(0);

        // Se chegou aqui sem exceção, o teste passou
    }

    @Test
    public void testeOpcaoInvalida() {

        FilmeService filmeService = mock(FilmeService.class);
        AvaliacaoService avaliacaoService = mock(AvaliacaoService.class);
        UsuarioService usuarioService = mock(UsuarioService.class);
        Menu menu = new Menu(filmeService, usuarioService,avaliacaoService);


        try {
            menu.processarOpcao(99);
        } catch (AssertionError e) {
            return; // comportamento esperado
        }

        throw new AssertionError("Era esperado lançar AssertionError");
    }
}