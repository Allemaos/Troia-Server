package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.utils.CostMatrix;

/**
* @author Konrad Kurdej
*/
public class ExpectedCostDecisionAlgorithm extends SampleLabelDecisionAlgorithm{

	@Override
	public String predictLabel(Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix) {
		String minCostLabel = null;
		double minCostLabelCost = Double.MAX_VALUE;
		for (String label: labelProbabilities.keySet()){
			double cost = calculateLabelCost(label, labelProbabilities, costMatrix);
			if (cost < minCostLabelCost){
				minCostLabel = label;
				minCostLabelCost = cost;
			}
		}
		return minCostLabel;
	}

	@Override
	public Double predictedLabelCost(Map<String, Double> labelProbabilities,
			CostMatrix<String> costMatrix) {
		String predictedLabel = predictLabel(labelProbabilities, costMatrix);
		return calculateLabelCost(predictedLabel, labelProbabilities, costMatrix);
	}

}
