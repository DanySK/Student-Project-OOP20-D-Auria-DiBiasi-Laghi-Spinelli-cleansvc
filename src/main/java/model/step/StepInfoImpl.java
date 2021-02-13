package model.step;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Nullable;


class StepInfoImpl implements StepInfo {

    private final Steps step;
    private final Date start;

    @Nullable
    private Date end;

   StepInfoImpl(final Steps step, final Date start) {
        this.step = step;
        this.start = start;
    }

   StepInfoImpl(final StepInfo stepInfo) {
       this(stepInfo.getType(), stepInfo.getStartDate());
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

    public final String toString() {
        return "[StepInfoImpl] {"
                + "step=" + step
                + "statTime=" + start
                + "endTime" + end
                + "}";
    }

    /**
     * Sets internal endDate.
     * @param end
     */

    void setInternalEndDate(final Date end) {
        this.end = new Date(end.getTime());
    }

}
