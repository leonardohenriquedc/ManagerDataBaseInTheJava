package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Getter
@Setter
public class ReadFiles {

    List<Pessoa> pessoas = null;

    public List<Pessoa> readFiles(String doc) throws Exception{

        String jsonString = new String(Files.readAllBytes(Paths.get(doc)));

        Gson gson = new Gson();

        Type type = new TypeToken<List<Pessoa>>(){}.getType();

        this.setPessoas(gson.fromJson(jsonString, type));

        return this.getPessoas();
    }
}
