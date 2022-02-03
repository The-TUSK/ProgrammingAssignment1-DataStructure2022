public class LinkedList<T>
{
    Node<T> front = null, rear = null;
    private int size = 0;

    /**
     * Add an element to the current list
     * @param element an element to add to the linked list
     * */
    void add(T element)
    {
        Node newNode = new Node(element);
        Node curr = front;
        //if our list is empty
        if (curr == null){
            rear = newNode;
            front = rear;
            size++;
        }
        //if our list is not empty
        else
        {
            //if we are not at the end of the list iterate
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            //add our new node to the rear or the rear's next node
            curr.setNext(newNode);
            //set the index of our new node
            newNode.setIndex(curr.getIndex() + 1);
            //change our rear
            rear = newNode;
            //increment our size
            size++;
        }
    }

    /**
     * Add an element to the front of the list
     * @param element an element to add to the linked list
     * */
    void addFront(T element)
    {
        Node newNode = new Node(element);
        //check if the list is empty
        if (size == 0)
        {
            rear = newNode;
            front = rear;
            front.setIndex(0);
            size++;
        }
        else
        {
            Node temp = front;
            //set the next node of our new node to front
            newNode.setNext(front);
            //set our front as our new node
            front = newNode;
            //set our new node's index
            front.setIndex(0);
            //increment the indexes of the nodes after our new front node
            incrementNextNodes(front.getNext());
            //increment the size of the list
            size++;
        }
    }

    /**
     * Add an element to the end of the list
     * @param element an element to add to the linked list
     * */
    void addRear(T element)
    {
        Node newNode = new Node(element);
        //check if the list is empty
        if (size == 0)
        {
            //make our new node the rear
            rear = newNode;
            //make our new node the front
            front = rear;
            //set the index of our node to 0
            front.setIndex(0);
            //increment our size
            size++;
        }
        else
        {
            //set the rear's next to our new node
            rear.setNext(newNode);
            //keep track of our rear's index
            int currIndex = rear.getIndex();
            //change our rear to our new node
            rear = rear.getNext();
            //set our new rear's index
            rear.setIndex(currIndex + 1);
            //increment the size of the list
            size++;
        }
    }

    /**
     * Get the element of the provided index argument
     * @param index an int value
     * */
    T get(int index){
        Node curr = front;
        // if the size of the index the user is asking for is larger than the size of the list or smaller than zero, throw error
        if (index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        //iterate until we have reached we reach the node with the index passed as an argument.
        while (curr.getIndex() != index){
            curr = curr.getNext();
        }
        //return the element of that node
        return (T) curr.getElement();
    }

    /**
     * Get the index of a given key
     * @param key an element to search for in the list
     * */
    int search(T key){
        int index = -1;
        Node curr = front;

        //iterate while we don't find the key, or the current is not null
        while (curr != null && curr.getElement() != key)
        {
            //we should not increment index because we will return the wrong index if we don't find our key in the list
            curr = curr.getNext();
        }

        //check if curr is null, skip if it is not to avoid throwing an exception.
        //if it is not null, then check if the current node's element equals our key
        if (curr != null && curr.getElement() == key) index = curr.getIndex();

        return index;
    }

    /**
     * Insert an element at a particular index
     * @param index an int value
     * @param element an element or T value
     * */
    void insert(int index, T element){
        //to avoid going out of bounds check underflow and overflow
        if (front == null || index < 0 || index > size) add(element);
        else{
            Node newNode = new Node(element);
            Node curr = front;
            //iterate until we find the index we are looking for
            while (curr.getIndex() != index){
                curr = curr.getNext();
            }
            if (curr != null)
            {
                //keep track of the next node of the curr node
                Node tempNode = curr.getNext();
                //set the next node of curr to our new node
                curr.setNext(newNode);
                //set the next or our new node to the curr's previous next
                newNode.setNext(tempNode);
                //set the index of our new node
                newNode.setIndex(curr.getIndex() + 1 );
                //increment the indexes of the nodes after our new node
                incrementNextNodes(newNode.getNext());
                //increment the size of our list
                size++;
            }
        }
    }

    /**
     * Delete a value in the linked list if it exists
     * @param key an element to find in the linked list
     * */
    void delete(T key){
        Node curr = front;
        //if our key is equal to the element at the front of the list.
        if (curr.getElement() == key) {
            //set our front to the next of our current front
            front = curr.getNext();
            //set the next of our previous front to null
            curr.setNext(null);
        }
        //iterate until we find the node with an element == key
        while (curr.getNext().getNext() != null && curr.getNext().getElement() != key)
        {
            curr = curr.getNext();
        }

        if (curr.getNext() != null && curr.getNext().getElement() == key){
            Node tempNode = curr.getNext().getNext();
            curr.getNext().setNext(null);
            //set the curr node's next node to be the next node of the next node.
            curr.setNext(tempNode);
            //decrement the indexes of the next nodes
            decrementNextNodes(curr.getNext());
            size--;
        }
    }

    /**
     * Returns the size of the linked list
     * */
    int getSize(){
        return this.size;
    }

    /**
     * Create a string value for the linked list
     * */
    public String toString(){
        String text = "";
        Node curr = front;
        while (curr != null){
            if (curr.getNext() == null) {
                text += curr.getElement();
            } else {
                text += curr.getElement() + "->";
            }
            curr = curr.getNext();
        }
        return text;
    }

    /**
     * Increment the index of the node passed as an argument, and the nodes after it.
     * @param node a Node
     * */
    private void incrementNextNodes(Node node){
        Node curr = node;
        while (curr != null)
        {
            curr.setIndex(curr.getIndex() + 1 );
            curr = curr.getNext();
        }
    }

    /**
     * Decrement the index of the node passed as an argument, and the nodes after it.
     * @param node a Node
     * */
    private void decrementNextNodes(Node node){
        Node curr = node;
        while (curr != null)
        {
            curr.setIndex(curr.getIndex() - 1 );
            curr = curr.getNext();
        }
    }


}

class Node<T>{
    private T element;
    private Node next;
    private int index;

    //initialize our node without params
    public Node(){
        element = null;
        this.next = null;
    }


    //initialize our node with an element
    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    //get the element of our node
    T getElement(){
        return this.element;
    }

    //set the element of our node
    void setElement(T element){
        this.element = element;
    }

    //get the next of our node
    Node<T> getNext(){
        return this.next;
    }

    //set the next of our node
    void setNext(Node<T> node){
        this.next = node;
    }

    //getting the index of our node
    public int getIndex() {
        return index;
    }

    //setting the index of our node
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *  Returns true of there is a node after the current node
     * */
    boolean hasNext(){
        return this.next != null;
    }

}
