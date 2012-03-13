package com.malethan.jslint4idea.annotate;

import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.googlecode.jslint4java.JSLintResult;
import com.googlecode.jslint4java.Option;

import java.util.Arrays;
import java.util.List;

/**
* @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
*/
public class JsLintProcessor {
    private JSLint lint;

    public JsLintProcessor(String jsLingCfg) {
        this.lint = new JSLintBuilder().fromDefault();

        updateJsLintOptions(jsLingCfg);
    }

    public void updateJsLintOptions(String jslintCfg) {
        this.lint.resetOptions();

        List<String> options = Arrays.asList(jslintCfg.split(","));
        for (Option o : Option.values()) {
            if(o.getType() == Boolean.class) {
                this.lint.addOption(o, "false");
            }
            for (String cfgOpt : options) {
                if(cfgOpt.startsWith(o.name().toLowerCase())) {
                    if(o.getType() == Boolean.class) {
                        this.lint.addOption(o);
                    } else if(o.getType() == Integer.class) {
                        String[] pair = cfgOpt.split("=");
                        this.lint.addOption(o, pair[1]);
                    }
                }
            }
        }
    }

    public JsLintIssueSet process(String filename, String text) {

        if (filename.endsWith(".js")) {

            JSLintResult result = lint.lint(filename, text);

            return new JsLintIssueSet(filename, result, text);

        } else {
            return new JsLintIssueSet();
        }

    }
}
