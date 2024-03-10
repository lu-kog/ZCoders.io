package tmp.vsr;
public class kata {
   public static boolean isPalindrome(String str) {
        // Convert the string to lowercase and remove spaces and punctuation
        str = str.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        // Initialize pointers for the start and end of the string
        int i = 0;
        int j = str.length() - 1;
        
        // Iterate over the string from both ends, comparing characters
        while (i < j) {
            // If characters at the pointers don't match, it's not a palindrome
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            // Move the pointers towards the center
            i++;
            j--;
        }
        // If the loop completes without returning false, the string is a palindrome
        return true;

   }
}