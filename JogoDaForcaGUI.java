import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JogoDaForcaGUI extends JFrame {
    private static final String[] palavras = {
            "computador", "programacao", "linguagem", "java", "algoritmo", "desenvolvimento", "software", "amor",
            "casa",
            "trabalho",
            "tempo",
            "vida",
            "cidade",
            "dia",
            "noite",
            "família",
            "escola",
            "carro",
            "computador",
            "livro",
            "agua",
            "comida",
            "amigo",
            "beleza",
            "natureza",
            "arte",
            "história",
            "festa",
            "musica",
            "filme",
            "palavra",
            "coração",
            "felicidade",
            "sorriso",
            "saude",
            "dinheiro",
            "sonho",
            "viagem",
            "praia",
            "montanha",
            "caminho",
            "pensamento",
            "sentimento",
            "esperança",
            "paz",
            "guerra",
            "tristeza",
            "alegria",
            "medo",
            "coragem",
            "oportunidade",
            "desafio",
            "conhecimento",
            "sabedoria",
            "educação",
            "cultura",
            "tradição",
            "inovação",
            "tecnologia",
            "internet",
            "redes",
            "televisão",
            "radio",
            "jornal",
            "revista",
            "celular",
            "tablet",
            "odio",
            "amizade",
            "inimizade",
            "amor",
            "amante",
            "família",
            "filho",
            "filha",
            "pai",
            "mãe",
            "irmao",
            "irma",
            "avo",
            "tio",
            "tia",
            "primo",
            "prima",
            "neto",
            "neta",
            "sobrinho",
            "sobrinha",
            "casamento",
            "divórcio",
            "namoro",
            "paixão",
            "romance",
            "encontro",
            "desencontro",
            "solidão",
            "companhia",
            "sozinho",
            "acompanhado",
            "livre",
            "preso",
            "trabalho",
            "emprego",
            "carreira",
            "sucesso"
            
    };
    private static final Random random = new Random();
    private String palavraSecreta;
    private char[] letrasDescobertas;
    private JLabel palavraLabel;
    private JLabel tentativasLabel;
    private int tentativasRestantes;

    public JogoDaForcaGUI() {
        super("Jogo da Forca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        palavraSecreta = gerarPalavraSecreta();
        letrasDescobertas = new char[palavraSecreta.length()];
        tentativasRestantes = 7;
        palavraLabel = new JLabel();
        tentativasLabel = new JLabel("Tentativas restantes: " + tentativasRestantes);
        JPanel centroPanel = new JPanel(new GridLayout(2, 1));
        centroPanel.add(palavraLabel);
        centroPanel.add(tentativasLabel);

        JPanel tecladoPanel = new JPanel(new GridLayout(3, 9));
        for (char c = 'a'; c <= 'z'; c++) {
            JButton button = new JButton(String.valueOf(c));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char letra = e.getActionCommand().charAt(0);
                    verificarLetra(palavraSecreta, letrasDescobertas, letra);
                    atualizarInterface();
                }
            });
            tecladoPanel.add(button);
        }

        add(centroPanel, BorderLayout.CENTER);
        add(tecladoPanel, BorderLayout.SOUTH);

        atualizarInterface();
    }

    private String gerarPalavraSecreta() {
        return palavras[random.nextInt(palavras.length)];
    }

    private void atualizarInterface() {
        palavraLabel.setText(formatarPalavra());
        tentativasLabel.setText("Tentativas restantes: " + tentativasRestantes);
        if (jogoAcabou()) {
            if (palavraDescoberta(letrasDescobertas)) {
                JOptionPane.showMessageDialog(this, "Parabéns, você ganhou!");
            } else {
                JOptionPane.showMessageDialog(this, "Você perdeu! A palavra era: " + palavraSecreta);
            }
            reiniciarJogo();
        }
    }

    private String formatarPalavra() {
        StringBuilder sb = new StringBuilder();
        for (char c : letrasDescobertas) {
            sb.append(c == 0 ? "_ " : c + " ");
        }
        return sb.toString();
    }

    private boolean jogoAcabou() {
        return tentativasRestantes == 0 || palavraDescoberta(letrasDescobertas);
    }

    private void reiniciarJogo() {
        palavraSecreta = gerarPalavraSecreta();
        letrasDescobertas = new char[palavraSecreta.length()];
        tentativasRestantes = 7;
        atualizarInterface();
        reabilitarBotoes(); // Reabilita todos os botões do teclado ao reiniciar o jogo
    }
    
    private void reabilitarBotoes() {
        Component[] components = ((JPanel) getContentPane().getComponent(1)).getComponents(); // Obtém os componentes do painel do teclado
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setEnabled(true); // Habilita todos os botões do teclado
            }
        }
    }
    
    
    private static boolean palavraDescoberta(char[] letrasDescobertas) {
        for (char c : letrasDescobertas) {
            if (c == 0) {
                return false;
            }
        }
        return true;
    }

    private void verificarLetra(String palavraSecreta, char[] letrasDescobertas, char letra) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                letrasDescobertas[i] = letra;
                letraEncontrada = true;
            }
        }
        if (!letraEncontrada) {
            tentativasRestantes--;
            desabilitarBotao(letra); // Desabilita o botão apenas se a letra não for encontrada
        }
        atualizarInterface();
        if (jogoAcabou()) {
            if (palavraDescoberta(letrasDescobertas)) {
                JOptionPane.showMessageDialog(this, "Parabéns, você ganhou!");
            } else {
                JOptionPane.showMessageDialog(this, "Você perdeu! A palavra era: " + palavraSecreta);
            }
            reiniciarJogo();
        }
    }
    
    
    
    private void desabilitarBotao(char letra) {
        Component[] components = ((JPanel) getContentPane().getComponent(1)).getComponents(); // Obtém os componentes do painel do teclado
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().charAt(0) == letra) { // Encontra o botão com a letra clicada
                    button.setEnabled(false); // Desabilita o botão
                    break;
                }
            }
        }
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoDaForcaGUI().setVisible(true));
    }
}
