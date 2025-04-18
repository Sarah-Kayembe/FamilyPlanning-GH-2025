public class MonthlyVisit {
    private int id;
    private int clientId;
    private String visitDate;
    private String visitMonth;
    private String commodity;
    private int unitsIssued;
    private boolean referred;
    private boolean expectedToReturn;
    private String remarks;

    public MonthlyVisit() {}

    public MonthlyVisit(int clientId, String visitDate, String visitMonth, String commodity, int unitsIssued, boolean referred, boolean expectedToReturn, String remarks) {
        this.clientId = clientId;
        this.visitDate = visitDate;
        this.visitMonth = visitMonth;
        this.commodity = commodity;
        this.unitsIssued = unitsIssued;
        this.referred = referred;
        this.expectedToReturn = expectedToReturn;
        this.remarks = remarks;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
    public String getVisitMonth() { return visitMonth; }
    public void setVisitMonth(String visitMonth) { this.visitMonth = visitMonth; }
    public String getCommodity() { return commodity; }
    public void setCommodity(String commodity) { this.commodity = commodity; }
    public int getUnitsIssued() { return unitsIssued; }
    public void setUnitsIssued(int unitsIssued) { this.unitsIssued = unitsIssued; }
    public boolean isReferred() { return referred; }
    public void setReferred(boolean referred) { this.referred = referred; }
    public boolean isExpectedToReturn() { return expectedToReturn; }
    public void setExpectedToReturn(boolean expectedToReturn) { this.expectedToReturn = expectedToReturn; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
