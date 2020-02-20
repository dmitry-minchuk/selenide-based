package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.internal.Base64;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class MongoDbClient {
    final static File folder = new File("D:\\Repositories\\selenide-based\\allure-results");

    public static List<Document> convertFilesToDocuments(final File folder) {
        List<Document> dlist = new ArrayList<>();
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.getName().endsWith(".json")) {
                String content = null;
                try {
                    content = FileUtils.readFileToString(fileEntry, Charset.defaultCharset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dlist.add(new Document(Base64.encode(fileEntry.getName().getBytes()), content));
            } else if (fileEntry.getName().endsWith(".png")) {
                try {
                    dlist.add(new Document(Base64.encode(fileEntry.getName().getBytes()), Files.readAllBytes(fileEntry.toPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dlist;
    }

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        mongoClient.listDatabaseNames().forEach((Consumer<? super String>) System.out::println);

        List<Document> documents = convertFilesToDocuments(folder);
        mongoClient.getDatabase("mydb").getCollection("test").insertMany(documents);
    }
}
