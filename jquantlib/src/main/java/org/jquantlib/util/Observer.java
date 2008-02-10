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

package org.jquantlib.util;

/**
 * This interface is intended to provide more flexibility to complex object
 * models when multiple inheritance is needed.
 * 
 * <p>
 * This class is based on the work done by Martin Fischer, with only minor changes.
 * See references below.
 * 
 * @see <a
 *      href="http://www.jroller.com/martin_fischer/entry/a_generic_java_observer_pattern">Martin
 *      Fischer: Observer and Observable interfaces</a>
 * @see <a href="http://jdj.sys-con.com/read/35878.htm">Improved
 *      Observer/Observable</a>
 * @see Observable
 * 
 * @author Martin Fischer (original author)
 * @author Richard Gomes
 */
public interface Observer {

	/**
	 * This method is called whenever the observed object is changed.
	 * 
	 * @param o
	 * @param arg
	 */
	public void update(Observable o, Object arg);

}
