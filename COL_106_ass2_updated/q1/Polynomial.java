public class Polynomial extends LinkedList{
    
    public Polynomial add(Polynomial p){
        //to be implemented by the student
        Node a;// head of the polynomial with larger size
        Node b;// head of the polynomial with smaller size
        if(p.len()>size){
         a= p.head;
         b = head;
        }
        else{
         a = head;
         b= p.head;
        }
        Polynomial ans = new Polynomial();
        int n= Math.max(p.len(),size);
        int m = Math.min(p.len(),size);
        int diff = n-m;
        while(diff>0){
            ans.insert(a.data);
            a=a.next;
            diff--;
        }
        while(a!=null){
            ans.insert(a.data+b.data);
            a=a.next;
            b=b.next;
        }
        // trimming zeroes
        Node temp=ans.head;
        while(temp.next!=null){
            if(temp.data!=0) {
                break;
            }
            else{
                temp=temp.next;
            }
        }
        ans.head=temp;
        return ans;
    }

    public Polynomial add_helper(Polynomial p){
        Node a;// head of the polynomial with larger size
        Node b;// head of the polynomial with smaller size
        if(p.len()>size){
         a= p.head;
         b = head;
        }
        else{
         a = head;
         b= p.head;
        }
        Polynomial ans = new Polynomial();
        int n= Math.max(p.len(),size);
        int m = Math.min(p.len(),size);
        int diff = n-m;
        while(diff>0){
            ans.insert(a.data);
            a=a.next;
            diff--;
        }
        while(a!=null){
            ans.insert(a.data+b.data);
            a=a.next;
            b=b.next;
        }
        return ans;
    }


    public Polynomial mult(Polynomial p){
        //to be implemented by the student
        Node a;// head of the polynomial with larger size
        Node b;// head of the polynomial with smaller size
        if(p.len()>size){
         a= p.head;
         b = head;
        }
        else{
         a = head;
         b= p.head;
        }
        int size_of_each_list = p.len() +size -1;
        int num_zeroes_that_should_be_added = size_of_each_list - Math.max(p.len(), size);
        int num_list_added=0;
        int num_list_that_should_be_added=Math.min(p.len(),size);
        Polynomial final_ans = new Polynomial();
        for(int i=0;i<size_of_each_list;i++){
            final_ans.insert(0);
        }
        while(num_list_added<num_list_that_should_be_added){
            Polynomial ans = new Polynomial();
            for(int i=0;i<num_list_added;i++){
                ans.insert(0);
            }
            Node c=a;
            for(int i=0;i<Math.max(size,p.len());i++){
                ans.insert(c.data*b.data);
                c=c.next;
            }
            for(int i=0;i<num_zeroes_that_should_be_added-num_list_added;i++){
                ans.insert(0);
            }
            final_ans=final_ans.add_helper(ans);
            num_list_added++;
            b=b.next;
        }
        // trimming zeroes
        Node temp=final_ans.head;
        while(temp.next!=null ){
            if(temp.data!=0) {
                break;
            }
            else{
                temp=temp.next;
            }
        }
        final_ans.head=temp;
        return final_ans;
    }
    // public static void main(String[] args) {
    //     Polynomial p1 = new Polynomial();
    //     Polynomial p2 = new Polynomial();
    //     p1.insert(-1);
    //     p1.insert(-2);
    //     p2.insert(1);
    //     p2.insert(2);
    //     Polynomial p3 = p1.add(p2);
    //     p3.display();
    //     System.out.println(p3.len());
    //     Polynomial p4 = p1.mult(p2);
    //     p4.display(); 
    //     System.out.println(p4.len());
    // }

}