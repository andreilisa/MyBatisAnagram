import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


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
            for (String element : mapper.showAll())
                System.out.println(element.replaceAll("\\[", " ")
                        .replaceAll(",", " ")
                        .replaceAll("]", " ").replaceAll("\\{", " ")
                        .replaceAll("\\}", " "));

        } finally {
            session.close();
        }
    }
}