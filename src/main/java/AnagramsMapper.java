import org.apache.ibatis.cursor.Cursor;

public interface AnagramsMapper {

    Cursor<Anagrams> showAll();

    void createFunction();

    void createTable();

    void write();

}
