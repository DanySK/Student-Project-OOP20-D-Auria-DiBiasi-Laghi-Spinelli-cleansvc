package test;

import org.junit.jupiter.api.Test;

import model.TimeToCleanValuation;


public class TestTimeToClean {

        @Test
        public void timeToTest() {
            System.out.println("Tempo stimato: " + new TimeToCleanValuation(1,2,30).coreValuation()+ " min");
        }
    }

