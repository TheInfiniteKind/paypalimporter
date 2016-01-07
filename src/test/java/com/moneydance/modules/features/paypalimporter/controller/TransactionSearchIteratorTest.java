// PayPal Importer for Moneydance - http://my-flow.github.io/paypalimporter/
// Copyright (C) 2013-2016 Florian J. Breunig. All rights reserved.

package com.moneydance.modules.features.paypalimporter.controller;

import com.infinitekind.moneydance.model.AccountBook;
import com.infinitekind.moneydance.model.OnlineTxn;
import com.moneydance.apps.md.controller.StubContextFactory;
import com.moneydance.modules.features.paypalimporter.model.InputData;
import com.moneydance.modules.features.paypalimporter.presentation.WizardHandler;
import com.moneydance.modules.features.paypalimporter.service.ServiceProvider;
import com.moneydance.modules.features.paypalimporter.util.Helper;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;

/**
 * @author Florian J. Breunig
 */
public final class TransactionSearchIteratorTest {

    private TransactionSearchIterator iterator;
    private AccountBook accountBook;

    @Before
    public void setUp() throws Exception {
        StubContextFactory factory = new StubContextFactory();
        this.accountBook = factory.getContext().getCurrentAccountBook();

        final char[] password = {'s', 't', 'u', 'b', ' ',
                'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        final InputData inputData = new InputData(
                "mock username",
                password,
                "mock signature",
                -1);
        this.iterator = new TransactionSearchIterator(
                new ViewControllerMock(),
                factory.getContext().getAccountBook(),
                new ServiceProvider(),
                inputData,
                this.accountBook.getRootAccount().getCurrencyType(),
                CurrencyCodeType.USD);
    }

    @Test
    public void testCallTransactionSearchService() {
        this.iterator.callTransactionSearchService();
    }

    @Test
    public void testStartWizard() {
        this.iterator.startWizard();
    }

    @Test
    public void testCancel() {
        this.iterator.cancel();
    }

    @Test
    public void testProceed() {
        this.iterator.proceed();
    }

    @Test
    public void testUnlockNoSearchWarning() {
        this.iterator.unlock(
                "stub text",
                "stub key");
    }

    @Test
    public void testUnlockWithSearchWarning() {
        this.iterator.unlock(
                "stub text",
                Helper.INSTANCE.getSettings().getErrorCodeSearchWarning());
    }

    @Test
    public void testCurrencyChecked() {
        this.iterator.currencyChecked(null,  null,  null);
    }

    @Test
    public void testTransactionsImportedWithSearchWarning() {
        this.iterator.transactionsImported(
                Collections.<OnlineTxn>emptyList(),
                new Date(Math.abs(System.currentTimeMillis() - RandomUtils.nextLong())),
                null,
                Helper.INSTANCE.getSettings().getErrorCodeSearchWarning());
    }

    @Test
    public void testTransactionsImportedNoSearchWarningEmpty() {
        this.iterator.transactionsImported(
                Collections.<OnlineTxn>emptyList(),
                null,
                null,
                null);
    }

    @Test
    public void testTransactionsImportedNoSearchWarningFilled() {
        final List<OnlineTxn> onlineTxns = new LinkedList<OnlineTxn>();
        onlineTxns.add(this.accountBook.getRootAccount().getDownloadedTxns().newTxn());
        this.iterator.transactionsImported(
                onlineTxns,
                null,
                null,
                null);
    }

    @Test
    public void testShowHelp() {
        this.iterator.showHelp();
    }

    @Test
    public void testRefreshAccounts() {
        this.iterator.refreshAccounts(-1);
    }

    @Test
    public void testUpdate() {
        for (WizardHandler.ExecutedAction action : WizardHandler.ExecutedAction.values()) {
            this.iterator.update(new Observable(), action);
        }
    }
}
