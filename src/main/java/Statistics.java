public class Statistics {

    private long exams;
    private long tareas;
    private long info;

    public Statistics() {
        this.exams = 0;
        this.tareas = 0;
        this.info = 0;
    }

    public long getExams() {
        return exams;
    }

    public long getTareas() {
        return tareas;
    }

    public long getInfo() {
        return info;
    }

    public void plusOneInfo() {
        info++;
    }

    public void plusOneExam() {
        exams++;
    }

    public void plusOneTarea() {
        tareas++;
    }
}
