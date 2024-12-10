package DevHelper.Utils;

import java.util.ArrayList;
import java.util.List;

public class BannedWords {

    private List<String> BannedWords;

    public boolean WordIsValid(String word) {
        if (word == null || word.trim().isEmpty())
            return true;

        BannedWords = new ArrayList<>();
        BannedWords.add("teste");

        return !BannedWords.contains(word);
    }
}
