package tmp.vsr;

import java.util.HashMap;

public class kata{
	public static String flames(String name1, String name2){
        // Convert names to lowercase to make comparison case-insensitive
        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();

        // Count characters in both names
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : name1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (char c : name2.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Remove common characters
        int commonChars = 0;
        for (int count : charCount.values()) {
            if (count > 1) {
                commonChars += count - 1;
            }
        }

        // Calculate the count of remaining characters
        int totalChars = name1.length() + name2.length() - commonChars;

        // Define the FLAMES acronym
        String[] flames = {"Friendship", "Love", "Affection", "Marriage", "Enemy", "Siblings"};

        // Calculate the final result
        int index = totalChars % flames.length;
        return flames[index == 0 ? flames.length - 1 : index - 1];
	}
}