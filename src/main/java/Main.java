import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class Main {

    public static void main(String[] args) throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        try (SqlSession session = sqlSessionFactory.openSession()) {

            AnagramsMapper mapper = session.getMapper(AnagramsMapper.class);
            mapper.createFunction();
            mapper.createTable();
            mapper.write();

            Cursor<Anagrams> cursor = session.getMapper(AnagramsMapper.class).showAll();
            for (Anagrams element : cursor)
                System.out.println(element.toString().replaceAll("\\[", " ")
                        .replaceAll(",", " ")
                        .replaceAll("]", " ").replaceAll("\\{", " ")
                        .replaceAll("\\}", " "));

        }
    }
}