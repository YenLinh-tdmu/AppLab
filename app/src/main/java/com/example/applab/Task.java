package com.example.applab;

public class Task {
    private String taskName;
    private String content;
    private String date;
    private String time;

    public Task(String taskName, String content, String date, String time) {
        this.taskName = taskName;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getTaskName() {
        return taskName;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    // Overriding toString() để hiển thị trong ListView (Không cần thiết nếu sử dụng Custom Adapter)
    @Override
    public String toString() {
        return "Công việc: " + taskName + "\nNội dung: " + content + "\nNgày HT: " + date + "\nGiờ HT: " + time;
    }
}
