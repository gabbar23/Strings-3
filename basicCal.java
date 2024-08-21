 /**
     * Time Complexity: O(n), where n is the length of the input string. Each character in the string is processed once,
     * and each operation involving the stack (push/pop) is done in constant time.
     * Space Complexity: O(n), where n is the length of the input string. The stack can hold up to n numbers in the worst case.
     */

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>(); // Stack to keep track of numbers for addition and subtraction
        int currentNum = 0;                   // Variable to build the current number from digits
        char lastSign = '+';                  // Stores the last operator encountered

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // If the character is a digit, build the current number
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + (c - '0');
            }
            
            // If the character is not a digit or whitespace, or it's the last character
            if (!Character.isDigit(c) && !Character.isWhitespace(c) || i == s.length() - 1) {
                // Process the number based on the last operator
                if (lastSign == '+') {
                    // For addition, push the current number onto the stack
                    stack.add(currentNum);
                } else if (lastSign == '-') {
                    // For subtraction, push the negative of the current number onto the stack
                    stack.add(-currentNum);
                } else if (lastSign == '/') {
                    // For division, pop the last number from the stack, divide it by the current number, and push the result
                    int lastNum = stack.pop();
                    stack.add(lastNum / currentNum);
                } else if (lastSign == '*') {
                    // For multiplication, pop the last number from the stack, multiply it by the current number, and push the result
                    int lastNum = stack.pop();
                    stack.add(lastNum * currentNum);
                }

                // Update the last operator to the current character (operator)
                lastSign = c;
                // Reset the current number for the next iteration
                currentNum = 0;
            }
        }

        // Sum up all numbers in the stack to get the final result
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result; // Return the final calculated result
    }
}
