public class Activity {
    public String activityName;
    private boolean isDone = false;

    public Activity(String activityName) {
        this.activityName = activityName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + activityName;
        }
        return "[ ] " + activityName;
    }
}
