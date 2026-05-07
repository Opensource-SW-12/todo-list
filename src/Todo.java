public class Todo {
    private String title;
    private boolean done;
    private String dueDate;

    public Todo(String title, boolean done, String dueDate) {
        this.title = title;
        this.done = done;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", done=" + done +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}