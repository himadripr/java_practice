package org.himadri.practice.java_practice.linked_list;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

    342 + 465 = 807
Make sure there are no trailing zeros in the output list
So, 7 -> 0 -> 8 -> 0 is not a valid response even though the value is still 807.
 * 
 *
 */
 
 class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
 }
 
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode A, ListNode B) {
        int carry = 0;
        ListNode head = null, pre=null, maybe=null, cur = null;
        int sum=0;
        boolean isfz = true;
        while (A!=null && B!=null){
            sum=A.val+B.val+carry;
            cur = new ListNode(sum%10);
            carry=sum/10;
            if (head==null){
                head=cur;
            }
            if (cur.val==0){
                if (isfz && pre!=null){
                    maybe=pre;
                    isfz = false;
                }
                
            } else {
                isfz=true;
                maybe=null;
            }
            if (pre!=null){
                pre.next = cur;
            }
            
            pre=cur;
            A=A.next;
            B=B.next;
        }
        
        ListNode C = null;
        if (A!=null){
            C=A;
        } else if (B!=null){
            C=B;
        }
        
        while (C!=null){
            sum=C.val+carry;
            cur = new ListNode(sum%10);
            carry=sum/10;
            if (head==null){
                head=cur;
            }
            if (cur.val==0){
                if (isfz && pre!=null){
                    maybe=pre;
                    isfz = false;
                }
                
            } else {
                isfz=true;
                maybe=null;
            }
            if (pre!=null){
                pre.next = cur;
            }
            
            pre=cur;
            C=C.next;
        }
        
        if (carry!=0){
            cur = new ListNode(carry);
            maybe=null;
            pre.next = cur;
        }
        
        if (maybe!=null){
            maybe.next=null;
        }
        return head;
        
    }
    
    public static void main(String[] args) {
		ListNode A = new ListNode(1);
		ListNode B = new ListNode(9);
	}
}