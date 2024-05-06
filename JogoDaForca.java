import java.util.Random;

public class JogoDaForca {
    private static final String[] palavras = {
        "computador", "programacao", "linguagem", "java", "algoritmo", "desenvolvimento", "software"
    };
    private static final Random random = new Random();

    public static void main(String[] args) {
        String palavraSecreta = gerarPalavraSecreta();
        char[] letrasDescobertas = new char[palavraSecreta.length()];
        boolean jogoAcabou = false;
        int tentativas = 7; // Número de tentativas permitidas

        while (!jogoAcabou) {
            mostrarPalavra(letrasDescobertas);
            char letra = pedirLetra();
            if (verificarLetra(palavraSecreta, letrasDescobertas, letra)) {
                if (palavraDescoberta(letrasDescobertas)) {
                    System.out.println("Parabéns, você ganhou!");
                    jogoAcabou = true;
                }
            } else {
                tentativas--;
                if (tentativas == 0) {
                    System.out.println("Você perdeu! A palavra era: " + palavraSecreta);
                    jogoAcabou = true;
                }
            }
        }
    }

    private static String gerarPalavraSecreta() {
        return palavras[random.nextInt(palavras.length)];
    }

    private static void mostrarPalavra(char[] letrasDescobertas) {
        for (char c : letrasDescobertas) {
            if (c == 0) {
                System.out.print("_ ");
            } else {
                System.out.print(c + " ");
            }
        }
        System.out.println();
    }

    private static char pedirLetra() {
        System.out.print("Digite uma letra: ");
        String input = System.console().readLine();
        return input.charAt(0);
    }

    private static boolean verificarLetra(String palavraSecreta, char[] letrasDescobertas, char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                letrasDescobertas[i] = letra;
                letraEncontrada = true;
            }
        }
        return letraEncontrada;
    }

    private static boolean palavraDescoberta(char[] letrasDescobertas) {
        for (char c : letrasDescobertas) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }
}
