/*
 Copyright (C) 2007 Richard Gomes

 This file is part of JQuantLib, a free-software/open-source library
 for financial quantitative analysts and developers - http://jquantlib.org/

 JQuantLib is free software: you can redistribute it and/or modify it
 under the terms of the QuantLib license.  You should have received a
 copy of the license along with this program; if not, please email
 <jquant-devel@lists.sourceforge.net>. The license is also available online at
 <http://www.jquantlib.org/index.php/LICENSE.TXT>.

 This program is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE.  See the license for more details.
 
 JQuantLib is based on QuantLib. http://quantlib.org/
 When applicable, the original copyright notice follows this notice.
 */

/*
 Copyright (C) 2003 Ferdinando Ametrano
 Copyright (C) 2001, 2002, 2003 Sadruddin Rejeb
 Copyright (C) 2006 StatPro Italia srl

 This file is part of QuantLib, a free-software/open-source library
 for financial quantitative analysts and developers - http://quantlib.org/

 QuantLib is free software: you can redistribute it and/or modify it
 under the terms of the QuantLib license.  You should have received a
 copy of the license along with this program; if not, please email
 <quantlib-dev@lists.sf.net>. The license is also available online at
 <http://quantlib.org/license.shtml>.

 This program is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE.  See the license for more details.
*/

package org.jquantlib.exercise;

/**
 * Early exercise base class
 * 
 * @author Richard Gomes
 */
public abstract class EarlyExercise extends Exercise {

	private boolean payoffAtExpiry;
	
	protected EarlyExercise(Exercise.Type type) {
		this(type, false);
	}

	protected EarlyExercise(Exercise.Type type, boolean payoffAtExpiry) {
		super(type);
		this.payoffAtExpiry = payoffAtExpiry;
	}

	/**
	 * Returns the payoff at expiry
	 * 
	 * @return the payoff at expiry
	 */
	protected boolean getPayoffAtExpiry() {
		return this.payoffAtExpiry;
	}
	
	/**
	 * This method is only used by extended classes on the very special cases 
	 * when the payoff at expiry must be changed.
	 * 
	 * @param payoffAtExpiry is the payoff at expiry
	 */
	protected void setPayoffAtExpiry(boolean payoffAtExpiry) {
		this.payoffAtExpiry = payoffAtExpiry;
	}

}
