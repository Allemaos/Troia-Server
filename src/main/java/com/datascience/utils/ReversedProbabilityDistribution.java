package com.datascience.utils;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Konrad Kurdej
 * 
 * @param <E>
 */
public class ReversedProbabilityDistribution<E> extends
        AbstractCollection<ProbabilityWithElement<E>> {

    private SortedSet<ProbabilityWithElement<E>> elements;

    public ReversedProbabilityDistribution() {
        super();
        this.elements = new TreeSet<ProbabilityWithElement<E>>();
    }

    @SuppressWarnings("unchecked")
    public ReversedProbabilityDistribution(Collection<Double> probabiliteis,
            Collection<E> elements) {
        this(probabiliteis.toArray(new Double[0]), (E[]) elements.toArray());
    }

    public ReversedProbabilityDistribution(Double[] probabiliteis, E[] elements) {
        this();
        assert probabiliteis.length == elements.length;
        for (int i = 0; i < elements.length; i++) {
            ProbabilityWithElement<E> pwe = new ProbabilityWithElement<E>(
                    probabiliteis[i], elements[i]);
            add(pwe);
        }
    }

    public ReversedProbabilityDistribution(
            Collection<ProbabilityWithElement<E>> elements) {
        super();
    }

    @Override
    public boolean add(ProbabilityWithElement<E> e) {
        return elements.add(e);
    }

    @Override
    public boolean remove(Object e) {
        return elements.remove(e);
    }
    
    @Override
    public Iterator<ProbabilityWithElement<E>> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }
}
