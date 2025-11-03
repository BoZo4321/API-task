package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojoClass.Board;

import java.io.File;
import java.io.IOException;

public class SaveBoardData {
    public static void save(String id, String name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Board data = new Board(id, name);
        mapper.writeValue(new File("board.json"), data);
    }
}

