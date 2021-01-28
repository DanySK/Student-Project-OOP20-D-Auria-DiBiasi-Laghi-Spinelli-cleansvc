package Test;


import org.junit.jupiter.api.Test;

import model.TimeToCleanValuation;


public class TestTimeToClean {
       
        @Test
        public void timeToTest(){
            System.out.println("Tempo stimato: " + new TimeToCleanValuation(1,2,300).washValuation()+ " min");
            System.out.println("Tempo stimato: " + new TimeToCleanValuation(1,2,300).disinfectionValuation()+ " min");
            System.out.println("Tempo stimato: " + new TimeToCleanValuation(1,2,300).flushingValuation()+ " min");

        }
        
        @Test
        public void completeTest() {
            System.out.println("Tempo per sanificazione completa " + new TimeToCleanValuation(3,2,300).completeSet() + " minuti");
        }
    }

