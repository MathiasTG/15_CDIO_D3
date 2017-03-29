
function validateForm() {
        var un = document.loginform.usr.value;
        var pw = document.loginform.pword.value;
        var username = "username"; 
        var password = "password";
        if ((un == username) && (pw == password)) {
            return true;
        }
        else {
            alert ("Login was unsuccessful, please check your username and password");
            return false;
        }
  }
 

function goTo () {
	if (validateForm == true) 
		document.getElementById('login').style.display = "none";
		document.getElementById('admin').style.display = "block";
	

}

