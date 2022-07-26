/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsoulou_me_gui_2;
/**
 *
 * @author i3test
 */
public class OmadaNode {
    public String onoma;
    public OmadaNode parent=null;
    public OmadaNode left=null;
    public OmadaNode right=null;
    public int goal;
    
    /*Constructors*/
    public OmadaNode(){   
    }
    
    public OmadaNode(String onoma){
    this.onoma=onoma;
    }

    //////
   public  void display(){
        System.out.println(onoma);
    }
    
  
    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getGoal() {
        return goal;
    }

    public void setOnoma(String onoma) {
        this.onoma = onoma;
    }

    public String getOnoma() {
        return onoma;
    }
    
}
