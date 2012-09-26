package com.datascience.gal.quality;

import java.util.HashMap;
import java.util.Map;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Datum;
import com.datascience.gal.DawidSkene;

public class DataQualityEstimator {
    // TODO: make this single object in system - probably with singleton pattern but there might be better option

    private static Map<String, MisclassificationCostCalculator> MISSCLASSIFICATION_COST_CALCULATORS =
            new HashMap<String, MisclassificationCostCalculator>();
    static {
        MISSCLASSIFICATION_COST_CALCULATORS.put("ExpectedCost", new MisclassificationCostCalculators.DSExpected());
        MISSCLASSIFICATION_COST_CALCULATORS.put("ExpectedMVCost", new MisclassificationCostCalculators.MVExpected());
        MISSCLASSIFICATION_COST_CALCULATORS.put("MinCost", new MisclassificationCostCalculators.DSMinimalized());
        MISSCLASSIFICATION_COST_CALCULATORS.put("MinMVCost", new MisclassificationCostCalculators.MVMinimalized());
    }

    public double estimateMissclassificationCost(DawidSkene ds, String method,
            String object_id) {
        // Ugly as hell but I don't see any other way ...
        AbstractDawidSkene ads = (AbstractDawidSkene) ds;
        Datum datum = ads.getObject(object_id);
        return MISSCLASSIFICATION_COST_CALCULATORS.get(method).objectMisclassificationCost(datum);
    }
}
