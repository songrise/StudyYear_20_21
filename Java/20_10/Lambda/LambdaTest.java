import java.util.Arrays;

class Word {
    final String text;
    int len;

    Word(String s) {
        this.text = "";
        this.len = s.length();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(" with length: ");
        sb.append(Integer.toString(len));

        return sb.toString();
    }

}

public class LambdaTest {
    public static void main(String[] args) {
        Word arr[] = { new Word("Lambda"), new Word("test"), new Word("Class") };
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, (first, second) -> first.len - second.len);
        System.out.println(Arrays.toString(arr));
    }
}
