(function() {

	document.getElementById("btnSave").onclick = function(){
	    
		let formvalue = document.getElementById('newForm');
	  	let formdata = new FormData(formvalue);
		let json = parseToJson(formdata, 'new');
		requestSave(json);
	};
	
	document.getElementById("btnEdit").onclick = function(){
	    
		let formvalue = document.getElementById('editForm');
	  	let formdata = new FormData(formvalue);
		let json = parseToJson(formdata, 'edit');
		requestSave(json);
	};
})();

function removeReadonly(element){
	element.removeAttribute('readonly');
	
	$('.collaboratorPsswd').val('');
	$('.editCollaboratorPsswd').val('');
}

function parseToJson(data, typeform){
		
	let object = {};
	if (typeform !== 'validate') {
		data.forEach(function(value, key) {
			if (key === 'password')
				object[key] = btoa(value);
			else if (key === 'collaborators') {
				let collaborators = typeform === 'new' ? $('#newSelect').val() : $('#editSelect').val();
				let objects = [];
				collaborators.forEach(function(v, k) {
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
	} else {
		console.log(data)
		let value = $(data).val();
		object['password'] = btoa(value);
	}
	let json = JSON.stringify(object);
	//console.log(json);
	return json;
};

function requestSave(json){
	
	let req = new XMLHttpRequest();
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
	    
	    if(responseJson != null){
			
		    $('.editCollaboratorId').val(responseJson.editCollaborator.id);
		    $('.editCollaboratorName').val(responseJson.editCollaborator.name);
		    let collaborators = responseJson.editCollaborator.collaborators;
		    let collaboratorsSelected = [];
		    
		    for (let i = 0; i < collaborators?.length; i++) {
				console.log('collaborator: ' +collaborators[i].name);
				collaboratorsSelected[i] = collaborators[i].name;
			}
			//console.log('collaboratorsSelected: ' + JSON.stringify(collaboratorsSelected));   
			$('.editCollaboratorHierarchy').val(JSON.stringify(collaboratorsSelected));   
		}
 
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
	let req = new XMLHttpRequest();
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

function validatePsswd(formid){
	
	console.log(formid);
	let jsonParsed = parseToJson(formid, 'validate');
	console.log(jsonParsed);
	
	let json = JSON.parse(jsonParsed);
	console.log(json.password);
	
	if(json.password){
		console.log('validate...');
		let uri = json.password+'/validate';
		
		fetch( uri, { method: "GET" })
		.then((response) => {
			return response.json();  		
		})
		.then((responseJson) => {
		    console.log("Success:", responseJson);
		    
		    if(responseJson != null){
				$('.collaboratorComplexity').val(responseJson.complexity);	
			}
	 
		})
		.catch((error) => {
		   console.error("Error:", error);
		});
	}
}

