// PayPal Importer for Moneydance - https://www.my-flow.com/paypalimporter/
// Copyright (C) 2013-2021 Florian J. Breunig. All rights reserved.

package com.moneydance.modules.features.paypalimporter.presentation;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * @author Florian J. Breunig
 */
final class PTextField extends JTextField {

    private static final long serialVersionUID = 1L;

    PTextField(final String promptText) {
        super(promptText);

        this.setToolTipText(promptText);

        addFocusListener(new FocusListener() {
            @Override
            public void focusLost(final FocusEvent event) {
                if (getText().isEmpty()) {
                    setText(promptText);
                }
            }

            @Override
            public void focusGained(final FocusEvent event) {
                if (getText().equals(promptText)) {
                    setText("");
                }
            }
        });
    }
}
