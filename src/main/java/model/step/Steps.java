package model.step;

import model.step.enumerations.StepType;

public interface Steps {

   double getTime500mq();

   double getPrice500mq();

   StepType getType();

   String description();


}
