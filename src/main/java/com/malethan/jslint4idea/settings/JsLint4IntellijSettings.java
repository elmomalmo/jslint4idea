package com.malethan.jslint4idea.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
 */
@State(
        name = "JsLint4IntellijSettings",
        storages = @Storage(id = "other", file = "$APP_CONFIG$/jslint4intellij.xml")
)
public class JsLint4IntellijSettings implements PersistentStateComponent<Element> {

    public static final String DEFAULT_CFG = "browser,cap,bitwise,plusplus,fragment,on,sub,indent=4";

    protected Set<WeakReference<JsLint4IntellijSettingsListener>> listeners;

    private String jslintCfg = DEFAULT_CFG;

    public static JsLint4IntellijSettings getInstance() {
        return ServiceManager.getService(JsLint4IntellijSettings.class);
    }

    @Override
    public Element getState() {
        Element element = new Element("JsLint4IntellijSettings");
        element.setAttribute("jslintCfg", jslintCfg);
        return element;
    }

    @Override
    public void loadState(Element element) {
        this.jslintCfg = element.getAttributeValue("jslintCfg");
        notifyListeners();
    }

    public String getJslintCfg() {
        return jslintCfg;
    }

    public void setJslintCfg(String jslintCfg) {
        if (!this.jslintCfg.equals(jslintCfg)) {
            this.jslintCfg = jslintCfg;
            notifyListeners();
        }
    }

    /**
     * Add a listener to this settings object changes.
     *
     * @param listener the {@link JsLint4IntellijSettingsListener}.
     */
    public void addListener(@NotNull final JsLint4IntellijSettingsListener listener) {
        if (listeners == null) listeners = new HashSet<WeakReference<JsLint4IntellijSettingsListener>>();
        listeners.add(new WeakReference<JsLint4IntellijSettingsListener>(listener));
    }

    /**
     * Notify event listeners of changes.
     */
    protected void notifyListeners() {
        if (listeners != null)
            for (final WeakReference<JsLint4IntellijSettingsListener> listenerRef : listeners)
                if (listenerRef.get() != null) listenerRef.get().handleSettingsChanged(this);
    }
}
