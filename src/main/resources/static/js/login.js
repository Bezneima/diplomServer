if (getCookie("user") === undefined) {
    showLoginMenu();
    giveLoginFun();
}


function showLoginMenu() {
    $("#all").html(`<div class="loginForm">
		<div class="loginTop">
			<div>
				<img src="./img/logo.png">
			</div>
			<div style="padding-top: 23px;">
				Система контроля версий от МГТУ «Станкин»<br>Дипломная работа <b>Кучер Павела Андреевича</b>
			</div>
		</div>
		<div class="loginMain">
			<div class="loginMainHeader">
				Войдите в систему
			</div>
			<div class="loginMainInputs">

				<div>
					<input type="text" name="login" id="login" placeholder="Логин">
				</div>
				<div>
					<input type="password" name="password" id="password" placeholder="Пароль">
				</div>
			</div>	

		</div>
		<div class="loginFooter">
			<div class="loginFooterButton">
				Войти
			</div>
		</div>

	</div>`);
}


function giveLoginFun() {
    $(".loginFooterButton").on("click", function () {
        let login = $("#login").val();
        $.ajax({
            type: "GET",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            url: "http://localhost:8080/authorization?login=" + login + "&password=" + $("#password").val(),
            processData: false,
            success: function (data) {
                if (data === "true") {
                    document.cookie = "user=" + login;
                } else {
                    alert("Вы ввели не верный логин или пароль");
                }
            },
            error: function () {
                console.log("error");
            }
        });
    });
}