package com.datascience.core;

import com.datascience.gal.Category;
import com.datascience.gal.Datum;
import com.datascience.utils.ProbabilityDistribution;
import com.datascience.utils.ProbabilityWithElement;

public abstract class ClassificationMethod {

    public abstract Category getCorrectCategory(Datum datum);

    public abstract ProbabilityWithElement<Category> getCorrectCategoryWithProbability(
            Datum datum);

    public abstract ProbabilityDistribution<Category> getCategoriesDistribution(
            Datum datum);
}
