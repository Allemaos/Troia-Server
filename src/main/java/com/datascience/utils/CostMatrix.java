package com.datascience.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * T must be hashable
 *
 * @author Konrad Kurdej
 *
 * @param <T>
 */
public class CostMatrix<T> {

	private Map<T, Map<T, Double>> costMatrix;

	public CostMatrix() {
		costMatrix = new HashMap<T, Map<T, Double>>();
	}

	public void add(T trueValue, T predictedValue, Double cost) {
		Map<T, Double> entryMap = costMatrix.get(trueValue);
		if (entryMap == null) {
			entryMap = new HashMap<T, Double>();
			costMatrix.put(trueValue, entryMap);
		}
		entryMap.put(predictedValue, cost);
	}

	public Double getCost(T trueValue, T predictedValue) {
		// I won't check if this "request" is correct - it should fail if not
		return costMatrix.get(trueValue).get(predictedValue);
	}

	public Map<T, Double> getDefinedCostsForValue(T trueValue) {
		return new HashMap<T, Double>(costMatrix.get(trueValue));
	}

}
