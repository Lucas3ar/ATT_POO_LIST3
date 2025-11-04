import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ranking {

    private static final String ARQUIVO_RANKING = "ranking.txt";

    private Map<String, Integer> carregarScores() {
        Map<String, Integer> scores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RANKING))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(":");
                if (partes.length == 2) {
                    scores.put(partes[0].toLowerCase(), Integer.parseInt(partes[1]));
                }
            }
        } catch (IOException e) {
            // Arquivo não existe ainda, o que é normal na primeira vez
        }
        return scores;
    }

    private void salvarScores(Map<String, Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RANKING))) {
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarVitoria(String username) {
        String usernameLower = username.toLowerCase();
        Map<String, Integer> scores = carregarScores();
        int vitoriasAtuais = scores.getOrDefault(usernameLower, 0);
        scores.put(usernameLower, vitoriasAtuais + 1);
        salvarScores(scores);
    }

    public Map<String, Integer> getRankingOrdenado() {
        Map<String, Integer> scores = carregarScores();
        return scores.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public void mostrarTopFive() {
        Map<String, Integer> ranking = getRankingOrdenado();

        System.out.println("--- TOP 5 JOGADORES ---");
        if (ranking.isEmpty()) {
            System.out.println("Ainda não há vencedores registrados.");
        } else {
            int count = 0;
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                if (count >= 5) {
                    break;
                }
                System.out.printf("%d. %s - %d vitórias%n", (count + 1), entry.getKey(), entry.getValue());
                count++;
            }
        }
        System.out.println("-------------------------");
    }
}