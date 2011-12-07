package com.malethan.jslint4idea;

import com.googlecode.jslint4java.*;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;

import java.util.Arrays;
import java.util.List;

public class JsLintAnnotator implements ExternalAnnotator {
    private JSLint lint;
    @SuppressWarnings({"FieldCanBeLocal"})
    private String lintCfg = "-=!! NOT VALID CFG !!=-";

    public JsLintAnnotator() {
        this.lint = new JSLintBuilder().fromDefault();
    }

    @Override
    public void annotate(PsiFile psiFile, AnnotationHolder annotationHolder) {
        if(!psiFile.getName().endsWith(".js")) {
            return;
        }

        Application application = ApplicationManager.getApplication();

        JsLint4Intellij component = application.getComponent(JsLint4Intellij.class);
        if(!component.jslintCfg.equals(this.lintCfg)) {
            updateJsLintOptions(component.jslintCfg);
        }


        final String text = psiFile.getText();

        final List<String> textRows = Arrays.asList(text.split("\n"));

        JSLintResult result = lint.lint(psiFile.getName(), text);

        for (Issue issue : result.getIssues()) {
            int start = convertLineColToGlobalCharIndex(textRows, issue);

            annotationHolder.createErrorAnnotation(new TextRange(start-1, start+1), issue.getReason());
        }

    }

    private int convertLineColToGlobalCharIndex(List<String> textRows, Issue issue) {
        int start = 0;

        int counter = 0;
        for (String textRow : textRows) {
            counter +=1;
            if(counter < issue.getLine()) {
                start += textRow.length() +1;
            } else if(counter == issue.getLine()) {
                start += issue.getCharacter();
            } else {
                break;
            }
        }
        return start;
    }

    private void updateJsLintOptions(String jslintCfg) {
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
}
