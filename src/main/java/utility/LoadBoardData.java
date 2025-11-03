package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojoClass.Board;

import java.io.File;
import java.io.IOException;

public class LoadBoardData {

    public static Board load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("board.json"), Board.class);
    }
}
