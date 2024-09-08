// SupportRequest.java
public class SupportRequest {
    private String issueType;
    private String description;

    public SupportRequest(String issueType, String description) {
        this.issueType = issueType;
        this.description = description;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getDescription() {
        return description;
    }
}
