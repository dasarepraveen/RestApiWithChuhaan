public class linkedList {

    Node head;
    static class Node{
        Node next;
        int data;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public void PrintLL(){
        Node current = head;
        while (current!=null){
            System.out.print("->" +current.data);
            current=current.next;
        }
    }
    public int leanght(){
        int count =0;
        Node current = head;
        while (current!=null){
            count++;
            current=current.next;
        }
        return count;
    }

    public void inserAtTheBegining(int data){
        Node new_node = new Node(data);
        new_node.next=head;
        head=new_node;
    }

    public void inserAtTheEnd(int data){
        Node newNode = new Node(data);
        Node current = head;
        while (current.next!=null){
            current=current.next;
        }
        current.next=newNode;

    }

    public void inserAfterGivenvalue(int givenvalue,int value){
        Node newNode = new Node(value);
        Node current = head;
        while (current.data!=givenvalue){
            current=current.next;
        }
        newNode.next=current.next;
        current.next=newNode;
    }

    public static void main(String[] args){
        linkedList linkedlist = new linkedList();
        linkedlist.head=new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);
        Node fourth = new Node(40);
        linkedlist.head.next=second;
        second.next=third;
        third.next=fourth;
        linkedlist.PrintLL();
        System.out.println("Leangth is "+linkedlist.leanght());
        linkedlist.inserAtTheBegining(5);
        linkedlist.PrintLL();
        System.out.println("Leangth is "+linkedlist.leanght());
        linkedlist.inserAtTheEnd(50);
        linkedlist.PrintLL();
        System.out.println("Leangth is "+linkedlist.leanght());
        linkedlist.inserAfterGivenvalue(20,25);
        linkedlist.PrintLL();
        System.out.println("Leangth is "+linkedlist.leanght());
    }
}
