package com.datascience.gal.quality;

import java.util.Collection;

import com.datascience.gal.AbstractDawidSkene;
import com.datascience.gal.Category;
import com.datascience.gal.Datum;
import com.datascience.utils.ProbabilityWithElement;

public abstract class MisclassificationCostCalculators implements
        MisclassificationCostCalculator {

    @Override
    public abstract double objectMisclassificationCost(Datum datum, AbstractDawidSkene ads);

    @Override
    public double overallMisclassificationCost(AbstractDawidSkene ads) {
        double sum = 0.;
        for (Datum datum : ads.getObjects()) {
            sum += objectMisclassificationCost(datum, ads);
        }
        return sum;
    }

    public static abstract class OneCategoryBased extends
            MisclassificationCostCalculators {
        
        @Override
        public double objectMisclassificationCost(Datum datum, AbstractDawidSkene ads) {
            String categoryid = chooseRightCategory(datum);
            Category category = ads.getCategory(categoryid);
            category.getCost(to)
            return 

        }

        protected abstract String chooseRightCategory(Datum datum);
    }

    public static class DSExpected extends MisclassificationCostCalculators {

        @Override
        public double objectMisclassificationCost(Datum datum) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    public static class MVExpected extends MisclassificationCostCalculators {

        @Override
        public double objectMisclassificationCost(Datum datum) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    public static class DSMinimalized extends OneCategoryBased {

        @Override
        protected String chooseRightCategory(Datum datum) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    public static class MVMinimalized extends OneCategoryBased {

        @Override
        protected String chooseRightCategory(Datum datum) {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
