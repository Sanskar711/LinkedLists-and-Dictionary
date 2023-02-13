import java.util.ArrayList;
import java.util.Hashtable;
import Includes.*;

public class FrequencyAnalysis {
    // sizes of hash-tables are updated
    static final int[] hashTableSizes = { 173, 6733, 100003 };
    COL106Dictionary<String, Integer> dict1 = new COL106Dictionary<String, Integer>(hashTableSizes[0]);
    COL106Dictionary<String, Integer> dict2 = new COL106Dictionary<String, Integer>(hashTableSizes[1]);
    COL106Dictionary<String, Integer> dict3 = new COL106Dictionary<String, Integer>(hashTableSizes[2]);

    public static int bintodecimal(String binary) {
        int decimal = 0;
        int len = binary.length() - 1;
        for (int i = 0; i <= len; i++) {
            int digit = binary.charAt(i) - '0';
            decimal += Math.pow(2, len - i) * digit;
        }
        return decimal;
    }
    

    void fillDictionaries(String inputString) throws NullKeyException, KeyAlreadyExistException, KeyNotFoundException {
        /*
         * To be filled in by the student
         */
        if (inputString == null) {
            throw new NullKeyException();
        }
        String word_to_be_added = "";
        ArrayList<String> words = new ArrayList<String>();
        for(int i=0;i<inputString.length();i++){
            if(inputString.charAt(i) == ' ' || i==inputString.length()-1){
                word_to_be_added+=inputString.charAt(i);
                words.add(word_to_be_added);
                word_to_be_added="";
            }
            else{
                word_to_be_added+=inputString.charAt(i);
            }
        }
         for(int i=0;i<words.size();i++){
            String word= words.get(i);
            String final_word="";
            for(int j=0;j<word.length();j++){
                if((word.charAt(j)>=97 && word.charAt(j)<=122)||(word.charAt(j)>=65 && word.charAt(j)<=90)){
                    final_word+=word.charAt(j);
                }
            }
            if(final_word.length()>0){
                final_word=final_word.toLowerCase();
                if(dict1.exist(final_word)){
                    dict1.update(final_word,dict1.get(final_word)+1);
                }
                else{
                    dict1.insert(final_word,1);
                }
                if(dict2.exist(final_word)){
                    dict2.update(final_word,dict2.get(final_word)+1);
                }
                else{
                    dict2.insert(final_word,1);
                }
                if(dict3.exist(final_word)){
                    dict3.update(final_word,dict3.get(final_word)+1);
                }
                else{
                    dict3.insert(final_word,1);
                }
            }
        }

    }

    long[] profile() {
        /*
         * To be filled in by the student
         */
        return new long[4];
    }

    int[] noOfCollisions() {
        /*
         * To be filled in by the student
         */
        int[] ans = new int[3];
        for (int i = 0; i < hashTableSizes[0]; i++) {
            if (dict1.hashTable[i] != null) {
                ans[0] += dict1.hashTable[i].size() - 1;
            }
        }
        for (int i = 0; i < hashTableSizes[1]; i++) {
            if (dict2.hashTable[i] != null) {
                ans[1] += dict2.hashTable[i].size() - 1;
            }
        }
        for (int i = 0; i < hashTableSizes[2]; i++) {
            if (dict3.hashTable[i] != null) {
                ans[2] += dict3.hashTable[i].size() - 1;
            }
        }
        return ans;
    }
    public static String hex_helper(String bin){
        Character [] notations = new Character[16];
        for(int i=0;i<10;i++){
            notations[i]= (char) (48+i);
        }
        for(int i=10;i<16;i++){
            notations[i] = (char) (65+i-10);
        }
        String final_bin="";
        if(bin.length()%4 !=0){
            for(int i=0;i<4-bin.length()%4;i++){
                final_bin+='0';
            }
        }
        for(int i=0;i<bin.length();i++){
            final_bin+=bin.charAt(i);
        }
        String hexString="";
        for(int i=final_bin.length()-1;i>=0;i-=4){
            String binary="";
            for(int j=i;j>i-4;j--){
                binary= final_bin.charAt(j)+binary;
            }
            hexString = notations[bintodecimal(binary)]+ hexString;      
        }
            
        return hexString;
    }
    String[] hashTableHexaDecimalSignature() {
        /*
         * To be filled in by the student
         */
        String[] ans = new String[3];
        String bin = "";
        for (int i = 0; i < hashTableSizes[0]; i++) {
            if (dict1.hashTable[i] != null) {
                bin += '1';
            } else {
                bin += '0';
            }
        }
        ans[0]=hex_helper(bin);
        
        String bin2 = "";
        for (int i = 0; i < hashTableSizes[1]; i++) {
            if (dict2.hashTable[i] != null) {
                bin2 += '1';
            } else {
                bin2 += '0';
            }
        }
        ans[1]=hex_helper(bin2);

        String bin3 = "";
        for (int i = 0; i < hashTableSizes[2]; i++) {
            if (dict3.hashTable[i] != null) {
                bin3 += '1';
            } else {
                bin3 += '0';
            }
        }
        ans[2]=hex_helper(bin3);
        
        return ans;
    }

    String[] distinctWords() {
        /*
         * To be filled in by the student
         */

        return dict1.keys(String.class);
    }

    Integer[] distinctWordsFrequencies() {
        /*
         * To be filled in by the student
         */
        return dict1.values(Integer.class);
    }

    public static void main(String[] args) throws NullKeyException,KeyAlreadyExistException, KeyNotFoundException {
        FrequencyAnalysis fa = new FrequencyAnalysis();
        String inputString = "The Indian Institute of Technology Delhi (IIT Delhi) is a public "+
        "institute of technology located in New Delhi, India. It is one "+
        "of the twenty-three Indian Institutes of Technology created "+
        "to be Centres of Excellence for India’s training, research and "+
        "development in science, engineering and technology.";
        fa.fillDictionaries(inputString);
        // System.out.println(inputString);
        String[] s = fa.distinctWords();
        Integer[] values = fa.distinctWordsFrequencies();
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
            System.out.print(" ");
            System.out.println(values[i]);
        }
        int[] collisions = fa.noOfCollisions();
        for (int i = 0; i < collisions.length; i++) {
            System.out.println(collisions[i]);
        }
        String[] hex = fa.hashTableHexaDecimalSignature();
        for (int i = 0; i < hex.length ; i++) {
            System.out.println(hex[i]);
        }
    }
}

