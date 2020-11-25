package Entities;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ToDo {
    @Id
    Long id;
    String description;
    int priority;
    boolean completed;
    LocalDate dueDate;

    protected ToDo(){

    }

    public ToDo(Long id, String description, int priority, boolean completed, LocalDate dueDate) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                ", dueDate=" + dueDate +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id &&
                priority == toDo.priority &&
                completed == toDo.completed &&
                description.equals(toDo.description) &&
                dueDate.equals(toDo.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, completed, dueDate);
    }

}
