/*
Copyright (C) 2008 Praneet Tiwari

This source code is release under the BSD License.

This file is part of JQuantLib, a free-software/open-source library
for financial quantitative analysts and developers - http://jquantlib.org/

JQuantLib is free software: you can redistribute it and/or modify it
under the terms of the JQuantLib license.  You should have received a
copy of the license along with this program; if not, please email
<jquant-devel@lists.sourceforge.net>. The license is also available online at
<http://www.jquantlib.org/index.php/LICENSE.TXT>.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE.  See the license for more details.

JQuantLib is based on QuantLib. http://quantlib.org/
When applicable, the original copyright notice follows this notice.
 */
package org.jquantlib.model;

//reviewed once uh.

import org.jquantlib.math.Array;
import org.jquantlib.math.optimization.Constraint;

/**
 * 
 * @author Praneet Tiwari
 */
public class ConstantParameter extends Parameter {

    private static class Impl extends Parameter.Impl {

        @Override
        public double /* @Real */value(final Array params, double /* @Time */t) {
            return params.get(0);
        }
    }

    public ConstantParameter(final Constraint constraint) {
        super(1, new ConstantParameter.Impl(), constraint);
    }

    public ConstantParameter(double /* @Real */value, final Constraint constraint) {
        super(1, new ConstantParameter.Impl(), constraint);
        params.set(0, value);
        if (!testParams(params)) {
            throw new IllegalArgumentException(value + ": invalid value");
        }

    }
}