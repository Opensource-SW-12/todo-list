import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TodoStorage {
    private String fileName;
    private int lastErrorCode;

    public TodoStorage(String fileName) {
        this.fileName = fileName;
        this.lastErrorCode = StorageResult.SUCCESS;
    }

    public int getLastErrorCode() {
        return lastErrorCode;
    }

    public int save(ArrayList<Todo> todos) {
        try {
            File file = new File(fileName);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            FileWriter writer = new FileWriter(fileName);

            writer.write("[\n");

            for (int i = 0; i < todos.size(); i++) {
                Todo todo = todos.get(i);

                writer.write("  {\n");
                writer.write("    \"title\": \"" + todo.getTitle() + "\",\n");
                writer.write("    \"done\": " + todo.isDone() + ",\n");
                writer.write("    \"dueDate\": \"" + todo.getDueDate() + "\"\n");
                writer.write("  }");

                if (i != todos.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]");
            writer.close();

            lastErrorCode = StorageResult.SUCCESS;
            return StorageResult.SUCCESS;
        } catch (IOException e) {
            lastErrorCode = StorageResult.SAVE_ERROR;
            return StorageResult.SAVE_ERROR;
        }
    }

    public ArrayList<Todo> load() {
        ArrayList<Todo> todos = new ArrayList<>();

        File file = new File(fileName);

        if (!file.exists()) {
            lastErrorCode = StorageResult.FILE_NOT_FOUND;
            return todos;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String title = "";
            boolean done = false;
            String dueDate = "";
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("\"title\"")) {
                    title = getStringValue(line);
                } else if (line.startsWith("\"done\"")) {
                    done = getBooleanValue(line);
                } else if (line.startsWith("\"dueDate\"")) {
                    dueDate = getStringValue(line);
                } else if (line.startsWith("}")) {
                    todos.add(new Todo(title, done, dueDate));
                }
            }

            reader.close();
            lastErrorCode = StorageResult.SUCCESS;
        } catch (IOException e) {
            lastErrorCode = StorageResult.LOAD_ERROR;
        }

        return todos;
    }

    private String getStringValue(String line) {
        int start = line.indexOf(":") + 1;
        String value = line.substring(start).trim();

        value = value.replace(",", "");
        value = value.replace("\"", "");

        return value;
    }

    private boolean getBooleanValue(String line) {
        int start = line.indexOf(":") + 1;
        String value = line.substring(start).trim();

        value = value.replace(",", "");

        return Boolean.parseBoolean(value);
    }
}