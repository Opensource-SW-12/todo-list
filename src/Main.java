public class Main {
    public static void main(String[] args) {
        TodoManager manager = new TodoManager("data/test-todo-2.json");

        manager.addTodo("오픈소스SW기초 과제 1", "2026-04-30");
        manager.addTodo("오픈소스SW기초 과제 2", "2026-04-27");
        manager.addTodo("오픈소스SW기초 과제 3", "2026-05-01");

        for (Todo todo : manager.getTodos()) {
            System.out.println(todo);
        }

        System.out.println("저장 상태 코드: " + manager.getStorageErrorCode());
    }
}