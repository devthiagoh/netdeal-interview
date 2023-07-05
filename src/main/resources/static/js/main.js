document.getElementById("save").onclick = function(){
    
	var formvalue = document.getElementById('form');
  	var formdata = new FormData(formvalue);

	var json = parseToJson(formdata);

	requestSave(json);
};

function parseToJson(data){
	var object = {};
	data.forEach(function(value, key){
	   if(key === 'password')
	   	object[key] = btoa(value);
	   else
	   	object[key] = value;	
	});
	var json = JSON.stringify(object);
	//	console.log(json);
	return json;
};

function requestSave(json){
	var req = new XMLHttpRequest();
	req.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
       		window.location.href = 'list';			
	    }
	};
	req.open('POST', 'save');
	req.setRequestHeader('Content-Type', 'application/json');
	req.send(json);
};

function requestEdit(uri){
//	var req = new XMLHttpRequest();
//	req.onreadystatechange = function() {
//	    if (this.readyState == 4 && this.status == 200) {
//			console.log(this);
//       		window.location.href = 'list';			
//	    }
//	};
//	req.open('GET', uri);
//	req.send();
	fetch(uri, { method: "GET" })
	.then((response) => {
		console.log(response.ok);
		console.log(response.status);
//		$('#modal').find('.modal-body').html(data);
	})
	.catch((error) => {
	   console.error("Error:", error);
	});
}

function edit(element){
	const id = element.getAttribute("value");
	const uri = id+'/edit';
	requestEdit(uri);
};

function requestDelete(uri){
	var req = new XMLHttpRequest();
	req.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
       		window.location.href = 'list';			
	    }
	};
	req.open('DELETE', uri);
	req.send();
}

function remove(element){
	const id = element.getAttribute("value");
	const uri = id+'/delete';
	requestDelete(uri);
};

//document.getElementById("modal").on('show.bs.modal', function () {
//        $.get("/modals/modal1", function (data) {
//            $('#exampleModal1').find('.modal-body').html(data);
//        })
//    });