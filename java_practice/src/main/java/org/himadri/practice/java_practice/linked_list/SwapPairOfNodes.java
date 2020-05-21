package org.himadri.practice.java_practice.linked_list;

public class SwapPairOfNodes {
	
	static class LLNode{
		int data;
		LLNode next;
		
		LLNode(int data){
			this.data = data;
			next = null;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//creating a LinkedList
		LLNode head = new LLNode(1);
		LLNode curr = head;
		for (int i=2; i<=9; i++) {
			curr.next = new LLNode(i);
			curr = curr.next;
		}
		
		System.out.println("Original List: ");
		if(head!=null) {
			System.out.print(head.data);
			curr = head.next;
		}
		while(curr!=null) {
			System.out.print( "," + curr.data);
			curr = curr.next;
		}
		
		head = swappairOfNodes(head);
		
		System.out.println("\nModified List: ");
		if(head!=null) {
			System.out.print(head.data);
			curr = head.next;
		}
		while(curr!=null) {
			System.out.print( "," + curr.data);
			curr = curr.next;
		}

	}
	
	// 1,2,3,4,5,6,7,8,9 -input
	
	// 2,1,4,3,6,5,8,7,9 -output
	
	private static LLNode swappairOfNodes(LLNode head) {
		if (head==null || head.next==null) {
			return head;
		}
		LLNode prev = head;
		LLNode curr = head.next;
		LLNode next = head.next.next;
		curr.next = prev;
		prev.next = swappairOfNodes(next);
		return curr;
	}
	

}
