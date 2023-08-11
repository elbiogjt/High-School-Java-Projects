import java.util.*;
import java.io.*;

public class MyArrayList<E> {
    private E e;
    private Object[] ar;

    public MyArrayList() {
        ar = new Object[0];
    }

    public boolean add(E newE) {
        Object[] nar=new Object[ar.length+1];
        nar[ar.length]=newE;
        int ct=0;
        for(int i=0;i<nar.length-1;i++){
            nar[i]=ar[i];
        }
        ar=nar.clone();
        return true;
    }

    public boolean add(E newE, int index) {
        Object[] nar=new Object[ar.length+1];
        int ct=0;
        for(int i=0;i<nar.length;i++){
            if (i == index) {
                nar[i] = newE;
            } else if (i<index) {
                nar[i]=ar[i];
            } else if (i>index) {
                nar[i]=ar[i-1];
            }
        }
        ar=nar.clone();
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int x){
        return (E) ar[x];
    }
    
    @SuppressWarnings("unchecked")
    public E remove(int x){
        Object[] nar=new Object[ar.length-1];
        Object temp = new Object();
        int ct=0;
        for (int i=0; i<ar.length; i++) {
            if (i != x) {
                nar[ct] = ar[i];
                ct++;
            } else {
                temp = ar[i];
            }
        }
        ar=nar;
        return (E) temp;
    }

    @SuppressWarnings("unchecked")
    public E remove(E x){
        Object[] nar=new Object[ar.length-1];
        Object temp = new Object();
        int ct=0;
        for (int i=0; i<ar.length; i++) {
            if (ar[i] != x) {
                nar[ct] = ar[i];
                ct++;
            } else {
                temp = ar[i];
            }
        }
        ar=nar;
        return (E) temp;
    }
    
    public void set(int i, E rep) {
        ar[i] = rep;
    }

    @SuppressWarnings("unchecked")
    public String toString() { 
        String tot = "";
        for (int i=0; i<ar.length; i++) {
            tot += ar[i].toString() + "\n";
        }
        return tot;
    }
    
    public int size(){
        return ar.length;
    }
}