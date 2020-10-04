import java.util.*;
/**
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
 * Each node of a binary expression tree has either zero or two children.
 * Leaf nodes (nodes with 0 children) correspond to operands (numbers),
 * and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication),
 * and '/' (division).
 *
 * For each internal node with operator o, the infix expression that it represents is (A o B),
 * where A is the expression the left subtree represents and B is the expression the right subtree represents.
 *
 * You are given a string s, an infix expression containing operands, the operators described above, and parentheses '(' and ')'.
 *
 * Return the binary expression tree, which its in-order traversal reproduce s.
 *
 * Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first,
 * and multiplication and division happen before addition and subtraction.
 *
 *
 *
 * Example 1: +
 *          /  \
 *         -    1
 *        / \
 *       2  /
 *         / \
 *        3   *
 *           / \
 *          5   2
 * Input: s = "2-3/(5*2)+1"
 * Output: [+,-,1,2,/,null,null,null,null,3,*,null,null,5,2]
 *
 * Example 2:
 *          -
 *         / \
 *        *   *
 *       /\   /\
 *      3 4  2  5
 * Input: s = "3*4-2*5"
 * Output: [-,*,*,3,4,2,5]
 * Example 3:
 *
 * Input: s = "1+2+3+4+5"
 * Output: [+,+,5,+,4,null,null,+,3,null,null,1,2]
 *          +
 *         / \
 *        +   5
 *       /\   /\
 *      +  4
 *     /\
 *    + 3
 *   /\
 *  1  2
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of digits and the characters '+', '-', '*', '/', '(', and ')'.
 * Operands in s are exactly 1 digit.
 * It is guaranteed that s is a valid expression.
 */
public class BuildBinaryExpressionTreeFromInfixExpression {
    public static void main(String[] args) {
        System.out.println(new BuildBinaryExpressionTreeFromInfixExpression().expTree("1+2+3+4+5"));
        System.out.println(new BuildBinaryExpressionTreeFromInfixExpression().expTree("2-3/(5*2)+1"));
    }
    private class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public Node expTree(String s) {
        Stack<Node> operators = new Stack<>();
        Stack<Node> operands = new Stack<>();

        int curr = 0;
        while (curr < s.length()) {
            char c = s.charAt(curr);
            /**先算括号里面的*/
            if(c == '(') {
                /**记录括号的开始*/
                int start = curr + 1;
                int open = 1;
                curr++;
                while(open  !=  0) {
                    s.charAt(curr);
                    if (s.charAt(curr) == ')') {
                        open--;
                    } else if(s.charAt(curr) == '(') {
                        open++;
                    }
                    curr++;
                }
                /**记录括号的结束*/
                int end = curr - 1;
                /**先算括号里面的*/
                Node n = expTree(s.substring(start,end));
                operands.push(n);
                continue;
            }
            if(c >= '0' && c <= '9') {
                operands.push(new Node(c));
            } else {
                /**加减之前要看看前面还有没有没被处理的operators*/
                if (!operators.isEmpty() && checkPrecedence(operators, c)) {
                    makeSubTree(operators, operands);
                    /**while的continue是不会++的，跟for的不同，学到了！！！*/
                    continue;
                } else {
                    operators.push(new Node(c));
                }
            }
            curr++;
        }

        while(!operators.isEmpty()) {
            makeSubTree(operators, operands);
        }
        return operands.pop();
    }

        private void makeSubTree(Stack<Node> operators, Stack<Node> operands) {
            Node root = operators.pop();
            Node right = operands.pop();
            Node left = operands.pop();
            root.left = left;
            root.right = right;
            operands.push(root);
        }

        private boolean checkPrecedence(Stack<Node> operators, char c) {
            if(c == '+'  ||  c == '-') {
                return true;
            }
            return false;
    }
}
