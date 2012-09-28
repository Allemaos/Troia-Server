package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Datum;

public abstract class LabelProbabilityDistributionCalculator {
	
	abstract public Map<String, Double> calculateDistribution(Datum datum, AbstractDawidSkene abs);

	public static class DSLabelProbabilityDistributionCalculator extends LabelProbabilityDistributionCalculator{

		@Override
		public Map<String, Double> calculateDistribution(Datum datum,
				AbstractDawidSkene abs) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public static class MVLabelProbabilityDistributionCalculator extends LabelProbabilityDistributionCalculator{

		@Override
		public Map<String, Double> calculateDistribution(Datum datum,
				AbstractDawidSkene abs) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
