package DevHelper.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

public class BannedWords {

    private List<String> BannedWords;
    private final Dotenv config = Dotenv.load(); // Configurações do Dotenv

    public boolean WordIsValid(String word) {
        if (word == null || word.trim().isEmpty())
            return true;

        BannedWords = GetBanneList();

        return !BannedWords.contains(word);
    }

    private List<String> GetBanneList() {
        BufferedReader reader;
        BannedWords = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();

		try {
            String bannedWordsFilePath = config.get("BANNEDWORDSFILEPATH");

			reader = new BufferedReader(new FileReader(filePath + bannedWordsFilePath));
			String line = reader.readLine();

			while (line != null) {
                BannedWords.add(line);
				// ler a próxima linha
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			BannedWords.add(e.getMessage());
		}

        return BannedWords;
    }
}
