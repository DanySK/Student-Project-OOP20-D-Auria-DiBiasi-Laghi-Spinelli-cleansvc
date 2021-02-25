package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;
import model.users.Clients;
import model.users.ClientsImpl;
import utility.ConstantsCleanSvc;

public class TestProcessSubSteps {
    private static ProcessImpl process = ProcessImpl.getInstance();
    private static final String CODE = "1S";
    private static final int TIME = 20;
    private static final String NAME = "Disinfezione Classica";
    private static final String DESCRIPTION = "Non c'è utilizzo di ozono";
    private static final StepType STEP = process.getStepTypeList().get(4);
    private static final int TIME2 = 120;
    private static final int COST = 170;
    private SubSteps subStep = null;

    @Before
    public void initSubStep() {
        this.subStep = new SubStepsImpl(CODE, TIME, NAME, DESCRIPTION, STEP);
    }

    @Test
    public void testSubStep() {
            assertTrue(subStep.getCode().equals(CODE));
            assertTrue(Integer.compare(subStep.getTime(), TIME) == 0);
            assertTrue(subStep.getName().equals(NAME));
            assertTrue(subStep.getDescription().equals(DESCRIPTION));
            assertTrue(subStep.getStepType().equals(STEP));
    }

    @Test
    public void testProcess() {
            assertTrue(process.getSubStepsList().isEmpty());
            process.addStep(subStep);
            assertFalse(process.getSubStepsList().isEmpty());
            assertTrue(process.getSubStepsList().get(0).equals(process.searchSubStep(CODE).get()));
            assertTrue(process.getSubStepsList().get(0).equals(process.getSubStepsByStepType(STEP.getType()).get().get(0)));
            process.removeStep(subStep);
            assertTrue(process.getSubStepsList().isEmpty());
    }

    @Test
    public void testStepTypes() {
            assertTrue(process.getStepTypeList().get(0).getType().equals("CLEANING"));
            assertTrue(process.getStepTypeList().get(1).getType().equals("CLEANSING"));
            assertTrue(process.getStepTypeList().get(2).getType().equals("DISINFECTION"));
            assertTrue(process.getStepTypeList().get(3).getType().equals("DISINFESTATION"));
            assertTrue(process.getStepTypeList().get(4).getType().equals("CONCLUSION"));
    }

    @Test
    public void testMathOperation() {
        final Clients c = new ClientsImpl("LGHRRA97E69H199X", "Aurora", "Via Fratelli bandiera, 7", "Forlimpopoli", 47034, "0543743592", "aurora.laghi@studio.unibo.it", 500);
        double time = TIME2;
        double cost = COST;
        double totCost = Math.floor(cost * ConstantsCleanSvc.INCOME_INCRISE * ConstantsCleanSvc.INCOME_TAX);
        int nStaff = 1;
        assertTrue(Double.compare(process.getProportialTime(time, c, nStaff), time) == 0);
        assertTrue(Double.compare(process.getProportialCost(cost, c), cost) == 0);
        assertTrue(Double.compare(process.getIncome(cost), totCost) == 0);
    }
}
