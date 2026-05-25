import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MenuTest {

    Menu menu = new Menu();

    @Test
    public void testeOpcaoSairValida() {
        assertTrue(menu.validarOpcao(0), "A opção de sair (0) deve ser válida");
    }

    @Test
    public void testeOpcaoRankingValida() {
        assertTrue(menu.validarOpcao(4), "A opção de ranking (4) deve ser válida");
    }

    @Test
    public void testeOpcaoNegativaInvalida() {
        assertFalse(menu.validarOpcao(-1), "Opções negativas devem ser inválidas");
    }

    @Test
    public void testeOpcaoAcimaDoLimiteInvalida() {
        assertFalse(menu.validarOpcao(5), "Opção fora do intervalo 0-4 deve ser inválida");
    }
}