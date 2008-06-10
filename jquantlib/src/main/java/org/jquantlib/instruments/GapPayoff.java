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

package org.jquantlib.instruments;

import org.jquantlib.util.TypedVisitor;
import org.jquantlib.util.Visitor;




/**
 * Binary cash-or-nothing payoff
 */
public class GapPayoff extends StrikedTypePayoff {
	
	protected /*@Price*/ double secondStrike;
	
	public GapPayoff(final Option.Type type, final /*@Price*/ double strike) {
		super(type, strike);
	}
	
	public /*@Price*/ double getSecondStrike() /* @ReadOnly */ {
		return secondStrike;
	}

	public final /*@Price*/ double valueOf(final /*@Price*/ double price) {
    	if (type==Option.Type.CALL) {
    		return (price-strike >= 0.0 ? price-secondStrike : 0.0);
    	} else if (type==Option.Type.PUT) {
    		return (strike-price >= 0.0 ? secondStrike-price : 0.0);
    	} else {
    		throw new IllegalArgumentException(UNKNOWN_OPTION_TYPE);
    	}
    }


	//
	// implements TypedVisitable
	//
	
	@Override
	public void accept(final TypedVisitor<Payoff> v) {
		Visitor<Payoff> v1 = (v!=null) ? v.getVisitor(this.getClass()) : null;
		if (v1 != null) {
			v1.visit(this);
		} else {
			super.accept(v);
		}
	}

}
