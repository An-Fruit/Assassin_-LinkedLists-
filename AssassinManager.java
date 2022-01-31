//***********************************************************************************//
//	Partner Name: Thanh Duong														 //
//	Name: Andre Zhang																 //
//																					 //
//																					 //
//																					 //
//***********************************************************************************//
import java.util.*;

public class AssassinManager {
    // YOUR CODE GOES HERE

	AssassinNode first;
	AssassinNode newDead;

	public AssassinManager(ArrayList<String> names) {
		newDead = null;
		try {
			first = new AssassinNode(names.get(0));
			AssassinNode cur = first;
			for (int i=1;i<names.size();i++) {
				cur.next = new AssassinNode(names.get(i));
				cur = cur.next;
			}
			cur.next = first;
		}
		catch(Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public String killRing() {
		String print = "";
		if (isGameOver()) 
			return first.name + " won the game!";
		AssassinNode cur = first;
		while(cur.next != first) {
			print += cur.name + " is stalking " + cur.next.name + "\n";
			cur = cur.next;
		}
		print += cur.name + " is stalking " + cur.next.name + "\n";
		return print;
	}
	
	public String graveyard() {
		if (newDead == null) {
			return "";
		}
		String print = "";
		AssassinNode cur = newDead ;
		while(cur.next != null) {
			print += cur.name + " was killed by " + cur.killer + "\n";
			cur = cur.next;
		}
		print += cur.name + " was killed by " + cur.killer + "\n";
		return print;
	}
	
	public boolean killRingContains(String name) {
		String n = name.toLowerCase();
		AssassinNode cur = first;
		int count =0;
		while(cur.next != first) {
			if (cur.name.toLowerCase().equals(n)) {
				return true;
			}
			cur = cur.next;
		}
		if (cur.name.toLowerCase().equals(n)) {
			return true;
		}
		return false;
		
	}
	
	public boolean graveyardContains(String name) {
		if (newDead == null) {
			return false;
		}
		String n = name.toLowerCase();
		AssassinNode cur = newDead;
		while(cur.next != null) {
			if (cur.name.toLowerCase().equals(n)) {
				return true;
			}
			cur = cur.next;
		}
		if (cur.name.toLowerCase().equals(n)) {
			return true;
		}
		return false;
	}
	
	public String winner() {
		if (isGameOver()) 
			return first.name;
		return null;
	}
	
	public void kill(String name) {
		if (isGameOver())  {
			throw new IllegalStateException();
		}
		if (!killRingContains(name))  {
			throw new IllegalArgumentException();
		}	
		String n = name.toLowerCase();
		AssassinNode cur = first.next;
		AssassinNode pre = first;
		while(cur != first) {
			if (cur.name.toLowerCase().equals(n)) {
				
				pre.next = cur.next;
				cur.next = newDead;
				newDead = cur;
				cur.killer = pre.name;
				return;
			}
			cur = cur.next;
			pre = pre.next;
		}
		if (cur.name.toLowerCase().equals(n)) {
			first = first.next;
			pre.next = first;
			AssassinNode dead = new AssassinNode(cur.name,cur.next);
			dead.next = newDead;
			dead.killer = pre.name;
			newDead = dead;
			return;
		}
		return;
	}
	
	
	public boolean isGameOver() {
		return first.next == null || first.next == first;
	}
	
	public static void main(String[] args) {
		ArrayList<String> bob = new ArrayList<String>(Arrays.asList("Bill Gates","Tim Apple","President Obama","Toby Maguire","Michael B. Jordan", "Big Chungus","Sherlock Holmes","Alex Jones"));
		AssassinManager tim = new AssassinManager(bob);
		System.out.println(tim.killRing());
		tim.kill("bill gates");
		System.out.println(tim.killRing());

		
		
		
	}
}