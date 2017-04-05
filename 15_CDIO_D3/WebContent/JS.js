
function validateForm() {
        var un = document.loginform.usr.value;
        var pw = document.loginform.pword.value;
        var username = "username"; 
        var password = "password";
        if ((un == username) && (pw == password)) {
        	document.getElementById('login').style.display = "none";
    		document.getElementById('admin').style.display = "";
        }
        else {
            alert ("Login was unsuccessful, please check your username and password");
            return false;
        }
  }

function handleSubmit() {
	getElementsByClassName()
	
	
}


function objectifyForm(formArray) {//serialize data function

	  var returnArray = {};
	  for (var i = 0; i < formArray.length; i++){
	    returnArray[formArray[i]['name']] = formArray[i]['value'];
	  }
	  return returnArray;
	}

var formData = JSON.stringify($("#loginform").serializeArray());

