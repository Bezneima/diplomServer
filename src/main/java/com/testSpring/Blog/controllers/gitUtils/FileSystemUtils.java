package com.testSpring.Blog.controllers.gitUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testSpring.Blog.controllers.gitUtils.pojos.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileSystemUtils {

    public static class SendingFile {
        public String fileName;
        public String path;
        public String text;
    }

    public static void makePathToFile(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();
    }


    public static void changeOrAddFileToBranch(String hash, String userName, String branchName, String pathToUserBranch, String path, String fileName, String comment, MultipartFile file) {
        Branches branches = Branches.getInstance();
        Branch branch = branches.findBranch(branchName);
        // Я получил ветку или создал ее (Ссылка)
        Commit commit = new Commit();
        commit.message = comment;
        commit.time = Long.toString(System.currentTimeMillis());
        commit.userName = userName;
        commit.hash = hash;
        // Все теперь у меня есть коммит в ветке
        // Я должен записать хранится ли это файлом или метаданными
        // Записываю файл полностью и сравниваю с хранимым файлом прошлой версии в коммит возвращаю разницу по строчкам
        try {
            commit.fileText = giveMetaDataFromBranch(file, pathToUserBranch, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Ну все, дальше чисто коммит сохранить и все
        commit.Id = getNextCommitIdInBranch(branchName);
        branch.commits.add(commit);
        checkSimilarCommitsInBranch(branchName, hash);
        writeFile(getCurrentWorkedPath() + "\\CommitHistory.st", makeJSONString(branches));
        writeMultipartFile(file, pathToUserBranch + path);

    }

    //Тк эта фигня полюбому потом будет на потоках, то по красоте трогать ток свои данные и поэтому заранее берем хеш
    private static void checkSimilarCommitsInBranch(String branchName, String hash) {
        Branches branches = Branches.getInstance();
        Branch branch = branches.findBranch(branchName);

        Commit combinedСommit = new Commit();
        combinedСommit.hash = hash;

        // Тут вначале соберем коммиты в 1;
        for (Commit commit : branch.commits) {
            if (commit.hash.equals(hash)) {
                combinedСommit.message = commit.message;
                combinedСommit.time = commit.time;
                combinedСommit.userName = commit.userName;
                combinedСommit.fileText.addAll(commit.fileText);
            }
        }

        branch.commits.removeIf(commit -> commit.hash.equals(hash));


        combinedСommit.Id = getNextCommitIdInBranch(branchName);
        branch.commits.add(combinedСommit);
    }

    public static String makeJSONString(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    static void writeFile(String path, String text) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println("Не могу записать в файл" + path + " информацию");
            System.out.println(ex.getMessage());
        }
    }

    private static void writeMultipartFile(MultipartFile file, String path) {
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(path)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            System.out.println("Вам не удалось загрузить " + path + " => " + e.getMessage());
        }
    }

    public static Branch getOrAddNewBranch(String name) {
        Branches branches = Branches.getInstance();
        for (int i = 0; i < branches.branches.size(); i++) {
            if (branches.branches.get(i).name.equals(name))
                return branches.branches.get(i);
        }
        return new Branch(name);
    }

    public static int getNextCommitIdInBranch(String branchName) {
        Branches branches = Branches.getInstance();
        for (int i = 0; i < branches.branches.size(); i++) {
            if (branches.branches.get(i).name.equals(branchName)) {
                return branches.branches.get(i).commits.size() + 1;
            }
        }
        return 0;
    }

    static String getCurrentWorkedPath() {
        return new File("").getAbsolutePath();
    }

    //TODO Слишком криво переписать, иначе гг;
    private static List<FilesChangePojo> giveMetaDataFromBranch(MultipartFile file, String pathToUserBranch, String path) throws IOException {
        List<FilesChangePojo> outputResult = new ArrayList<>();
        File fileInServer = new File(pathToUserBranch + "\\" + path);
        List<FilesChangePojo> savedFileLines = new ArrayList<>();
        AtomicInteger saveidLineId = new AtomicInteger();
        if (fileInServer.exists()) {//Я должен разбить его по линиям
            Files.lines(Paths.get(fileInServer.getPath()), StandardCharsets.UTF_8).forEach(line -> {
                savedFileLines.add(new FilesChangePojo(saveidLineId.getAndIncrement(), line, file.getName(), pathToUserBranch + "\\" + path));
            });
        }
        // Считал хранимый файл на сервере
        BufferedReader br;
        List<FilesChangePojo> inputFileLines = new ArrayList<>();
        saveidLineId.set(0);
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                inputFileLines.add(new FilesChangePojo(saveidLineId.getAndIncrement(), line, file.getName(), pathToUserBranch + "\\" + path));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Считал новый файл и вроде все не так уж и плохо
        // Так вот так вот как-то, каряво, но время ппц жмет
        // Поитому тут если файл был меньше то так иначе добаваить строчки с пометкой удалил
        if (inputFileLines.size() > savedFileLines.size()) {
            for (int i = 0; i < inputFileLines.size(); i++) {
                if (savedFileLines.size() > i && savedFileLines.get(i) != null) {//Если в старом файле есть такая строчка
                    if (!savedFileLines.get(i).textOfChange.equals(inputFileLines.get(i).textOfChange)) {
                        inputFileLines.get(i).status = LineStatus.CHANGED;
                        outputResult.add(inputFileLines.get(i));
                    }
                } else {
                    inputFileLines.get(i).status = LineStatus.ADDED;
                    outputResult.add(inputFileLines.get(i));
                }
            }
        } else {//TODO Вот это рил калич перепиши если успеешь
            for (int i = 0; i < savedFileLines.size(); i++) {
                if (inputFileLines.size() > i && inputFileLines.get(i) != null) {//Если в новом файле есть такая строчка
                    if (!savedFileLines.get(i).textOfChange.equals(inputFileLines.get(i).textOfChange)) {
                        inputFileLines.get(i).status = LineStatus.CHANGED;
                        outputResult.add(inputFileLines.get(i));
                    }
                } else {
                    savedFileLines.get(i).status = LineStatus.REMOVED;
                    outputResult.add(savedFileLines.get(i));
                }
            }
        }

        return outputResult;
    }

    public static void loadHistorySettings() {
        StringBuilder SettingsFileText = new StringBuilder();
        try (FileInputStream fin = new FileInputStream("C:\\Users\\Bezne\\Desktop\\Blog\\CommitHistory.st")) {
            int i = -1;
            while ((i = fin.read()) != -1) {

                SettingsFileText.append((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Gson gson = new Gson();
        Branches branches = Branches.getInstance();
        try {
            branches.branches = gson.fromJson(SettingsFileText.toString(), Branches.class).branches;
        } catch (Exception e) {
            System.out.println("Первая попытка");
        }
    }

    public static String readFile(String path) {
        StringBuilder result = new StringBuilder();
        try (FileInputStream fin = new FileInputStream(path)) {
            int i = -1;
            while ((i = fin.read()) != -1) {

                result.append((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return result.toString();
    }

    public static List<SendingFile> listFilesForFolder(final File folder) {
        List<SendingFile> allFiles = new ArrayList<>();
        Branches branches = Branches.getInstance();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                // щас в allFiles все файлы, что он добавил ранее;
                allFiles.addAll(listFilesForFolder(fileEntry));
            } else {
                SendingFile newfile = new SendingFile();
                newfile.fileName = fileEntry.getName();
                newfile.text = readFile(fileEntry.getPath());
                newfile.path = fileEntry.getPath();
                allFiles.add(newfile);
                //System.out.println(fileEntry.getName());
                //System.out.println(fileEntry.getPath().replace(getCurrentWorkedPath(), ""));
            }
        }
        return allFiles;
    }

}
