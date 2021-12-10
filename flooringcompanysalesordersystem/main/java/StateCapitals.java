
import java.util.HashMap;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivaylomaslev
 */
public class StateCapitals {

    public static void main(String[] args) {
        HashMap<String, Capitol> stateCapitols = new HashMap<>();

        /*HashMap<String, String> capitolCiti = new HashMap<>();*/
        Capitol c1 = new Capitol(); /*Student Quizes sq1 = new arrayList + initialize*/

        c1.setName("Montgomery");

        c1.setPopulation(2050000);

        c1.setSquaremile(156);

        stateCapitols.put("Alabama", c1);/*studentQuizGrades.put("Ivo Masle", sq1)*/

        Capitol c2 = new Capitol();

        c2.setName("Juneau");

        c2.setPopulation(31000);

        c2.setSquaremile(3255);

        stateCapitols.put("Alaska", c2);
        
         Capitol c3 = new Capitol();

        c3.setName("Phoenix");

        c3.setPopulation(1445000);

        c3.setSquaremile(517);

        stateCapitols.put("Arizona", c3);
        
         Capitol c4 = new Capitol();

        c4.setName("Arkansas");

        c4.setPopulation(193000);

        c4.setSquaremile(116);

        stateCapitols.put("Little Rock", c4);
        

        /* populations.put("Alaska - Juneau", 3100);

        populations.put("Arizona - Phoenix", 1445000);

        populations.put("Arkansas - Little Rock", 193000);*/
 /* STATE/CAPITAL PAIRS:
====================
Alabama - Montgomery | Pop: 205000 | Area: 156 sq mi
Alaska - Juneau | Pop: 31000 | Area: 3255 sq mi
Arizona - Phoenix | 1445000 | Area: 517 sq mi
Arkansas - Little Rock | Pop: 193000 | Area: 116 sq mi*/
        Set<String> keys = stateCapitols.keySet();
        for (String k : keys) {
            /*sate capitol get K one time*/
            System.out.println(k + " - " + stateCapitols.get(k).getName() + " | Pop: " + stateCapitols.get(k).getPopulation() + " | Area: " + stateCapitols.get(k).getSquaremile() + " sq mi ");
            /* System.out.println();*/
        }

    }

}
