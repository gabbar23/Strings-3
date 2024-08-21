    /**
     * Time Complexity: O(log n), where n is the input number. This is because the number of groups (triplets) to process
     * is proportional to the logarithm of the number in base 1000.
     * Space Complexity: O(1), since the space used is constant regardless of the input size.
     */

class Solution {
    // Words for numbers from 0 to 19
    private static final String[] BELOW_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    
    // Words for multiples of ten (20, 30, etc.)
    private static final String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    
    // Words for large numbers (thousands, millions, billions)
    private static final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };


    public String numberToWords(int num) {
        if (num == 0) return "Zero"; // Handle the special case where the number is 0
        
        StringBuilder result = new StringBuilder(); // To build the final result string
        int idx = 0; // Index to track the current group of thousands (Thousand, Million, Billion)
        
        // Process each group of thousands
        while (num != 0) {
            // Extract the last triplet (group of three digits)
            int triplet = num % 1000;
            
            // Convert the triplet to words and append the corresponding thousand label
            if (triplet != 0) {
                result.insert(0, helper(triplet).strip() + " " + THOUSANDS[idx] + " ");
            }

            // Move to the next group of thousands
            idx++;
            num = num / 1000;
        }
        
        // Return the final result after stripping any leading/trailing whitespace
        return result.toString().strip();
    }

    /**
     * Helper method to convert a number less than 1000 to words.
     * Handles numbers from 0 to 999 by breaking them down into hundreds, tens, and units.
     *
     * Time Complexity: O(1), as it handles at most three digits.
     * Space Complexity: O(1), as it uses a constant amount of space.
     */
    private String helper(int num) {
        if (num == 0)
            return ""; // Handle the special case where the number is 0
        if (num < 20)
            return BELOW_20[num]; // Directly map numbers less than 20
        else if (num < 100)
            return TENS[num / 10] + " " + helper(num % 10); // Handle numbers from 20 to 99
        else {
            // Handle numbers from 100 to 999
            return BELOW_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}
