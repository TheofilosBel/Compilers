package compiler.exceptions;

import java.util.LinkedList;

public class CompilerErrorList {

    LinkedList<String> errorList; 
    
    /* Create empty List*/
    public CompilerErrorList() {
        this.errorList = new LinkedList<String>();
    }
    
    /*
     * Adds an error to the error list
     */
    public void addToList(String errMsg) {
        this.errorList.add(errMsg);
    }
    
    /*
     * Prints every error message in the list with \n between them 
     */
    public void printList() {
        for (int msg = 0; msg < this.errorList.size(); msg++) {
            System.out.println(this.errorList.get(msg));
            System.out.println();
        }
    }
    
    
    /* Getters - Setter */
    public LinkedList<String> getErrorList() {
        return this.errorList;
    }

}
