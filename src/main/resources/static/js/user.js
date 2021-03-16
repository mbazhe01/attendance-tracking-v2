/***
// get all buttons and remove spinners
function clearSpinners() {
	var elements = document.getElementsByClassName("btn-outline-primary");
	for(var x=0; x < elements.length; x++)
	{
		if (elements[x].innerHTML.includes("spinner"))
			alert(elements[x].innerHTML.toString());
			//elements[x].innerHTML.slide(-3);
		}
	}
}
***/

function readURL(input, idNum) {
    if (input.files && input.files[0]) {
        let reader = new FileReader();

        reader.onload = function (e) {
            $("#imgPreview" + idNum).attr("src", e.target.result).width(200);
        }
        
		console.log("Image file: " + input.files[0].name);

        reader.readAsDataURL(input.files[0]);
    }
}


function deleteElement(elId) {
	console.log('Element Id: ' + elId);
	document.getElementById(elId).remove();

}

function deletePhone(elId) {
	console.log('Element Id: ' + elId);
	var phoneNumber = document.getElementById("pnum" + elId).value;
	
	if (confirm("Do you want to delete phone number " + phoneNumber + " ?") == true) {
		document.getElementById(elId).remove();
	} else {
		console.log("Cancelled delete: " + phoneNumber);
	}

}

function deleteAddress(elId) {
	console.log('Element Id: ' + elId);
	var address = document.getElementById("addr" + elId).value;
	if (address==null)
		address="";
	
	if (confirm("Do you want to delete address " + address + " ?") == true) {
		document.getElementById(elId).remove();
	} else {
		console.log("Cancelled delete: " + address);
	}

}

function deleteNote(elId) {
	console.log('Element Id: ' + elId);
	var note = document.getElementById("note" + elId).value;
	if (note==null)
		note="";
	
	if (confirm("Do you want to delete note " + "''"+ note + "''" + " ?") == true) {
		document.getElementById(elId).remove();
	} else {
		console.log("Cancelled delete: " + note);
	}

}


function scrollToSection(elId) {

	console.log(elId);
	if(elId!="Top")
		document.getElementById(elId).scrollIntoView({ block: 'start', behavior: 'smooth' });
				

}

function hideSaveMassage() {
	document.getElementById("message").style.display = "none";
}

function hideErrMsg(id) {
	obj = document.getElementById(id);

	obj.style.display = 'none';
	document.getElementById("message").style.display = "none";
}

function changeButtonText() {

	document.getElementById("SaveButton").innerHTML =
		'<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Saving...';
}

function showButtonSpinner(elementId) {

	var month = elementId.substring(0, 3);
	document.getElementById(elementId).innerHTML =
		'<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> ' + month;
}

function showButtonSpinnerGenerate(elementId) {
	var month = elementId.substring(0, 3);
	document.getElementById(elementId).innerHTML =
		'<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Generating... ';
}

function changeDeleteButtonText() {
	if (!(confirm('Are you sure you want to delete attendance records for this date?'))) return false;
	document.getElementById("DeleteButton").innerHTML =
		'<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Deleting Records...';
}

function validateVacationYears() {
	var elements = document.getElementByName("vacationYear");

	for (var i = 0; i < elements.length; i++) {
		var tmp = elements[i].value;
	}

	return false;
}

function clearSpinners() {
	var elements = document.getElementsByClassName("btn-outline-primary");
	for (var x = 0; x < elements.length; x++) {
		alert(elements[x].innerHTML.toString().trim());
		return;

	}
}


