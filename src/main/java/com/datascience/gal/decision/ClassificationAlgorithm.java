package com.datascience.gal.decision;

import java.util.Map;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Datum;
import com.datascience.utils.CostMatrix;

public class ClassificationAlgorithm {

	private LabelProbabilityDistributionCalculator lpdc;
	private LabelCostCalculator lcc;
	private LabelClassificationAlgorithm lca;
	
	public ClassificationAlgorithm(LabelProbabilityDistributionCalculator lpdc, LabelCostCalculator lcc,
			LabelClassificationAlgorithm lca){
		this.lpdc = lpdc;
		this.lcc = lcc;
		this.lca = lca;		
	}
	
	public String predictLabel(Datum datum, AbstractDawidSkene ads){
		
	}
	
	public Double predictedLabelCost(Datum datum, AbstractDawidSkene ads){
		String plabel = predictLabel(datum, ads);
		Map<String, Double> labelProbabilities = getLabelProbabilities(datum, ads);
		CostMatrix<String> costMatrix = getCostMatrix(ads);
		return lcc.calculateLabelCost(plabel, labelProbabilities, costMatrix);
	}
	
	private Map<String, Double> getLabelProbabilities(Datum datum, AbstractDawidSkene ads){
		
	}
	
	private CostMatrix<String> getCostMatrix(AbstractDawidSkene ads){
		
	}
}
