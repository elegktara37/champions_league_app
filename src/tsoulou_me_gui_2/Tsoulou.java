/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsoulou_me_gui_2;

import java.awt.Font;
import sun.audio.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Tsoulou {

    Scanner input = new Scanner(System.in);
    OmadaNode[] data;               //pinadas pou tha periexei komvous
    String[] pin_omadwn;            //pinakas me omades(opws tis eisagei o xristis)
    String[] pin_anakatemenos;      //pinakas pou periexei ta anakatemena onomata omadwn
    int fash;                       //h fash (h alliws ta fulla tou dentrou)
    int level;                      //to epipedo
    int komvoi;                     //sunolikos arithmos kombwn
    int metritis_omadwn = 0;        // metritis pou metraei ton arithmo ton omadwn sunolika ektos epanalipsis tou kathe epipedou
    boolean flagallo = false;       ////flag poy afhnei na dimiourgithei ton pinaka me tous kombous mono mia fora
    boolean flag_isopalia = false;  //flag pou tsekarei an ena mats  pige paratasi
    int counter = 0;                //metritis pou metraei ton arithmo twn omadwn kai auxanetai kata 2 giati h epanalipsi trexei gia zeygari(=2 omades)
    boolean flag = false;           //flag gia to menou fashs...an ginei mia fora kataxwrisi fashs den mporei na xanaginei
    boolean flag_kataxwrisi_omadwn = false; //flag gia na dw an exei ginei eisagwgh omadwn
    boolean flag_klhrwsh = false;   //flag gia na dei an exei ginei klhrwsh

    public void nikitis() {
        ImageIcon eikona = new ImageIcon(getClass().getResource("trophy2mikro.png"));
        if (this.data != null) {
            winnerSound();
            String message = this.data[0].getOnoma();
            JLabel label = new JLabel(message);
            label.setFont(new Font("serif", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, "Νικητής διοργάνωσης!!!", JOptionPane.INFORMATION_MESSAGE, eikona);
            //JOptionPane.showMessageDialog(null, "O νικητής της διοργάνωσης είναι ο/η " + this.data[0].getOnoma(),"Νικητής!!!",JOptionPane.INFORMATION_MESSAGE,eikona);

            //System.out.println("O νικητής της διοργάνωσης είναι η/ο "+this.data[0].getOnoma());
        } else {
            errorSound();
            JOptionPane.showMessageDialog(null, "Δεν έχει βγει ακόμα νικητής.");
        }
    }

    public void dentro() {

        if (flag_klhrwsh == true) {
            if (!flagallo) {                                    //afhnei na dimiourgithei ton pinaka me tous kombous mono mia fora
                level(this.fash);
                komvoi(this.level);
                this.data = new OmadaNode[this.komvoi];         //dhmiourgoyme enan pinaka me kombous megethous oso einai oi sunolikoi komboi
                for (int i = 0; i < this.komvoi; i++) {
                    if (i <= this.komvoi - this.fash - 1) {     // an einai MH fulla (komvoi - fulla -1 einai ta ΜΗ fulla)
                        OmadaNode temp = new OmadaNode();
                        data[i] = temp;                         //gia ta MH fulla dhmiourgw kombous xwris onoma
                    } else {                                    //an einai fulla
                        OmadaNode temp = new OmadaNode();

                        temp.setOnoma(this.pin_anakatemenos[i - this.fash + 1]);  //me to i-this.fash+1 pairnw thn 1h thesh tou pinaka anakatemenos
                        data[i] = temp;                         //gia ta fulla dhmiourgw kombous me onoma apo ton pinaka "anakatemeno"

                    }
                }
                flagallo = true;
            }
            try {
                counter = 0;            //metritis pou metraei ton arithmo twn omadwn
                if (this.level > 1) {  // otan teleiwsei h diorganwsh to level exei ftasei sto 1 ara tha mas petaei mnm sto telos oti teleiwse
                    for (int i = this.komvoi - this.fash - metritis_omadwn / 2; i <= this.komvoi - metritis_omadwn - 1; i += 2) {//o metritis i auxanetai me vima 2 gia na proxwraei ana 2 o pinakas afou tha tsekarei to zeugari (i kai to i+1) opote meta prepei na paei sto i+=2

                        if (i >= this.komvoi - this.fash - metritis_omadwn / 2) {       //h i > this.komvoi - this.fash - 1 - metritis_omadwn / 2
                            JOptionPane.showMessageDialog(null, "Τα ζευγάρια είναι: " + data[i].getOnoma() + " - " + data[i + 1].getOnoma());
                            //System.out.println("Τα ζευγάρια είναι: " + data[i].getOnoma() + " - " + data[i + 1].getOnoma());
                            int goal;
                            do {            //do-while tha ekteleitai oso ta einai idia ta score
                                do {        //do-while gia na tsekarei to score na mhn einai arnitiko ths omadas i
                                    String eisodos = JOptionPane.showInputDialog("Δώσε τα γκολ του/της " + data[i].getOnoma());
                                    //System.out.println("Δώσε τα γκολ του/της " + data[i].getOnoma());
                                    goal = Integer.parseInt(eisodos);
                                    data[i].setGoal(goal);
                                    if (data[i].getGoal() < 0) {
                                        JOptionPane.showMessageDialog(null, "Δώσε έγκυρο Σκόρ");
                                        //System.out.println("Δώσε έγκυρο Σκόρ");
                                    }
                                } while (data[i].getGoal() < 0);

                                do {    //do-while gia na tsekarei to score na mhn einai arnitiko ths omadas i+1
                                    String eisodos2 = JOptionPane.showInputDialog("Δώσε τα γκολ του/της " + data[i + 1].getOnoma());
                                    //System.out.println("Δώσε τα γκολ της του/της " + data[i+1].getOnoma());
                                    goal = Integer.parseInt(eisodos2);
                                    data[i + 1].setGoal(goal);
                                    if (data[i + 1].getGoal() < 0) {
                                        JOptionPane.showMessageDialog(null, "Δώσε έγκυρο Σκόρ");
                                        //System.out.println("Δώσε έγκυρο Σκόρ");
                                    }
                                } while (data[i + 1].getGoal() < 0);

                                if (data[i].getGoal() == data[i + 1].getGoal()) {  //an to score einai idio vgainei ap thn if kai xana ektelei thn do-while
                                    if ((flag_isopalia == true)) {     //exw ena boolean pou an paei paratasei tha exei ginei true, opote an isxuei auto tote tha paei sta penalti
                                        if (data[i].getGoal() == data[i + 1].getGoal()) {
                                            JOptionPane.showMessageDialog(null, "Δώσε Σκόρ Πεναλτι");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Δώσε Σκόρ παράτασης");
                                        //System.out.println("Δώσε Σκόρ παράτασης");
                                    }
                                    flag_isopalia = true;
                                } else {                                            // alliws vazei ston kombo parent to onoma tou nikiti
                                    if (data[i].getGoal() > data[i + 1].getGoal()) {
                                        data[parent(i)].setOnoma(data[i].getOnoma());
                                    } else {
                                        data[parent(i)].setOnoma(data[i + 1].getOnoma());
                                    }
                                }
                            } while (data[i].getGoal() == data[i + 1].getGoal());       //do-while tha ekteleitai oso ta einai idia ta score
                            counter += 2;         //metavliti pou metraei ton arithmo twn omadwn kai auxanetai kata 2 giati h epanalipsi trexei gia zeygari(=2 omades)
                            flag_isopalia = false;      //xanamhdenizw to flag giati tha paei ston epomeno agwna meta kai prepei na einai false pali ex arxis

                        }

                    }
                    metritis_omadwn += counter;      // flag pou metraei ton arithmo ton omadwn sunolika ektos epanalipsis tou kathe epipedou
                    level -= 1;    //to level tha meiwnetai kata ena kathe fora pou diatrexetai plirws ena level
                } else {      //an to level ginei 1 tote h diorganwsh tha exei teleiwsei
                    JOptionPane.showMessageDialog(null, "H διοργάνωση τέλειωσε.");
                    //System.out.println("O νικητής της διοργάνωσης είναι η/ο "+this.data[0].getOnoma());
//        phase/=2;

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Εισάγετε σωστά στοιχεία.");
            }
        } else {
            errorSound();
            JOptionPane.showMessageDialog(null, "Δεν έχετε κάνει κλήρωση.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void klirwsi() {
        //pinakas me 4 tuxaious arithmous
        if (flag_kataxwrisi_omadwn == true) {           //if wste na mhn kanei klirwsi an den exei parei omades
            int pin_arithmwn[] = new int[fash];
            for (int m = 0; m < fash; m++) {
                pin_arithmwn[m] = ((int) (Math.random() * fash));
                for (int j = 0; j < m; j++) {
                    if (pin_arithmwn[m] == pin_arithmwn[j]) {
                        m--;
                        break;
                    }
                }
            }
//        //ektypwnw ton pinaka me tous 4 tuxaious arithmous
//            System.out.println("****πινακας αριθμων*****");
//            for (int m = 0; m < fash; m++) {
//                System.out.println(" " + pin_arithmwn[m]);
//            }
//                System.out.println("***************");
            pin_anakatemenos = new String[fash];//ftiaxnw neo pinaka

            for (int i = 0; i < fash; i++) {//sundew ton pinaka omadwn kai arithmwn, wste na bgei o neos pou einai anakatemenos
                pin_anakatemenos[i] = pin_omadwn[pin_arithmwn[i]];
            }
//            JOptionPane.showMessageDialog(null, "****ANAKATEMENEΣ ΟΜΑΔΕΣ*****");
//            //System.out.println("****ANAKATEMENEΣ ΟΜΑΔΕΣ*****");
//            for (int i = 0; i < fash; i++) {
//                JOptionPane.showMessageDialog(null, pin_anakatemenos[i]);
//                //System.out.println(pin_anakatemenos[i]);
//            }
//            JOptionPane.showMessageDialog(null, "***************");
//            //System.out.println("***************");

            klhrwshSound();
            flag_klhrwsh = true;

        } else {
            errorSound();
            JOptionPane.showMessageDialog(null, "Δεν έχετε εισάγει ομάδες.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
        }

    }

    public int parent(int x) {                  //komvos parent(opou x einai enas int)
        if (x != 0) {
            if (x % 2 == 1) {
                return (int) (x / 2);
            } else {
                return (int) (x / 2) - 1;
            }
        } else {
            return -1;
        }

    }

    //methodo gia upologismo tou level ( epipedo )
    public int level(int fash) {
        this.level = (int) (Math.log(fash) / Math.log(2)) + 1;
        return this.level;
    }

    //methodo gia upologismo komvwn
    public int komvoi(int level) {
        this.komvoi = (int) Math.pow(2, this.level(fash)) - 1;
        return this.komvoi;
    }

    public void eisagwgiOmadwn() {
        if (fash != 0) {   //vazw ena if gia na emfanizei mhnuma sfalmatos an den exei balei th fash
            for (int m = 0; m < fash; m++) {
                try {
                    String eisodos = JOptionPane.showInputDialog(null, "Παρακαλώ εισάγετε τη " + (m + 1) + "η ομάδα.", "Εισαγωγή ομάδας", JOptionPane.QUESTION_MESSAGE);
                    //System.out.println("Παρακαλώ εισάγετε τη " + (m + 1) + "η ομάδα.");
                    pin_omadwn[m] = eisodos;
                    /*me to 2o for tsekarw na mhn uparxei h idia omada idi ston pinaka*/
                    for (int j = 0; j < m; j++) {  //tsekarw an i omada exei idi mpei ston pinaka
                        if (pin_omadwn[m].equals(pin_omadwn[j])) {
                            errorSound();
                            JOptionPane.showMessageDialog(null, "Η ομάδα αυτή έχει προστεθεί ήδη.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
                            //System.out.println("Η ομάδα αυτή έχει προστεθεί ήδη.");
                            m--;
                            break;
                        }
                    }
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Εισάγετε σωστά στοιχεία.");
                }
            }
            flag_kataxwrisi_omadwn = true;
        } else {
            errorSound();
            JOptionPane.showMessageDialog(null, "Δεν έχετε επιλέξει τη φάση.", "Προσοχή", JOptionPane.WARNING_MESSAGE);
            //System.out.println("Δεν έχετε επιλέξει τη φάση.");
        }
        //ektypwnw ton  pinaka omadwn POY DEN EINAI ANAKATEMENOS
//        if (pin_omadwn != null) {
//            System.out.println("****ΟΜΑΔΕΣ*****");
//            for (int m = 0; m < fash; m++) {
//                System.out.println(" " + pin_omadwn[m]);
//            }
//            System.out.println("***************");
//        }

    }

    public void lipsiPhasis() {
        if (flag == true) { //an to flag einai true simainei oti exei ginei kataxwrhsh ths fashs k den ton afhnei na xanakanei
            JOptionPane.showMessageDialog(null, "Η καταχώρηση της φάσης έχει ήδη γίνει.");
        }
        try {
            while (flag == false) {  // h kataxwrhsh ths fashs tha ginei mono mia fora tha ginei true to flag kai den tha xanampei

                String eisodos = JOptionPane.showInputDialog(null, "1.Παρακαλώ εισάγετε τη φάση.", "Εισαγωγή φάσης", JOptionPane.QUESTION_MESSAGE);

                this.fash = Integer.parseInt(eisodos);
                if ((this.fash > 0) && ((this.fash & (this.fash - 1)) == 0)) {  //tsekarei an h fash einai egkyrh (dunami tou 2)
                    pin_omadwn = new String[this.fash]; // dhmiourgei ena keno pinaka mhkous fash
                    flag = true;
                } else {
                    errorSound();
                    JOptionPane.showMessageDialog(null, "Mη έγκυρη φάση.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Εισάγετε σωστά στοιχεία.");
        }

    }
//
//    static int print_menu() {
//        System.out.println("1.Εισαγωγή φάσης." + "\n" + "2.Εισαγωγή ομάδων (και προβολή τους)." + "\n" + "3.Κλήρωση."
//                + "\n" + "4.Εισαγωγή αποτελεσμάτων" + "\n" + "5.Νικητής διοργάνωσης" + "\n" + "6.Έξοδος");
//        Scanner key1 = new Scanner(System.in);
//        int y = key1.nextInt();//y είναι η επιλογή στο μενού
//        return y;
//    }

    public void winnerSound() {
        InputStream in;
        try {
            String dir = System.getProperty("user.dir");
            //File f = new File(getClass().getResource("/folder/winning_sound.wav").getFile());
            //in = new FileInputStream(f);
            in = getClass().getResourceAsStream("/folder/winning_sound.wav");
            //in = new FileInputStream(new File("C:\\Users\\Sebastian\\Documents\\NetBeansProjects\\Champions_League_by_Seb\\src\\folder\\winning_sound.wav"));
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void klhrwshSound() {
        InputStream in;
        try {
            String dir = System.getProperty("user.dir");
            //File f = new File(getClass().getResource("/folder/klhrwsh_sound.wav").getFile());
            //in = new FileInputStream(f);
            in = getClass().getResourceAsStream("/folder/klhrwsh_sound.wav");
            //in = new FileInputStream(new File("C:\\Users\\Sebastian\\Documents\\NetBeansProjects\\Champions_League_by_Seb\\src\\folder\\klhrwsh_sound.wav"));
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void errorSound() {
        InputStream in;
        try {
            String dir = System.getProperty("user.dir");
            //File f = new File(getClass().getResource("/folder/windows_xp_error_sound.wav").getFile());
            //in = new FileInputStream(f);
            in = getClass().getResourceAsStream("/folder/windows_xp_error_sound.wav");
            //in = new FileInputStream(new File("/folder/windows_xp_error_sound.wav"));
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
