package com.datascience.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * I think this is safe to assume that if object is not in ProbabilityDistribution than we should return 0.
 * @author Konrad Kurdej
 *
 * @param <T>
 */
public interface ProbabilityDistribution<T> extends Map<T, Double>{
}
