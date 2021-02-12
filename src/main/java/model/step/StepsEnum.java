package model.step;

import utils.*;


public enum StepsEnum implements Steps {
    /** 
     * Mandatory CLEANING step (pulizia).
     */
    CLEANING,
    /**
     * Mandatory CLEANSING step (detersione).
     */
    CLEANSING,
    /**
     * Mandatory DISINFECTION step (disinfezione). 
     */
    DISINFECTION,
    /**
     * Optional RINSING step (risciacquo).
     */
    RINSING,
    /**
     * Optional DISINFESTATION step (disinfestazione).
     */
    DISINFESTATION,
    /**
     * Mandatory CONCLUSION step (processo concluso).
     */
    CONCLUSION;

    @Override
    public boolean isEnd() {
        return equals(StepsEnum.CONCLUSION);
    }

    @Override
    public String getName() {
        return StringUtils.underscoreWithWords(this.name());
    }

    public String toString() {
        return this.getName();
    }
 

}
