package com.datascience.gal.quality;

import com.datascience.gal.DawidSkene;

public interface DataQualityEstimationAlgorithm {
    
//    public int getID();
    
    public double estimateQuality(DawidSkene ds);

}
