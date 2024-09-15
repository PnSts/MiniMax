/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

import java.util.Random;

/**
 *
 * @author Panagiotis Tsoukalas
 */
public class MiniMax {

    /**
     * @param args the command line arguments
     */
    final static int M = 20;    //ΣΥΝΟΛΙΚΟΣ ΑΡΙΘΜΟΣ ΚΥΒΩΝ
    final static int K = 4;     //ΤΡΙΤΗ ΕΝΑΛΛΑΚΤΙΚΗ ΠΟΣΟΤΗΤΑ ΑΦΑΙΡΕΣΗΣ ΚΥΒΩΝ 
    public static int max_choice; //ΠΟΣΟΤΗΤΑ ΚΥΒΩΝ ΠΟΥ ΕΧΕΙ Ο ΚΟΜΒΟΣ ΠΟΥ ΕΠΙΛΕΓΕΙ Ο MAX
    
    public static void minimax(int state){        
        //ΤΙΜΗ ΑΡΧΙΚΟΥ ΚΟΜΒΟΥ ΜΑΧ
        int f = max_nodes(state);
        //ΠΑΡΑΔΟΧΗ: ΕΠΙΛΟΓΗ ΑΦΑΙΡΕΣΗΣ 1 ΚΥΒΟΥ, ΑΝ Ο ΜΑΧ ΠΑΙΡΝΕΙ ΤΙΜΗ -1
        if(f == -1){max_choice = state-1;}
    }
    
    //ΚΟΜΒΟΙ ΜΑΧ
    public static int max_nodes(int state){
        //ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΜΒΩΝ ΕΝΑΛΛΑΚΤΙΚΩΝ ΕΠΙΛΟΓΩΝ
        int node_1 = -1;
        int node_2 = -1;
        int node_K = -1;
        //ΑΡΧΙΚΟΠΟΙΗΣΗ
        max_choice = -1;
        //ΑΡΙΣΤΕΡΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ 1 ΚΥΒΟΥ
        if(state-1>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_1 = min_nodes(state-1); 
        }else if (state-1==0){ //ΕΥΡΕΣΗ ΦΥΛΛΟΥ  
            max_choice = 0;    //ΚΥΒΟΙ ΠΟΥ ΑΝΤΙΣΤΟΙΧΟΥΝ ΣΤΟ ΦΥΛΛΟ
            node_1 = 1;        //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ
        }
        //ΜΕΣΑΙΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ 2 ΚΥΒΩΝ
        if(state-2>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_2 = min_nodes(state-2);
        }else if (state-2==0){  //ΕΥΡΕΣΗ ΦΥΛΛΟΥ
            max_choice = 0;     //ΚΥΒΟΙ ΠΟΥ ΑΝΤΙΣΤΟΙΧΟΥΝ ΣΤΟ ΦΥΛΛΟ
            node_2 = 1;         //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ 
        }
        //ΔΕΞΙΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ Κ ΚΥΒΩΝ
        if(state-K>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_K = min_nodes(state-K);
        }else if (state-K==0){  //ΕΥΡΕΣΗ ΦΥΛΛΟΥ
            max_choice = 0;     //ΚΥΒΟΙ ΠΟΥ ΑΝΤΙΣΤΟΙΧΟΥΝ ΣΤΟ ΦΥΛΛΟ
            node_K = 1;         //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ
        }
        //ΑΡΧΙΚΟΠΟΙΗΣΗ
        int max = node_1;
        max_choice = state-1;
        //ΕΥΡΕΣΗ ΑΠΟΓΟΝΟΥ ΜΕ ΜΕΓΑΛΥΤΕΡΗ ΑΞΙΑ Κ ΑΝΤΙΣΤΟΙΧΗΣ ΠΟΣΟΤΗΤΑΣ ΚΥΒΩΝ
        if(node_2>max) {
            max = node_2;
            max_choice = state-2;
        }
        if(node_K>max) {
            max = node_K;
            max_choice = state-K;
        }
        //ΣΕ ΠΕΡΙΠΤΩΣΗ ΙΔΙΑΣ ΑΞΙΑΣ ΑΠΟΓΟΝΩΝ ΑΝ ΟΙ ΚΥΒΟΙ ΚΑΠΟΙΟΥ ΑΠΟΓΟΝΟΥ ΕΙΝΑΙ 0
        //ΤΟΤΕ ΕΠΙΛΕΓΕΤΑΙ ΩΣ ΒΕΛΤΙΣΤΗ ΛΥΣΗ
        if(node_1==node_2 || node_1==node_K ||node_2==node_K){
            if(state-1==0) max_choice=state-1;
            else if(state-2==0) max_choice=state-2;
            else if(state-K==0) max_choice=state-K;
        }

        return max;
    }
    
    //ΚΟΜΒΟΙ ΜΙΝ
    public static int min_nodes(int state){
        //ΑΡΧΙΚΟΠΟΙΗΣΗ ΚΟΜΒΩΝ ΕΝΑΛΛΑΚΤΙΚΩΝ ΕΠΙΛΟΓΩΝ
        int node_1 =0;
        int node_2;
        int node_K;
        
        //ΑΡΙΣΤΕΡΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ 1 ΚΥΒΟΥ
        if(state-1>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_1 = max_nodes(state-1);
        }else if (state-1==0){  //ΕΥΡΕΣΗ ΦΥΛΛΟΥ
            node_1 = -1;        //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ
        }
        //ΑΡΧΙΚΟΠΟΙΗΣΗ
        int min = node_1;
        //ΔΕΞΙΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ Κ ΚΥΒΩΝ
        if(state-2>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_2 = max_nodes(state-2);
            //ΕΛΕΓΧΟΣ Κ ΟΡΙΣΜΟΣ ΑΠΟΓΟΝΟΥ ΜΕ ΜΙΚΡΟΤΕΡΗ ΑΞΙΑ
            if(node_2<min) {min = node_2;}
        }else if (state-2==0){  //ΕΥΡΕΣΗ ΦΥΛΛΟΥ
            node_2 = -1;        //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ
            //ΕΛΕΓΧΟΣ Κ ΟΡΙΣΜΟΣ ΑΠΟΓΟΝΟΥ ΜΕ ΜΙΚΡΟΤΕΡΗ ΑΞΙΑ
            if(node_2<min) {min = node_2;}
        }
        //ΔΕΞΙΟ ΥΠΟΔΕΝΤΡΟ - ΑΦΑΙΡΕΣΗ Κ ΚΥΒΩΝ
        if(state-K>0){
            //ΑΝΑΖΗΤΗΣΗ ΚΑΤΑ ΒΑΘΟΣ
            node_K = max_nodes(state-K);
            //ΕΛΕΓΧΟΣ Κ ΟΡΙΣΜΟΣ ΑΠΟΓΟΝΟΥ ΜΕ ΜΙΚΡΟΤΕΡΗ ΑΞΙΑ
            if(node_K<min) {min = node_K;}
        }else if (state-K==0){  //ΕΥΡΕΣΗ ΦΥΛΛΟΥ
            node_K = -1;        //ΤΙΜΗ ΕΥΡΕΤΙΚΗΣ ΣΥΝΑΡΤΗΣΗΣ ΦΥΛΛΟΥ
            //ΕΛΕΓΧΟΣ Κ ΟΡΙΣΜΟΣ ΑΠΟΓΟΝΟΥ ΜΕ ΜΙΚΡΟΤΕΡΗ ΑΞΙΑ
            if(node_K<min) {min = node_K;}
        }
          
        return min;
    }
    
    public static void main(String[] args) {
        //ΑΡΧΙΚΟΠΟΙΗΣΗ
        int s = M;
        int min_choice = 1;
        
        System.out.println("ΕΝΑΡΞΗ ΠΑΙΓΝΙΟΥ");
        System.out.println("Έχουμε "+ M +" κύβους");
        //ΠΙΝΑΚΑΣ Κ ΜΕΤΑΒΛΗΤΗ ΓΙΑ ΧΡΗΣΗ RANDOM
        int[] intArray = {1, 2, K};
        int idx;
        
        while(s>0){     //ΟΣΟ ΥΠΑΡΧΟΥΝ ΑΚΟΜΑ ΚΥΒΟΙ
            //ΠΑΙΖΕΙ Ο ΠΑΙΚΤΗΣ ΜΑΧ
            minimax(s);            
            System.out.println("Ο παίκτης ΜΑΧ θα αφαιρέσει "+ (s - max_choice) + " κύβο/ους");
            //ΚΥΒΟΙ ΠΟΥ ΜΕΝΟΥΝ          
            s = max_choice;                
            //ΑΝΑΚΟΙΝΩΣΗ ΝΙΚΗΤΗ Ή ΣΥΝΕΧΕΙΑ ΠΑΙΧΝΙΔΙΟΥ    
            if(s>0){
                //ΠΑΙΖΕΙ Ο ΠΑΙΚΤΗΣ ΜΙΝ - ΤΥΧΑΙΑ ΕΠΙΛΟΓΗ ΑΡΙΘΜΟΥ ΚΥΒΩΝ
                do{ //ΕΛΕΓΧΟΣ ΕΓΚΥΡΗΣ ΠΟΣΟΤΗΤΑΣ ΑΦΑΙΡΕΣΗΣ ΚΥΒΩΝ
                    idx = new Random().nextInt(intArray.length);
                    min_choice = intArray[idx];
                }while(max_choice - min_choice<0);
                 
                System.out.println("Ο παίκτης ΜΙΝ αφαιρεί "+ min_choice + " κύβο/ους");
                //ΚΥΒΟΙ ΠΟΥ ΜΕΝΟΥΝ
                s -= min_choice;
            
                if(s==0) {System.out.println("ΝΙΚΗΤΗΣ: Ο παίκτης ΜIN");}
                
            }else{ 
                System.out.println("ΝΙΚΗΤΗΣ: Ο παίκτης ΜΑΧ");
            }                       
        }                
    }
    
}
