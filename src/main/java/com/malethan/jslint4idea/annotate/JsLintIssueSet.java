package com.malethan.jslint4idea.annotate;

import com.googlecode.jslint4java.Issue;
import com.googlecode.jslint4java.JSLintResult;
import com.intellij.openapi.util.TextRange;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Elwyn Malethan &lt;emalethan@specificmedia.com&gt;
 */
public class JsLintIssueSet {

    private Set<JsLintIssue> issues = new HashSet<JsLintIssue>();
    private String filename;

    public JsLintIssueSet(String filename, JSLintResult result, String text) {

        this.filename = filename;

        final List<String> textRows = Arrays.asList(text.split("\n"));
        for (Issue issue : result.getIssues()) {
            int start = convertLineColToGlobalCharIndex(textRows, issue);

            issues.add(new JsLintIssueSet.JsLintIssue(new TextRange(start-1, start+1), issue.getReason()));
        }
    }

    public JsLintIssueSet() {
    }

    public String getFilename() {
        return filename;
    }

    public Set<JsLintIssue> getIssues() {
        return issues;
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

    public static class JsLintIssue {


        private TextRange range;
        private String reason;

        public JsLintIssue(TextRange range, String reason) {
            //To change body of created methods use File | Settings | File Templates.
            this.range = range;
            this.reason = reason;
        }

        public TextRange getRange() {
            return range;
        }

        public void setRange(TextRange range) {
            this.range = range;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
