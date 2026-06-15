package br.com.minitrello.data;

import br.com.minitrello.classes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonDatabase {

    private static final String FILE_PATH = "database.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<User> loadUsers() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> users = gson.fromJson(reader, listType);

            return users != null ? users : new ArrayList<>();

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar no banco de dados: " + e.getMessage());
        }
    }

}