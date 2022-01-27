public class LinkedList<T>
{
    Node<T> front = null, rear = null;
    int size = 0;

    /**
     * Add an element to the current list
     * @param element an element to add to the linked list
     * */
    void add(T element)
    {
        Node newNode = new Node(element);
        Node curr = front;
        if (curr == null){
            rear = newNode;
            front = rear;
            size++;
        }
        else
        {
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
            newNode.setIndex(curr.getIndex() + 1);
            rear = newNode;
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
            front.setIndex(1);
            size++;
        }
        else
        {
            Node temp = front;
            Node node = new Node(element);
            node.setNext(front);
            front = node;
            incrementNextNodes(front.getNext());
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
            rear = newNode;
            front = rear;
            front.setIndex(1);
            size++;
        }
        else
        {
            rear.setNext(newNode);
            int currIndex = rear.getIndex();
            rear = rear.getNext();
            rear.setIndex(currIndex + 1);
            size++;
        }
    }

    /**
     * Get the element of the provided index argument
     * @param index an int value
     * */
    T get(int index){
        Node curr = front;
        if (index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        while (curr.getIndex() != index && curr != null){
            curr = curr.getNext();
        }
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
        while (curr.getElement() != key && curr != null)
        {
            //we should not increment index because we will return the wrong index if we don't find our key in the list
            curr = curr.getNext();
        }

        //check if curr is null, skip if it is not to avoid throwing an exception.
        //if it is not null, then check if the current node's element equals our key
        if (curr != null & curr.getElement() == key) index = curr.getIndex();

        return index;
    }

    /**
     * Insert an element at a particular index
     * @param index an int value
     * @param element an element or T value
     * */
    void insert(int index, T element){
        //to avoid going out of bounds check underflow and overflow
        if (index < size || index > size) add(element);
        else{
            Node newNode = new Node(element);
            Node curr = front;
            while (curr.getIndex() != index && curr != null){
                curr = curr.getNext();
            }
            Node tempNode = curr.getNext();
            curr.setNext(newNode);
            newNode.setNext(tempNode);
            newNode.setIndex(curr.getIndex() + 1 );
            incrementNextNodes(newNode.getNext());
            size++;
        }
    }

    /**
     * Delete a value in the linked list if it exists
     * @param key an element to find in the linked list
     * */
    void delete(T key){
        Node curr = front;
        if (curr.getElement() == key) {
            front = curr.getNext();
            curr.setNext(null);
        }
        while (curr.getNext() != null && curr.getNext().getElement() != key)
        {
            curr = curr.getNext();
        }

        if (curr != null & curr.getNext().getElement() == key){
            //set the curr node's next node to be the next node of the next node.
            curr.setNext(curr.getNext().getNext());
            //set the next of the next node to null
            curr.getNext().setNext(null);
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
        //if empty return nothing
        if (size  == 0) return "";
        //iterate until there are no more
        while (curr != null){
            if (curr.getNext() == null){
                text = text + curr.getNext();
            } else {
                text = text + curr.getElement() + " -> ";
            }
        }
        return text;
    }

    /**
     * Increment the index of the node passed as an argument, and the nodes after it.
     * @param node a Node
     * */
    private void incrementNextNodes(Node node){
        Node curr = node;
        node.setIndex(node.getIndex() + 1);

        while (curr != null)
        {
            curr = curr.getNext();
            curr.setIndex(curr.getIndex() + 1 );
        }
    }

    /**
     * Decrement the index of the node passed as an argument, and the nodes after it.
     * @param node a Node
     * */
    private void decrementNextNodes(Node node){
        Node curr = node;
        node.setIndex(node.getIndex() - 1);

        while (curr != null)
        {
            curr = curr.getNext();
            curr.setIndex(curr.getIndex() - 1 );
        }
    }



}

class Node<T>{
    private T element;
    private Node next;
    private int index;

    public Node(){
        element = null;
        this.next = null;
    }

    public Node(T element) {
        this.element = element;
        this.next = null;
    }

    T getElement(){
        return this.element;
    }

    void setElement(T element){
        this.element = element;
    }

    Node<T> getNext(){
        return this.next;
    }

    void setNext(Node<T> node){
        this.next = node;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
