package com.malethan.jslint4idea;

import javax.swing.*;

/**
 * @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
 */
public class JsLint4IntellijCfg {
    private JTextField jslintCfgField;
    private JLabel jslintCfgLabel;
    private JPanel root;

    public void setData(JsLint4Intellij data) {
        jslintCfgField.setText(data.getJslintCfg());
    }

    public void getData(JsLint4Intellij data) {
        data.setJslintCfg(jslintCfgField.getText());
    }

    public boolean isModified(JsLint4Intellij data) {
        if (jslintCfgField.getText() != null ? !jslintCfgField.getText().equals(data.getJslintCfg()) : data.getJslintCfg() != null)
            return true;
        return false;
    }

    public JPanel getRoot() {
        return root;
    }
}
