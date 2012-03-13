package com.malethan.jslint4idea.settings;

import javax.swing.*;

public class JsLint4IntellijCfg {
    private JTextField jslintCfgField;
    private JLabel jslintCfgLabel;
    private JPanel root;

    public String getJslintCfg() {
        return jslintCfgField.getText();
    }

    public void setJslintCfg(String data) {
        jslintCfgField.setText(data);
    }

    public JPanel getRoot() {
        return root;
    }
}
