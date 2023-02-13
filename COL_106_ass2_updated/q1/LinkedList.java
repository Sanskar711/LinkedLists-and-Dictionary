public class LinkedList{ 
    
    public Node tail;
    public Node head;
    public int size=0;
    
    public LinkedList(){
        head = null;
        tail=null;
    }

    public void insert(int c){
        //to be completed by the student
        Node z= new Node(c);
        if(size==0){ tail =z;head=z;}
        else{
            tail.next=z;
            tail=z;
        }
        size+=1;
    }

    public int len(){
        //to be completed by the student
        if(tail==null){
            return 0;
        }
        int len=0;
        Node temp = head;
        while(temp!=null){
            len++;
            temp=temp.next;
        }
        size=len;
        return len;
    }
    public void display(){
        //to be completed by the student
        Node temp = head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.println();
    }
}


