package com.datascience.utils;

import java.util.HashMap;

@SuppressWarnings("serial")
public class HashableObjectsProbabilityDistribution<T> extends
        HashMap<T, Double> implements ProbabilityDistribution<T> {
    
    @Override
    public Double get(Object key){
        Double ret = super.get(key);
        if (ret != null)
            return ret;
        return 0.;
    }
}
