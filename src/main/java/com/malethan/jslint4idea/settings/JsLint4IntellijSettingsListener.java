package com.malethan.jslint4idea.settings;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;

/**
 * @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
 */
public interface JsLint4IntellijSettingsListener extends EventListener {

    /**
     * Handle a settings change event.
     *
     * @param newSettings the updated {@link JsLint4IntellijSettings} object.
     */
    void handleSettingsChanged(@NotNull final JsLint4IntellijSettings newSettings);
}