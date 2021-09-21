import org.apache.ibatis.cursor.Cursor;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
public interface AnagramsMapper {

    Cursor<String> showAll();

    TreeSet<String> anagrams(String value);

    void createFunction();

    void createTable();

    List<String> write(String path);

}
