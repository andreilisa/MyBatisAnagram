import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;



public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            AnagramsMapper mapper = session.getMapper(AnagramsMapper.class);
            mapper.createFunction();
            mapper.createTable();
            mapper.write();
            for (Anagrams element : mapper.showAll())
            System.out.println(element.toString().replaceAll("\\[", " ")
                    .replaceAll(",", " ")
                    .replaceAll("]", " ").replaceAll("\\{", " ").replaceAll("\\}", " "));

        } finally {
            session.close();
        }
    }
}
