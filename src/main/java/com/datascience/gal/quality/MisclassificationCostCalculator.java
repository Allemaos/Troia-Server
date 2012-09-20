package com.datascience.gal.quality;

public interface MisclassificationCostCalculator {
    
    /*
     * TODO add docs
     */
    public double expectedCost();
    
    /*
     * TODO add docs
     */
    public double minimalizedCost();
    
    /*
     * TODO add docs
     */
    public double softCost();

}
