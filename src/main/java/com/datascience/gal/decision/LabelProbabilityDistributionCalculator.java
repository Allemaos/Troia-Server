package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Datum;

public interface LabelProbabilityDistributionCalculator {
	
    public Map<String, Double> calculateDistribution(Datum datum, AbstractDawidSkene ads);

}
