import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    private final List<Todo> todos;

    public TodoManager() {
        todos = new ArrayList<>();
    }

    public void addTodo(String title, String dueDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("할 일 제목은 비어 있을 수 없습니다.");
        }

        Todo todo = new Todo(title.trim(), false, dueDate);
        todos.add(todo);
    }

    public List<Todo> getTodos() {
        return new ArrayList<>(todos);
    }
}