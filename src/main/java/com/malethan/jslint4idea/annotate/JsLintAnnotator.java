package com.malethan.jslint4idea.annotate;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.psi.PsiFile;
import com.malethan.jslint4idea.settings.JsLint4IntellijSettings;
import com.malethan.jslint4idea.settings.JsLint4IntellijSettingsListener;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class JsLintAnnotator extends ExternalAnnotator<PsiFile, JsLintIssueSet> {
    @SuppressWarnings({"FieldCanBeLocal"})
    private JsLintProcessor processor = new JsLintProcessor(JsLint4IntellijSettings.getInstance().getJslintCfg());

    public JsLintAnnotator() {
        // Listen to global settings changes.
        JsLint4IntellijSettings.getInstance().addListener(new JsLint4IntellijSettingsListener() {
            public void handleSettingsChanged(@NotNull final JsLint4IntellijSettings newSettings) {
                processor = new JsLintProcessor(newSettings.getJslintCfg());
            }
        });
    }

    @Override
    public PsiFile collectionInformation(@NotNull PsiFile file) {
        return file;
    }

    @Override
    public JsLintIssueSet doAnnotate(PsiFile file) {
        return processor.process(file.getName(), file.getText());
    }

    @Override
    public void apply(@NotNull PsiFile file, JsLintIssueSet result, @NotNull AnnotationHolder holder) {
        if (file.getName().equals(result.getFilename())) {
            for (JsLintIssueSet.JsLintIssue issue : result.getIssues()) {
                holder.createErrorAnnotation(issue.getRange(), issue.getReason());
            }
        }
    }
}
