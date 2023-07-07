document.getElementById("btnSave").onclick = function(){
    
	var formvalue = document.getElementById('newForm');
  	var formdata = new FormData(formvalue);

	var json = parseToJson(formdata, 'new');

	requestSave(json);
};

document.getElementById("btnEdit").onclick = function(){
    
	var formvalue = document.getElementById('editForm');
  	var formdata = new FormData(formvalue);

	var json = parseToJson(formdata, 'edit');

	requestSave(json);
};

function parseToJson(data, typeform){
	var object = {};
	data.forEach(function(value, key){
	   if(key === 'password')
	   	object[key] = btoa(value);
	   else if(key === 'collaborators'){
	 	let collaborators = typeform === 'new' ? $('#newSelect').val() : $('#editSelect').val();
		let objects = [];
		collaborators.forEach(function(v, k){
			let vl = v.split(',');
			let collaborator = {
				"id": vl[0] + "",
				"name": vl[1]
			}	   
			objects[k] = collaborator;
		});
	   	object[key] = objects;		   
	   } else
	   	object[key] = value;	
	});
	var json = JSON.stringify(object);
	//console.log(json);
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
	fetch(uri, { method: "GET" })
	.then((response) => {
		return response.json();  		
	})
	.then((responseJson) => {
	    console.log("Success:", responseJson);
	    $('.collaboratorId').val(responseJson.editCollaborator.id);
	    $('.collaboratorName').val(responseJson.editCollaborator.name);
	    let collaborators = responseJson.editCollaborator.collaborators;
	    let collaboratorsSelected = [];
	    
	    for (var i = 0; i < collaborators.length; i++) {
			console.log('collaborator: ' +collaborators[i].name);
			collaboratorsSelected[i] = collaborators[i].name.trim();
		}
		console.log('collaboratorsSelected: ' + JSON.stringify(collaboratorsSelected));
	    $('.selectpicker').selectpicker(JSON.parse(JSON.stringify(collaboratorsSelected)));
	    $('.selectpicker').selectpicker('render');	    
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

