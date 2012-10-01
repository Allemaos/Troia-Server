package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.utils.CostMatrix;

/**
 * @author Konrad Kurdej
 */
public abstract class ObjectLabelDecisionAlgorithm {

	abstract public String predictLabel(Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix);

	abstract public Double predictedLabelCost(Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix);
	
	
	protected Double calculateLabelCost(String calcLabel, Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix){
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
