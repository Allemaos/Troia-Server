package com.datascience.utils;

public class ProbabilityWithElement<T> implements
        Comparable<ProbabilityWithElement<T>> {
    public double probability;
    public T element;

    public ProbabilityWithElement(double probability, T element) {
        this.probability = probability;
        this.element = element;
    }

    @Override
    public int compareTo(ProbabilityWithElement<T> other) {
        return Double.compare(probability, other.probability);
    }
}
