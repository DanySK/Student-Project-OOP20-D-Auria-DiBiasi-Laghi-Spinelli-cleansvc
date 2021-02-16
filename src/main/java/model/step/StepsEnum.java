package model.step;


import utils.StringUtils;

/**
 * Enum for describing the various steps that a sanitization has to go through production.
 */
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
     * Mandatory DISINFECTION step. 
     */
    DISINFECTION,
    /**
     * Optional RINSING step (risciacquo).
     */
    RINSING,
    /**
     * Optional DISINFESTATION step.
     */
    DISINFESTATION,
    /**
     * Mandatory CONCLUSION step.
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

    @Override
    public String toString() {
        return this.getName();
    }

}
