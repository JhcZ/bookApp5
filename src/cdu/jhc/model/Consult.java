package cdu.jhc.model;

//图书咨询实体类
public class Consult {
    private int id;
    private int bookId;  //要咨询的图书id
    private String question;  //咨询问题
    private long askTime;  //咨询时间
    private String consultingUser;  //咨询用户，可以为空
    private String reply;  //回复
    private long reTime;  //回复时间
    private AdminUser adminUser;  //回复的管理员
    private ConsultStatus reStatus;  //回复状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getAskTime() {
        return askTime;
    }

    public void setAskTime(long askTime) {
        this.askTime = askTime;
    }

    public String getConsultingUser() {
        return consultingUser;
    }

    public void setConsultingUser(String consultingUser) {
        this.consultingUser = consultingUser;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public long getReTime() {
        return reTime;
    }

    public void setReTime(long reTime) {
        this.reTime = reTime;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public ConsultStatus getReStatus() {
        return reStatus;
    }

    public void setReStatus(ConsultStatus reStatus) {
        this.reStatus = reStatus;
    }

    public void setReStatus(String statusName) {
        if(statusName == null || statusName.equals("")){
            setReStatus(ConsultStatus.ALL);
            return;
        }
        switch (statusName){
            case "已回复":
                setReStatus(ConsultStatus.DONE);
                break;
            default:  //未回复
                setReStatus(ConsultStatus.NOREPLY);
        }
    }
}
