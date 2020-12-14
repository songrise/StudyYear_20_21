package comp2011.lec3;

public class Grader {
    private static double score(int min, int correct, int wrong) {
        int answered = correct + wrong;
        double score = (answered >= min)? (correct * 100. / answered):(correct * 100. / min); 
        double bonus = (correct > min)? ((correct - min) / 2.):0;
        System.out.println();
        System.out.printf("The minimum number of questions you need to answer is %d."
                + " You gave %d correct answers and %d wrong answers, so your score is %2.1f, with %2.1f bonus.",
                min, correct, wrong, score + bonus, bonus);
        return score + bonus;
    }
    
    public static void main(String[] args) {
        score(60, 80, 6);
    }

}
