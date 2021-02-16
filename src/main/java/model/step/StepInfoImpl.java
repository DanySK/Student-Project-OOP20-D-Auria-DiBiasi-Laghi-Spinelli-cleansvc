package model.step;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Nullable;

/**
 * Implementation of StepInfo using pattern decorator for constructor.
 *  This package is private.
 */


class StepInfoImpl implements StepInfo extends StepsEnum {

    private final Steps step;
    private final Date start;

    @Nullable
    private Date end;

   StepInfoImpl(final Steps step, final Date start) {
        this.step = step;
        this.start = new Date(start.getTime());
    }

   /**
    * Decorator constructor copying the data passed by parameter.
    * @param stepInfo, it is a copy.
    */

   StepInfoImpl(final StepInfo stepInfo) {
       this(stepInfo.getName(), stepInfo.getStartDate());
       stepInfo.getEndDate().ifPresent(this::setInternalEndDate);
   }

    @Override
    public Steps getType() {
        return this.step;
    }

    @Override
    public Date getStartDate() {
        return new Date(this.start.getTime());
    }

    @Override
    public Optional<Date> getEndDate() {
        return Optional.ofNullable(this.end).map(e -> new Date(e.getTime()));
    }

    @Override
    public final String toString() {
        return "[StepInfoImpl] {"
                + "step=" + step
                + "statTime=" + start
                + "endTime" + end
                + '}';
    }

    /**
     * Sets internal endDate.
     * @param endDate date to set as end date.
     */

    void setInternalEndDate(final Date end) {
        this.end = new Date(end.getTime());
    }

 

}
