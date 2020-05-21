package com.testSpring.Blog.controllers.gitUtils.pojos;

import java.util.ArrayList;
import java.util.List;

public class Commit {
    public Integer Id;
    public String hash;//Чисто id по которому можно искать одинаковые файлы во время залива и много всего.;
    public String userName;
    public String time;
    public List<FilesChangePojo> fileText = new ArrayList<>();//Каждый элемент массива - это файл
    public String message;
}
