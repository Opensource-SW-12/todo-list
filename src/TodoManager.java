import java.util.ArrayList;

public class TodoManager {
    private ArrayList<Todo> todos;
    private TodoStorage storage;

    public TodoManager(String fileName) {
        storage = new TodoStorage(fileName);
        todos = storage.load();

        if (storage.getLastErrorCode() == StorageResult.FILE_NOT_FOUND) {
            System.out.println("저장된 파일이 없어 새로 시작합니다.");
        }
    }

    public void addTodo(String title, String dueDate) {
        if (title == null || title.trim().equals("")) {
            System.out.println("할 일 제목은 비어 있을 수 없습니다.");
            return;
        }

        Todo todo = new Todo(title, false, dueDate);
        todos.add(todo);

        int result = storage.save(todos);

        if (result == StorageResult.SAVE_ERROR) {
            System.out.println("파일 저장에 실패했습니다.");
        }
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public int getStorageErrorCode() {
        return storage.getLastErrorCode();
    }
}