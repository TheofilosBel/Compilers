package compiler.semanticAnalysis;

import compiler.types.*;
import java.util.LinkedList;

/*
 * Attributs of every node added to the exprTypes
 * hashmap during the type checking phase
 */
public class Attributes {

    private Type   type;               /* Type of the node */
    private String place;              /* Place of the node (IntConst or TempVariable or id) */
    private LinkedList<Integer> Next;  /* A lists that holds the quad labels for backpatching the next quads */
    private LinkedList<Integer> True;  /* A lists that holds the quad labels for backpatching the True quads */
    private LinkedList<Integer> False; /* A lists that holds the quad labels for backpatching the False quads */

    public Attributes(Type newType) {
        this.type = newType;
    }

    public Attributes(Type newType, String place) {
        this.type  = newType;
        this.place = place; 
    }

    /* Returns the attribute type */
    public Type getType() {
        return this.type;
    }

    /* Returns the attribute place */
    public String getPlace() {
        return this.place;
    }

    /* Returns Next list */
    public LinkedList<Integer> getNext() {
        return this.Next;
    }

    /* Returns True list */
    public LinkedList<Integer> getTrue() {
        return this.True;
    }

    /* Returns False list */
    public LinkedList<Integer> getFalse() {
        return this.False;
    }

    /* Sets Next list */
    public void setNext(LinkedList<Integer> newList) {
        this.Next = newList;
    }

    /* Sets True list */
    public void setTrue(LinkedList<Integer> newList) {
        this.True = newList;
    }

    /* Sets False list */
    public void setFalse(LinkedList<Integer> newList) {
        this.False = newList;
    }

    /* Makes a new List */
    public void makeList(String list, Integer quadLabel) {
        /* Initialize list - Add integer */
        if (list.equals("Next")) {
            this.Next = new LinkedList<Integer>();
            this.Next.add(quadLabel);
        }
        else if (list.equals("True")) {
            this.True = new LinkedList<Integer>();
            this.True.add(quadLabel);
        }
        else if (list.equals("False")) {
            this.False = new LinkedList<Integer>();
            this.False.add(quadLabel);
        }
    }

    /* Makes a new empty List */
    public void makeEmptyList(String list) {
        /* Initialize list - Add integer */
        if (list.equals("Next"))
            this.Next = new LinkedList<Integer>();
        else if (list.equals("True"))
            this.True = new LinkedList<Integer>();
        else if (list.equals("False"))
            this.False = new LinkedList<Integer>();
    }

    /* Sets the attribute place */
    public void setPlace(String place) {
        this.place = place;
    }

}
