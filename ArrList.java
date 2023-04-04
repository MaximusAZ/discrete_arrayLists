import java.util.EmptyStackException;


public class ArrList {

    ///////////////////////////////// VARIABLES ////////////////////////////////////////////////
    
    private Array array;
    private int head;
    private int tail;
    private int total;
    private int cap;
   

    ///////////////////////////////// CONSTRUCTORS /////////////////////////////////////////////

    ArrList() {
        array = new Array(10);
        this.cap = 10;
        head = cap-1;
        tail = 0;
        total = 0;
    }
    
    ArrList(int cap) {
        array = new Array(cap);
        this.cap = cap;
        head = cap-1;
        tail = 0;
        total = 0;
    }


    ////////////////////////////////// FUNCTIONS ///////////////////////////////////////////////


    public void addLast(int num){

        if (total == this.cap){    // FULL array
            if (head == this.cap-1)
                head = -1;
            array.resize(this.cap*2, head+1, total);
            this.cap *= 2;
            tail = total;
            head = this.cap-1;
        }

        array.setVal(tail, num);
        total++;
        tail++;
        if (tail > this.cap)
            tail = 0;
    }



    public void addFirst(int num){
        
        if (total == this.cap) {
            if (head == this.cap-1)
                head=-1;
            array.resize(this.cap*2, head+1, total);
            this.cap *= 2;
            tail = total;
            head = this.cap-1;
        }

        array.setVal(head, num);
        total++;
        head--;
        if (head < 0)
            head = cap-1;

    }



   
    public int get(int i){
        int val = (head+i+1)%this.cap;
        return array.getVal(val);
    }



    public boolean isEmpty(){
        if (total == 0)
            return true;
        else 
            return false;
    }


    

    public int indexOf(int num){
        for (int i=0; i<total; i++){
            if (array.getVal((head+i+1)%this.cap) == num)
                return i;
        }
        return -1;
    }




    public boolean contains(int num){
        for (int i=0; i<array.length(); i++){
            if (array.getVal((head+i+1)%this.cap) == num)
                return true;
        }
        return false;
    }



    
    public int lastIndexOf(int num){
        for (int i=array.length()-1; i>=0; i--){
            if (array.getVal((head+i+1)%this.cap) == num)
                return i;
        }
        return -1;
    }



    
    public int set(int index, int num){
        int val = (head+index+1)%this.cap;
        array.setVal((head+index+1)%this.cap, num);

        return val;
    }


    

    public int size(){
        return total;
    }



    public int removeFirst() throws EmptyListException {
        if (total == 0)
            throw new EmptyStackException();

        int removed = array.getVal((head+1)%this.cap);   // remove the first head value
    
        head = head+1;
        if (head == this.cap)
            head = 0;
        array.setVal(head, 0);
        total--;
        if (head > cap-1)
            head = 0;

        if (total < (this.cap/4)) {
            if (head == this.cap-1)
                head = -1;
            array.resize(this.cap/4, head+1, total);
            cap /= 4;
            head = cap-1;
            tail = total;
            
        }
        
    
        return removed;
    }




    public int removeLast() throws EmptyListException {
        if (total == 0)
            throw new EmptyStackException();

        if (tail == 0)
            tail = this.cap;
        int removed = array.getVal((tail-1)%this.cap);   

        tail = tail-1;
        array.setVal(tail, 0);
        total--;
        if (tail < 0)
            tail = this.cap-1;

        if (total < (this.cap/4)) {
            if (head == this.cap-1)
                head = -1;
            array.resize(cap/4, head+1, total);
            cap /= 4;
            head = cap-1;
            tail = total;
            
        }   
    
        return removed;
    }



    public int removeByIndex(int i) throws EmptyListException {
        if (total == 0)
            throw new EmptyStackException();

        if (head == cap-1)
            head = 0;
        int removed = array.getVal((head+i)%this.cap);   
      
        i = i+1;
        if (head == this.cap)
        head = 0;
        array.setVal(i, 0);
        total--;
        if (head > cap-1)
            head = 0;

        if (total < (this.cap/4)) {
            if (head == this.cap-1)
                head = -1;
            array.resize(cap/4, head+1, total);
            cap /= 4;
            head = cap-1;
            tail = total;
            
        }
        
        return removed;
    }



    // removeByValue()
    public boolean removeByValue(int num) throws EmptyListException {
        boolean flag = false;
        if (total == 0)
            throw new EmptyStackException();

        for (int i=0; i<total; i++){
            if (array.getVal((head+i+1)%this.cap) == num)
                flag = true;
        }
       
        head = head+1;
        if (head == cap)
            head = 0;
        array.setVal(head, 0);
        total--;
        if (total < (this.cap/4)) {
            if (head == this.cap-1)
                head = -1;  
            array.resize(cap/4, head+1, total);
            cap /= 4;
            
        }

    return flag;
        
    }


    ////////////////////////////////////////// TESTING /////////////////////////////////////////////////////

    //used only for testing!!!
    public int getAccessCount() {
        return array.getAccessCount();
    }
    
    //used only for testing!!!
    public void resetAccessCount() {
        this.array.resetAccessCount();
    }


}
