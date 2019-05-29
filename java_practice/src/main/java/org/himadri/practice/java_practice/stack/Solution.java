package org.himadri.practice.java_practice.stack;

import java.util.*;


public class Solution {
	
	public static void main(String[] args) {
		System.out.println(simplifyPath("/./.././ykt/xhp/nka/eyo/blr/emm/xxm/fuv/bjg/./qbd/./../pir/dhu/./../../wrm/grm/ach/jsy/dic/ggz/smq/mhl/./../yte/hou/ucd/vnn/fpf/cnb/ouf/hqq/upz/akr/./pzo/../llb/./tud/olc/zns/fiv/./eeu/fex/rhi/pnm/../../kke/./eng/bow/uvz/jmz/hwb/./././ids/dwj/aqu/erf/./koz/.."));
	}
	
    public static String simplifyPath(String A) {
        
        ArrayList<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder("");
        for (int i=0;i<A.length();i++){
            
            if (A.charAt(i)=='/'){
                strings.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(A.charAt(i));
            
        }
        if (!sb.toString().isEmpty()){
        	strings.add(sb.toString());
        }
        
        Stack<String> st = new Stack<>();
        for (int i=0; i<strings.size(); i++){
            String s = strings.get(i).trim();
            //System.out.println(s);
            if (s.length()==0 || s.equals(".")){
                //System.out.println(s);
                continue;
            }
            if (s.equals("..")){
                if (!st.isEmpty()){
                    st.pop();
                }
                continue;
            }
            st.push(s);
        }
        String res = "";
        while (!st.isEmpty()){
            res = st.peek()+res;
            st.pop();
            res = "/"+res;
        }
        return res;
    }
}
