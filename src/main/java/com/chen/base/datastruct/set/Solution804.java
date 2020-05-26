package com.chen.base.datastruct.set;

/**
 * @author: Chentian
 * @date: Created in 2020/5/20 7:27
 * @desc
 */
public class Solution804 {



    public int uniqueMorseRepresentations(String[] words) {
        String[] keys = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        if (words == null || words.length == 0 ){
            return 0;
        }
        LinkedSet<String> set = new LinkedSet<>();
        for (String word : words){
            StringBuilder sbr = new StringBuilder();
            for (int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                sbr.append(keys[index]);
            }
            set.add(sbr.toString());
        }
        return set.getSize();
    }
}
