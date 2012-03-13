package com.malethan.jslint4idea.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
 */
public class JsLint4IntellijSettingsConfigurable implements SearchableConfigurable {
    protected JsLint4IntellijSettings settings;

    private JsLint4IntellijCfg cfgForm;

    public JsLint4IntellijSettingsConfigurable() {
        settings = JsLint4IntellijSettings.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return "JsLint4IntelliJ";
    }

    @Override
    public Runnable enableSearch(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "JsLint 4 IntelliJ";
    }

    @Override
    public Icon getIcon() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getHelpTopic() {
        return getId();
    }

    @Override
    public JComponent createComponent() {
        if (cfgForm == null) {
            cfgForm = new JsLint4IntellijCfg();
        }
        return cfgForm.getRoot();
    }

    @Override
    public boolean isModified() {
        return cfgForm == null || !settings.getJslintCfg().equals(cfgForm.getJslintCfg());
    }

    @Override
    public void apply() throws ConfigurationException {
        if(cfgForm != null) {
            settings.setJslintCfg(cfgForm.getJslintCfg());
        }
    }

    @Override
    public void reset() {
        if(cfgForm != null) {
            cfgForm.setJslintCfg(JsLint4IntellijSettings.DEFAULT_CFG);
        }
    }

    @Override
    public void disposeUIResources() {
        cfgForm = null;
    }
}
