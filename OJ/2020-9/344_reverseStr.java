import java.util.Arrays;

class Solution {

    public void helper(char[] s, int start, int end) {
        if (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            helper(s, start + 1, end - 1);
        }
    }

    public void reverseString(char[] s) {
        this.helper(s, 0, s.length - 1);
    }
}




class Solution {
    public void reverseString(char[] s) {
        int start, end;
        while(start<end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            
            start+=1;
            end+=1;
        }
    }
}