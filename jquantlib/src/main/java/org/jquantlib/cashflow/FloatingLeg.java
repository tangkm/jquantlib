package org.jquantlib.cashflow;

import org.jquantlib.QL;
import org.jquantlib.daycounters.DayCounter;
import org.jquantlib.indexes.IborIndex;
import org.jquantlib.indexes.InterestRateIndex;
import org.jquantlib.lang.exceptions.LibraryException;
import org.jquantlib.lang.reflect.TypeTokenTree;
import org.jquantlib.math.matrixutilities.Array;
import org.jquantlib.time.BusinessDayConvention;
import org.jquantlib.time.Calendar;
import org.jquantlib.time.Date;
import org.jquantlib.time.Schedule;

// TODO: code review :: license, class comments, comments for access modifiers, comments for @Override
// TODO: code review :: Please complete this class and perform another code review.
public class FloatingLeg<InterestRateIndexType extends InterestRateIndex, FloatingCouponType extends FloatingRateCoupon, CappedFlooredCouponType>
		extends Leg {

	public FloatingLeg(final Array nominals, final Schedule schedule,
			final InterestRateIndexType index,
			final DayCounter paymentDayCounter,
			final BusinessDayConvention paymentAdj, final Array fixingDays,
			final Array gearings, final Array spreads, final Array caps,
			final Array floors, final boolean isInArrears, final boolean isZero) {
		super(schedule.size() - 1);

		if (System.getProperty("EXPERIMENTAL") == null) {
			throw new UnsupportedOperationException("Work in progress");
		}

		final int n = schedule.size() - 1;
		QL.require(nominals.size() <= n, "too many nominals"); // QA:[RG]::verified
																// // TODO:
																// message
		QL.require(spreads.size() <= n, "too many spreads"); // QA:[RG]::verified
																// // TODO:
																// message
		QL.require(gearings.size() <= n, "too many gearings"); // QA:[RG]::verified
																// // TODO:
																// message
		QL.require(caps.size() <= n, "too many caps"); // QA:[RG]::verified //
														// TODO: message
		QL.require(floors.size() <= n, "too many floors"); // QA:[RG]::verified
															// // TODO: message
		QL.require(!isZero || !isInArrears,
				"features in-arrears and zero are not compatible"); // QA:[RG]::verified
																	// // TODO:
																	// message

		// the following is not always correct (orignial c++ comment)
		final Calendar calendar = schedule.calendar();

		// FIXME: constructor for uninitialized date available ?
		Date refStart, start, refEnd, end;
		Date paymentDate;

		for (int i = 0; i < n; i++) {
			refStart = start = schedule.date(i);
			refEnd = end = schedule.date(i + 1);
			paymentDate = calendar.adjust(end, paymentAdj);

			if (i == 0 && !schedule.isRegular(i + 1)) {
				refStart = calendar.adjust(end.sub(schedule.tenor()),
						paymentAdj);
			}
			if (i == n - 1 && !schedule.isRegular(i + 1)) {
				refEnd = calendar.adjust(start.add(schedule.tenor()),
						paymentAdj);
			}
			if (Detail.get(gearings, i, 1.0) == 0.0) {
				add(new FixedRateCoupon(Detail
						.get(nominals, i, new Double(1.0)), paymentDate, Detail
						.effectiveFixedRate(spreads, caps, floors, i),
						paymentDayCounter, start, end, refStart, refEnd));
			}
			else if (Detail.noOption(caps, floors, i)) {

				// get the generic type
				final Class<?> fctklass = new TypeTokenTree(this.getClass())
						.getRoot().get(1).getElement();

				// construct a new instance using reflection. first get the
				// constructor ...
				FloatingCouponType frc;
				try {
					frc = (FloatingCouponType) fctklass.getConstructor(
							Date.class, // paymentdate
							double.class, // nominal
							Date.class, // start date
							Date.class, // enddate
							int.class, // fixing days
							index.getClass(), // ii
							double.class, // gearing
							double.class, // spread
							Date.class, // refperiodstart
							Date.class, // refperiodend
							DayCounter.class,// daycounter
							boolean.class) // inarrears
							// then create a new instance
							.newInstance(
									paymentDate,
									Detail.get(nominals, i, (double) 1.0),
									start,
									end,
									(int) Detail.get(fixingDays, i, index
											.fixingDays()), index,
									Detail.get(gearings, i, 1.0),
									Detail.get(spreads, i, 0.0), refStart,
									refEnd, paymentDayCounter, isInArrears);
				} catch (final Exception e) {
					throw new LibraryException(
							"Couldn't construct new instance from generic type"); // QA:[RG]::verified
																					// //
																					// TODO:
																					// message
				}

				// append coupon cashflow
				add((CashFlow) frc);
			}
			else {
				final Class<?> cfcklass = new TypeTokenTree(this.getClass())
						.getRoot().get(2).getElement();
				CappedFlooredCouponType cfctc;
				try {
/*
					CappedFlooredIborCoupon cpn = new CappedFlooredIborCoupon(
							paymentDate, 
							Detail.get(nominals,i, (double) 1.0),
							start, 
							end,
							(int) Detail.get(fixingDays, i, (int) index.fixingDays()), 
							(IborIndex) index, 
							Detail.get(gearings, i, (double) 1.0),
							Detail.get(spreads, i, (double) 0.0),
							Detail.get(caps, i, Double.MAX_VALUE),
							Detail.get(floors, i, Double.MIN_VALUE),
							refStart,
							refEnd,
							paymentDayCounter,
							isInArrears);
*/
					// FIXME: not finished yet!!!!!!!!!!!!!!
					cfctc = (CappedFlooredCouponType) cfcklass.getConstructor(
							Date.class, // paymentdate
							double.class, // nominal
							Date.class, // start date
							Date.class, // enddate
							int.class, // fixing days
							index.getClass(), // ii
							double.class, // gearing
							double.class, // spread
							double.class, //caps
							double.class, //floors
							Date.class, // refperiodstart
							Date.class, // refperiodend
							DayCounter.class,// daycounter
							boolean.class) // inarrears
							// then create a new instance
							.newInstance (
									paymentDate, 
									Detail.get(nominals,i, (double) 1.0),
									start, 
									end,
									(int) Detail.get(fixingDays, i, (int) index.fixingDays()), 
									index, 
									Detail.get(gearings, i, (double) 1.0),
									Detail.get(spreads, i, (double) 0.0),
									Detail.get(caps, i, Double.MAX_VALUE),
									Detail.get(floors, i, Double.MIN_VALUE),
									refStart,
									refEnd,
									paymentDayCounter,
									isInArrears);									
							
							
							
				} catch (final Exception e) {
					throw new LibraryException(
							"Couldn't construct new instance from generic type"); // QA:[RG]::verified
																					// //
																					// TODO:
																					// message
				}
				add((CashFlow) cfctc);
			}
		}
	}
}
