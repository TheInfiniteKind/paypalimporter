// PayPal Importer for Moneydance - http://my-flow.github.io/paypalimporter/
// Copyright (C) 2013-2018 Florian J. Breunig. All rights reserved.

package com.moneydance.modules.features.paypalimporter.controller;

import com.infinitekind.moneydance.model.Account;
import com.infinitekind.moneydance.model.DateRange;
import com.infinitekind.moneydance.model.OnlineTxn;
import com.moneydance.apps.md.controller.StubAccountBookFactory;
import com.moneydance.apps.md.controller.StubContextFactory;
import com.moneydance.modules.features.paypalimporter.model.InputData;
import com.moneydance.modules.features.paypalimporter.util.Helper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;

/**
 * @author Florian J. Breunig
 */
public final class ViewControllerImplTest {

    private ViewController viewController;
    private Account account;

    @Before
    public void setUp() {
        StubContextFactory factory = new StubContextFactory();
        Helper.INSTANCE.setPreferences(
                new StubAccountBookFactory(
                        factory.getContext().getAccountBook()));
        this.account = factory.getContext().getRootAccount().getSubAccount(0);

        ViewControllerImpl viewControllerImpl = new ViewControllerImpl(
                factory.getContext(),
                Helper.INSTANCE.getTracker(0));
        final char[] password = {'s', 't', 'u', 'b', ' ',
                'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        InputData inputData = new InputData("", password, "",
                this.account.getAccountNum(), new DateRange());
        viewControllerImpl.setInputData(inputData);
        this.viewController = viewControllerImpl;
    }

    @Test
    public void testStartWizard() {
        this.viewController.startWizard();
    }

    @Test
    public void testCurrencyCheckedEmpty() {
        this.viewController.currencyChecked(
                this.account.getCurrencyType(),
                CurrencyCodeType.USD,
                Collections.<CurrencyCodeType>emptyList());
    }

    @Test
    public void testCurrencyCheckedFilled() {
        List<CurrencyCodeType> currencyCodes = new LinkedList<CurrencyCodeType>();
        currencyCodes.add(CurrencyCodeType.USD);
        currencyCodes.add(CurrencyCodeType.EUR);
        this.viewController.currencyChecked(
                this.account.getCurrencyType(),
                CurrencyCodeType.USD,
                currencyCodes);
    }

    @Test
    public void testTransactionsImportedEmptyWithErrorCode() {
        this.viewController.transactionsImported(
                Collections.<OnlineTxn>emptyList(),
                null,
                this.account,
                Helper.INSTANCE.getSettings().getErrorCodeSearchWarning());
    }
}
