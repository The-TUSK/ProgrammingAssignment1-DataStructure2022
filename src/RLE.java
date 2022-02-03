public class RLE {
    /**
     * Takes a Single Linked List as a paramater and prints an encoded string
     * @param list a single linked list
     * */
    String encode(LinkedList list)
    {
        String answer = "";
        int copies = 0;
        char character = 0;
        //iterate through the list
        Node curr = list.front;
        //iterate to the end of the list
        while (curr != null)
        {
            //if the character has not been initialized, initialize it with the current node's element
            if (character == 0) {
                character = (char) curr.getElement();
                //increment our copy
                copies++;
            }
            //if we have detected a different character
            else if (character != (char) curr.getElement()){
                //add our copies and character to our answer
                answer += ""+ copies + character;
                //change our current character to the current node's char
                character = (char) curr.getElement();
                //reset our copies to 1
                copies = 1;
            }
            //if the character var is initialized, and we have seen the current node's character, we increment our copy
            else {
                copies++;
            }
            //iterate in the list
            curr = curr.getNext();
        }
        answer += ""+ copies + character;
        return answer;
    }
    
    /**
     * takes an encoded string as the parameter, turns it into a single linked list and prints its contents
     * @param text a string value
     * */
    LinkedList decode(String text)
    {
        int copy = 0;
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < text.length(); i++)
        {
            //is the current character a number?
            if (Character.isDigit(text.charAt(i))){
                //if copy has not been initialized
                if (copy == 0) copy = Character.getNumericValue(text.charAt(i));
                //if another number is detected attach that number to the current copy
                else{
                    copy = Integer.parseInt( Integer.toString(copy)  + text.charAt(i));
                }
            }
            //if the current character is not a number
            else {
                //iterate until we have added all the copies
               while (copy > 0){
                   copy--;
                   list.add(text.charAt(i));
               }
            }
        }
        return list;
    }
    
    /**
     * takes to single linked lists as parameters and returns true if they represent the same sequence, false otherwise
     * @param list1 a single linked list
     * @param list2 a single linked list
     * */
    boolean equal(LinkedList list1, LinkedList list2)
    {
        boolean answer = true;
        Node curr = list1.front;
        Node curr2 = list2.front;
        //if either nodes are empty or null
        if (list1 == null || list2 == null) return false; //if it's not a linked list to begin with then it's false
        else if (list1.getSize() == 0 && list2.getSize() == 0) return true; //if the nodes are both empty
        else if (list1.getSize() == 0 || list2.getSize() == 0) return false; //if one but not the other is empty
        //while the node has a next node iterate
        while (curr.hasNext()){
            //if one element does not equal the element of the second list, return false
            if (curr.getElement() != curr2.getElement()) return false;

            //iterate to next node
            curr = curr.getNext();
            curr2 = curr2.getNext();
        }
        return answer;
    }
}
