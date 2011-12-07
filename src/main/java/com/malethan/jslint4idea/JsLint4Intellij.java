package com.malethan.jslint4idea;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class JsLint4Intellij implements ApplicationComponent, Configurable, JDOMExternalizable {
    public String jslintCfg = "browser,cap,bitwise,plusplus,fragment,on,sub,indent=4";

    private JsLint4IntellijCfg cfgForm;

    public JsLint4Intellij() {
    }

    public void initComponent() {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                    }
                }

        );
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "JsLint4IntelliJ";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "JsLint 4 IntelliJ";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getHelpTopic() {
        return null;
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
        return cfgForm != null && cfgForm.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        if (cfgForm != null) {
            // Get data from form to component
            cfgForm.getData(this);
        }
    }

    @Override
    public void reset() {
        if (cfgForm != null) {
            // Reset form data from component
            cfgForm.setData(this);
        }
    }

    @Override
    public void disposeUIResources() {
        cfgForm = null;
    }

    public String getJslintCfg() {
        return jslintCfg;
    }

    public void setJslintCfg(final String jslintCfg) {
        this.jslintCfg = jslintCfg;
    }

    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this, element);
    }

    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }
}
