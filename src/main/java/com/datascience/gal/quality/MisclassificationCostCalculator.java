package com.datascience.gal.quality;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Datum;

public interface MisclassificationCostCalculator {
    
    public double objectMisclassificationCost(Datum datum, AbstractDawidSkene ads);
    
    public double overallMisclassificationCost(AbstractDawidSkene ads);

}
