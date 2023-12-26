package com.example.mydemo.leetcode.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;


public class IsValid {

    private static Map<Character, Character> map = new HashMap<Character, Character>() {
        {
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }
    };

    public boolean isValid(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<>();
        for (Character c: s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.addLast(c);
            } else {
                Character character = stack.pollLast();
                if (map.get(character) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
