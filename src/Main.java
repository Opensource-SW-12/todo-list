public class Main {
    public static void main(String[] args) {
        TodoManager manager = new TodoManager();

        manager.addTodo("오픈소스SW기초 과제", "2026-04-30");
        manager.addTodo("GitHub PR 만들기", "2026-04-27");

        for (Todo todo : manager.getTodos()) {
            System.out.println(todo);
        }
    }
}