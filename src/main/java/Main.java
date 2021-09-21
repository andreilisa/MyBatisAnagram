import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sqlSessionFactory.openSession()) {

            AnagramsMapper mapper = session.getMapper(AnagramsMapper.class);
            mapper.createFunction();
            mapper.createTable();
            try (Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\andrei.lisa\\IdeaProjects\\qwe\\folder\\"))) {
                List<String> result = walk.filter(Files::isRegularFile)
                        .map(Path::toString)
                        .filter(s -> s.endsWith(".txt")).collect(Collectors.toList());
                for (int i = 0; i < result.size(); i++)
                    mapper.write(result.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
            AnagramsMapper anagramsMapper = session.getMapper(AnagramsMapper.class);
            Cursor<String> anagrams = anagramsMapper.showAll();
            for (String anagram : anagrams) {
                TreeSet<String> anagramsByValue = anagramsMapper.anagrams(anagram);
                if (anagramsByValue.size() > 1) {
                    System.out.println(anagramsByValue.toString().replaceAll("\\[", " ")
                            .replaceAll(",", " ")
                            .replaceAll("]", " ").replaceAll("\\{", " ")
                            .replaceAll("\\}", " "));

                }
            }

        }
    }
}