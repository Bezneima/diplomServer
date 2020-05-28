var brances;

class textLineClass {
    constructor() {
        this.lineNumber;
        this.text;
    }
}

if (getCookie("user") !== undefined) {
    showMain();
    $("#userNameLogin").text(getCookie("user"));
}

function getProjectBoxString(userName, brancName) {
    return "<div class=\"projectBox\"><div class=\"issuesInfoListBranchesImg\"><img src=\"./img/treeIcon.png\"></div><div class=\"issuesInfoListBranchesText\"><div class=\"issuesInfoListBranchesUserName\">" + userName + "</div><div class=\"issuesInfoListBranchesBrancesName\">" + brancName + "</div></div><div style=\"line-height: 33px;\">></div></div>";
}

function getCommitString(number, userName, text) {
    return "<div class=\"issuesInfoCommitsCommit\"><div class=\"issuesInfoCommitsCommitInfo\"><div class=\"issuesInfoCommitsCommitInfoID\">" + number + "</div><div class=\"issuesInfoCommitsCommitInfoName\">" + userName + "</div></div><div class=\"issuesInfoCommitsCommitText\">" + text + "</div></div>";
}

function getLines(text, color) {
    return "<div class=\"issuesInfoCommitsTextLine " + color + "\"><div class=\"issuesInfoCommitsTextLineText colorRed\">" + text + "</div></div>";
}

function showMain() {
    $("#all").html("<div class=\"align_center\" ><div class=\"align_center_to_left\"><div class=\"align_center_to_right\"><div class=\"mainTop\"><div class=\"mainTopLeft\"><div class=\"mainTopLeftImg\" style=\"\tpadding-left: 0px;\"><img src=\"./img/logo.png\"></div><div id=\"TopTasks\">Задачи</div><div id=\"TopProjects\">Пользователи</div><div id=\"TopIssues\">Запросы</div></div><div class=\"mainTopRight\"><div class=\"TOPlogoutButton\">Выйти</div><div id=\"userNameLogin\"></div></div></div></div></div></div>" +
        "<div class=\"align_center\"  style=\"background: white;border: 0px;\"><div class=\"align_center_to_left\"><div class=\"align_center_to_right\"><div class=\"issuesInfo\"><div class=\"issuesInfoList\"><div class=\"issuesInfoListBranchesHeader\">Ветки</div><div class=\"issuesInfoListBranchesContainer\"></div></div><div class=\"issuesInfoCommits\"><div class=\"issuesInfoCommitsHeader\">Коммиты</div></div><div class=\"issuesInfoCommitsText\"><div class='issuesInfoCommitsTextHeader'>Изменения</div></div></div></div></div></div>");

    getBranches();
}

function getBranches() {
    $.ajax({
        type: "GET",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        url: "http://localhost:8080/getBrancesJSON",
        processData: false,
        success: function (data) {
            brances = JSON.parse(data);
            showBranches();
        },
        error: function () {
            console.log("error");
        }
    });
}


function showBranches() {
    let branchesContainer = $(".issuesInfoListBranchesContainer");
    for (let i = 0; i < brances["branches"].length; i++) {

        branchesContainer.append(getProjectBoxString(brances["branches"][i]["commits"][0].userName, brances["branches"][i].name));
        $(branchesContainer.find(".projectBox").get(branchesContainer.find(".projectBox").length - 1)).on("click", function () {
            showCommits(brances["branches"][i]["commits"]);
        })
        //тут получил название
    }
    //issuesInfoListBranchesContainer
}

function showCommits(commitsArray) {
    let commitsContainer = $(".issuesInfoCommits");
    commitsContainer.html("<div class=\"issuesInfoCommitsHeader\">Коммиты</div>");
    for (let i = 0; i < commitsArray.length; i++) {
        commitsContainer.append(getCommitString(commitsArray[i].Id, commitsArray[i].userName, commitsArray[i].message));
        $(commitsContainer.find(".issuesInfoCommitsCommit").get(commitsContainer.find(".issuesInfoCommitsCommit").length - 1)).on("click", function () {
            showFileText(commitsArray[i].Id, commitsArray);
        })
    }
}

function showFileText(commitID, commitArray) {
    let textContainer = $(".issuesInfoCommitsText");
    textContainer.html("<div class=\"issuesInfoCommitsTextHeader\">Изменения</div>");

    commitArray = sortCommit(commitArray);

    let listOfFiles = [];
    for (let i = 0; i < commitArray.length; i++) {
        if (commitArray[i].Id === commitID)
            listOfFiles = makeListOfFile(commitArray[i]);
    }

    console.log(listOfFiles);
    // Так отсортировал все коммиты и имею имя файлов которые мы изменяли
    // Теперь нужно собрать все строчки и просто выставить последнее их состояние как строчку файла

    let listOfFilesText = [];
    for (let i = 0; i < listOfFiles.length; i++) {
        listOfFilesText.push(getAllFileLinesFromAllCommits(commitArray, listOfFiles[i], commitID));
    }


}

//Получает коммит и смотрит возвращает список имен файлов которые изменялись
function makeListOfFile(commit) {
    //Так епт
    let result = [];
    for (let i = 0; i < commit.fileText.length; i++) {
        if (!contains(result, commit.fileText[i].fileName))
            result.push(commit.fileText[i].fileName);
    }
    return result;
}

function contains(arr, elem) {
    return arr.indexOf(elem) != -1;
}

function sortCommit(a) {
    var swapp;
    var n = a.length - 1;
    var x = a;
    do {
        swapp = false;
        for (var i = 0; i < n; i++) {
            if (x[i].Id > x[i + 1].Id) {
                var temp = x[i];
                x[i] = x[i + 1];
                x[i + 1] = temp;
                swapp = true;
            }
        }
        n--;
    } while (swapp);
    return x;
}

function getAllFileLinesFromAllCommits(commitArray, fileName, commitID) {
    // commitArray - все коммиты;
    // fileName - имя проверяемоего файла;
    console.log(commitArray);
    let result = [];
    for (let i = 0; i < commitID-1; i++) {
        for (let j = 0; j < commitArray[i].fileText.length; j++) {
            if (commitArray[i].fileText[j].fileName === fileName) {
                {
                    result.push(commitArray[i].fileText[j]);
                }
            }
        }
    }

    console.log(result, fileName);
    console.log(confluenceFileTextFromLines(result, fileName));

// return confluenceFileTextFromLines();
}

function confluenceFileTextFromLines(fileLines, fileName) {
    // let textLine = new textLineClass();
    let allTextInFile = [];
    console.log(fileLines);
    for (let i = 0; i < fileLines.length; i++) {
        switch (fileLines[i].status) {
            case "ADDED":
                let textLine = new textLineClass();
                textLine.lineNumber = fileLines[i].lineNumber;
                textLine.text = fileLines[i].textOfChange;
                allTextInFile.push(textLine);
                break;
            case "CHANGED":
                for (let j = 0; j < allTextInFile.length; j++) {
                    if (allTextInFile[j].lineNumber === fileLines[i].lineNumber)
                        allTextInFile[j].text = fileLines[i].textOfChange;
                }
                break;
            case "REMOVED":
                for (let j = 0; j < allTextInFile.length; j++) {
                    if (allTextInFile[j].lineNumber === fileLines[i].lineNumber) {
                        allTextInFile = removeItemOnce(allTextInFile, fileLines[i].lineNumber);
                    }
                }
                break;

        }
    }
    return allTextInFile;
}

function removeItemOnce(arr, id) {

    let value;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i].lineNumber === id) {
            value = arr[i];
            break;
        }
    }

    var index = arr.indexOf(value);
    if (index > -1) {
        arr.splice(index, 1);
    }
    return arr;
}