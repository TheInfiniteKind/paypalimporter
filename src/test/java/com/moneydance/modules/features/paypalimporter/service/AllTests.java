// PayPal Importer for Moneydance - http://my-flow.github.io/paypalimporter/
// Copyright (C) 2013-2014 Florian J. Breunig. All rights reserved.

package com.moneydance.modules.features.paypalimporter.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Florian J. Breunig
 */
@RunWith(Suite.class)
@SuiteClasses({
    CheckCurrencyServiceTest.class,
    ServiceProviderTest.class,
    ServiceResultTest.class,
    TransactionSearchServiceTest.class })
public final class AllTests {
    // no test cases
}
