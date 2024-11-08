import java.util.Random;

public class SimpleRandomSentences {

    private static Random random = new Random();
    private static final int MAX_DEPTH = 15; // Set a maximum recursion depth

    public static void main(String[] args) {
        int numSentences = 15; // You can adjust the number of sentences to generate
        for (int i = 0; i < numSentences; i++) {
            String sentence = generateSentence(0);
            System.out.println(sentence);
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
            }
        }
    }

    private static String generateSentence(int depth) {
        if (depth > MAX_DEPTH || random.nextBoolean()) {
            return generateSimpleSentence(depth);
        } else {
            return generateSimpleSentence(depth) + " " + generateConjunction() + " " + generateSentence(depth + 1);
        }
    }

    private static String generateSimpleSentence(int depth) {
        return generateNounPhrase(depth) + " " + generateVerbPhrase(depth);
    }

    private static String generateNounPhrase(int depth) {
        if (random.nextBoolean()) {
            return generateProperNoun();
        } else {
            String determiner = generateDeterminer();
            String adjective = random.nextBoolean() ? " " + generateAdjective() : "";
            String commonNoun = generateCommonNoun();
            String whoClause = random.nextBoolean() ? " who " + generateVerbPhrase(depth) : "";
            return determiner + adjective + " " + commonNoun + whoClause;
        }
    }

    private static String generateVerbPhrase(int depth) {
        if (random.nextBoolean()) {
            return generateIntransitiveVerb();
        } else if (random.nextBoolean()) {
            return "is " + generateAdjective();
        } else {
            return "believes that " + generateSimpleSentence(depth + 1);
        }
    }

    private static String generateConjunction() {
        String[] conjunctions = {"and", "or", "but", "because"};
        return randomItem(conjunctions);
    }

    private static String generateProperNoun() {
        String[] properNouns = {"Fred", "Jane", "Richard Nixon", "Miss America"};
        return randomItem(properNouns);
    }

    private static String generateCommonNoun() {
        String[] commonNouns = {"man", "woman", "fish", "elephant", "unicorn"};
        return randomItem(commonNouns);
    }

    private static String generateDeterminer() {
        String[] determiners = {"a", "the", "every", "some"};
        return randomItem(determiners);
    }

    private static String generateAdjective() {
        String[] adjectives = {"big", "tiny", "pretty", "bald"};
        return randomItem(adjectives);
    }

    private static String generateIntransitiveVerb() {
        String[] intransitiveVerbs = {"runs", "jumps", "talks", "sleeps"};
        return randomItem(intransitiveVerbs);
    }

    private static String randomItem(String[] list) {
        return list[random.nextInt(list.length)];
    }
}
