package testesJunit;

import main.controller.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MainTest {
    
    @Test
    public void deveCriarArquivoComSucesso() {
        String nomeArquivo = "arquivo-teste.txt";
        Path caminhoArquivo = Paths.get(nomeArquivo);

        // Crie o arquivo
        Main.criarArquivo(nomeArquivo);

        // Verifique se o arquivo foi criado
        boolean arquivoExiste = Files.exists(caminhoArquivo);
        System.out.println("Caminho do arquivo: " + caminhoArquivo.toAbsolutePath());
        System.out.println("Arquivo existe: " + arquivoExiste);
        assertTrue(arquivoExiste, "O arquivo não foi criado como esperado.");

        // Redirecione a saída do console para um ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // Chame o método novamente para verificar a mensagem
        Main.criarArquivo(nomeArquivo);
        System.out.flush();
        
        // Verifique se a mensagem de sucesso foi impressa
        String mensagem = out.toString();
        System.out.println("Mensagem: " + mensagem);
        assertTrue(mensagem.contains("Arquivo " + nomeArquivo + " criado com sucesso!"), "A mensagem de sucesso não foi encontrada.");
    }

    // Restaure a saída padrão após o teste
    @AfterEach
    public void restoreSystemOut() {
        System.setOut(System.out);
    }
}
