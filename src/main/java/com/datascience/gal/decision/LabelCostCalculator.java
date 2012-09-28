package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.utils.CostMatrix;

public abstract class LabelCostCalculator {
	
	abstract public Double calculateLabelCost(String calcLabel, Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix);
	
	public static class ProbabilityWeighted extends LabelCostCalculator{

		@Override
		public Double calculateLabelCost(String calcLabel, Map<String, Double> labelProbabilities,
				CostMatrix<String> costMatrix) {
			double sum = 0.;
			for (Map.Entry<String, Double> me: costMatrix.getDefinedCostsForValue(calcLabel).entrySet()){
				String label = me.getKey();
				double cost = me.getValue();
				double prob = labelProbabilities.get(label);
				sum += cost * prob;
			}
			return sum;
		}
	}
}
