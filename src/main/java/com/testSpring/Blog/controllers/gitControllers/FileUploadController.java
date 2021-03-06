package com.testSpring.Blog.controllers.gitControllers;


import com.testSpring.Blog.controllers.gitUtils.FileSystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.SendingFile;
import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.changeOrAddFileToBranch;
import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.listFilesForFolder;
import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.loadHistorySettings;
import static com.testSpring.Blog.controllers.gitUtils.FileSystemUtils.makeJSONString;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value = "/getFilesFromBranch", method = RequestMethod.POST)
    public @ResponseBody
    String getBranch(@RequestParam("userName") String userName,
                     @RequestParam("branch") String branch) throws IOException {
        loadHistorySettings();
        String pathToUserFolder = new File("").getAbsolutePath() + "\\users\\" + userName;
        //Создаю тут папку под пользователя
        if (!new File(pathToUserFolder).exists()) {
            return "No user started";
        }
        String pathToUserBranch = pathToUserFolder + "\\" + branch;
        //Папка под пользователя точно уже есть
        if (!new File(pathToUserBranch).exists()) {
            return "No branch started";
        }

        String resultJSON;
        List<SendingFile> sendingFiles = listFilesForFolder(new File(pathToUserBranch));
        for (SendingFile sendingFile : sendingFiles) {
            sendingFile.path = sendingFile.path.replace(pathToUserFolder + "\\" + branch, "");
        }
        return makeJSONString(sendingFiles);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("nameOfFile") String nameOfFile,
                            @RequestParam("userName") String userName,
                            @RequestParam("branch") String branch,
                            @RequestParam("path") String path,
                            @RequestParam("file") MultipartFile file,
                            @RequestParam("comment") String comment,
                            @RequestParam("hash") String hash) throws IOException {
        loadHistorySettings();
        String pathToUserFolder = new File("").getAbsolutePath() + "\\users\\" + userName;
        //Создаю тут папку под пользователя
        if (!new File(pathToUserFolder).exists()) {
            Files.createDirectory(Paths.get(pathToUserFolder));
        }
        String pathToUserBranch = pathToUserFolder + "\\" + branch;
        //Папка под пользователя точно уже есть
        if (!new File(pathToUserBranch).exists()) {
            Files.createDirectory(Paths.get(pathToUserBranch));
        }

        //тут уже мы находимся в ветке которую пользователь использует сейчас
        FileSystemUtils.makePathToFile(pathToUserBranch + path);

        if (!file.isEmpty()) {
            changeOrAddFileToBranch(hash, userName, branch, pathToUserBranch, path, nameOfFile, comment, file);
            return "файлы успешно загружены";
        } else {
            return "Вам не удалось загрузить " + path + " потому что файл пустой.";
        }
    }

}