/*
 Copyright (C) 2007 Richard Gomes

 This file is part of JQuantLib, a free-software/open-source library
 for financial quantitative analysts and developers - http://jquantlib.org/

 JQuantLib is free software: you can redistribute it and/or modify it
 under the terms of the QuantLib license.  You should have received a
 copy of the license along with this program; if not, please email
 <jquantlib-dev@lists.sf.net>. The license is also available online at
 <http://jquantlib.org/license.shtml>.

 This program is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE.  See the license for more details.
 
 JQuantLib is based on QuantLib. http://quantlib.org/
 When applicable, the originating copyright notice follows below.
 */

package org.jquantlib.exercise;

import java.util.Arrays;
import java.util.List;

import javolution.util.FastTable;

import org.jquantlib.util.Date;

/*! A Bermudan option can only be exercised at a set of fixed dates.

\todo it would be nice to have a way for making a Bermudan with
      one exercise date equivalent to an European
*/


/**
 * A Bermudan option can only be exercised at a set of fixed dates.
 * 
 * @author Richard Gomes
 */
public class BermudanExercise extends EarlyExercise {

	/**
	 * Constructs a BermudanExercise with a list of exercise dates and the default payoff
	 * 
	 * <p><b>Note:</b> In the very special case when the list of dates contains only one date, the BermudanExercise behaves
	 * like an EuropeanExercise.
	 * 
	 * <p><b>Note:</b>When there's a single expiry date, this constructor assumes that <i>there will be no payoff at expiry date</i>.
	 * If this is not the desired behavior, use {@link BermudanExercise#BermudanExercise(List, boolean)} instead.
	 * 
	 * @param dates is a list of exercise dates. If the list contains only one date, a BermundanExercise behaves like an EuropeanExercise.
	 * @throws IllegalArgumentException if the list is null or empty
	 * 
	 * @see EuropeanExercise
	 * @see BermudanExercise#BermudanExercise(List, boolean)
	 */
	public BermudanExercise(final Date[] dates) {
		this(dates, false);
	}

	/**
	 * This is a convenience constructor equivalent to {@link BermudanExercise#BermudanExercise(Date[])}
	 * @param dates is a list of exercise dates. If the list contains only one date, a BermundanExercise behaves like an EuropeanExercise.
	 */
	public BermudanExercise(final FastTable<Date> dates) {
		this(dates.toArray(new Date[0]), false);
	}
	
	
	
	
	/**
	 * Constructs a BermudanExercise with a list of exercise dates and the default payoff
	 * 
	 * <p><b>Note:</b> In the very special case when the list of dates contains only one date, the BermudanExercise behaves
	 * like an EuropeanExercise.
	 * 
	 * @param dates is a list of exercise dates. If the list contains only one date, a BermundanExercise behaves like an EuropeanExercise.
	 * @param payoffAtExpiry is <code>true</code> if payoffs are expected to happen on exercise dates
	 * @throws IllegalArgumentException if the list is null or empty
	 * 
	 * @see EuropeanExercise
	 */
	public BermudanExercise(final FastTable<Date> dates, boolean payoffAtExpiry) {
		super(Exercise.Type.Bermudan, payoffAtExpiry);
		if (dates==null) throw new NullPointerException();
		if (dates.size()==0) throw new IllegalArgumentException("exercise dates is empty");
		if (dates.size()==1) {
			super.setType(Exercise.Type.European);
			super.setPayoffAtExpiry(false);
		}
		addAll(0, dates);
	}

	/**
	 * This is a convenience constructor equivalent to {@link BermudanExercise#BermudanExercise(Date[], boolean)}
	 * @param dates is a list of exercise dates. If the list contains only one date, a BermundanExercise behaves like an EuropeanExercise.
	 * @param payoffAtExpiry
	 */
	public BermudanExercise(final Date[] dates, boolean payoffAtExpiry) {
		super(Exercise.Type.Bermudan, payoffAtExpiry);
		if (dates==null) throw new NullPointerException();
		if (dates.length==0) throw new IllegalArgumentException("exercise dates is empty");
		if (dates.length==1) {
			super.setType(Exercise.Type.European);
			super.setPayoffAtExpiry(false);
		}
		addAll(0, new FastTable(Arrays.asList(dates)));
	}

}
