package funcional;

import models.SolucaoDoExercicio;
import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class ApplicationFunctionalTest {

    @Test
    public void verificaSeOTituloEIgualAePractice() {
        running(testServer(3333), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo(System.getenv("URL_ENVIRONMENT"));
                assertThat(browser.$("title").getTexts().get(0)).isEqualTo("e-Practice");
            }
        });
    }

    @Test
    public void adicionaRegistroNoBancoDeDados() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                SolucaoDoExercicio solucaoDoExercicio = new SolucaoDoExercicio("uma string qualquer");

                Integer numeroDeRegistrosInicial = SolucaoDoExercicio.all().size();

                SolucaoDoExercicio.create(solucaoDoExercicio);

                int numeroDeRegistrosPosterior = SolucaoDoExercicio.all().size();

                assertThat(numeroDeRegistrosPosterior == numeroDeRegistrosInicial + 1);
            }
        });
    }
}