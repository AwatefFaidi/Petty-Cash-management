// Create a Component object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

if (!this.Component) {
	this.Component = {};
}

(function() {
	var componentContextPath = location.protocol + '//' + location.host
			+ location.pathname;

	var createEditForm = $("#componentsForm");

	$(document).ready(function() {

		createEditForm.validate({
			submitHandler : function(form) {
				createUpdateComponent(form);
			}
		});

	});

	function createUpdateComponent(el) {

		var componentSeries = getComponentSeriesFormData();

		$.ajax({
			method : $(el).attr('method'),
			url : $(el).attr('action'),
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify(componentSeries),
			success : function(data) {
				onSaveSuccess(data);
			},
			error : function(xhr, error) {
				onError(xhr, error);
			}
		});
	}

	function getComponentSeriesFormData() {

		var values = [];
		$('.spocNames').each(function() {
			values.push({
				name : this.name,
				value : this.value
			});
		});

		var array = [];

		if (values.length > 0) {

			for (var i = 0; i < values.length / 2; i++) {

				var component = {
					pid : null,
					name : null,
					alias : null,
					description : null,
				};

				$.each(values, function(j, val) {
					if (val.name == ("car[" + i + "][name]")) {
						component.name = val.value;
					}
					if (val.name == ("car[" + i + "][description]")) {
						component.description = val.value;
					}
				});
				if (component.name != "") {
					array.push(component);
				}
			}
		}
		return array;
	}

	function onSaveSuccess(result) {
		// reloading page to see the updated data
		window.location = componentContextPath;
	}

	function onDeleteSuccess(result) {
		// reloading page to see the updated data
		window.location = componentContextPath;
	}

	Component.closeModalPopup = function(el) {
		el.modal('hide');
	}

	function resetForm() {
		$('.alert').hide();
		createEditForm.trigger("reset"); // clear form fields
		createEditForm.validate().resetForm(); // clear validation messages
		createEditForm.attr('method', 'POST'); // set default method
		componentModel.pid = null; // reset component model;
	}

	function addErrorAlert(message, key, data) {
		$(".alert > p").html(message);
		$('.alert').show();
	}

	function onError(httpResponse, exception) {
		var i;
		switch (httpResponse.status) {
		// connection refused, server not reachable
		case 0:
			addErrorAlert('Server not reachable', 'error.server.not.reachable');
			break;
		case 400:
			var errorHeader = httpResponse
					.getResponseHeader('X-octopusWebApp-error');
			var entityKey = httpResponse
					.getResponseHeader('X-octopusWebApp-params');
			var entityHeaderMessage = httpResponse
					.getResponseHeader('X-octopusWebApp-message');
			if (errorHeader) {
				var entityName = entityKey;
				addErrorAlert(entityHeaderMessage, errorHeader, {
					entityName : entityName
				});
			} else if (httpResponse.responseText) {
				var data = JSON.parse(httpResponse.responseText);
				if (data && data.fieldErrors) {
					for (i = 0; i < data.fieldErrors.length; i++) {
						var fieldError = data.fieldErrors[i];
						var convertedField = fieldError.field.replace(
								/\[\d*\]/g, '[]');
						var fieldName = convertedField.charAt(0).toUpperCase()
								+ convertedField.slice(1);
						addErrorAlert(
								'Field ' + fieldName + ' cannot be empty',
								'error.' + fieldError.message, {
									fieldName : fieldName
								});
					}
				} else if (data && data.message) {
					addErrorAlert(data.message, data.message, data);
				} else {
					addErrorAlert(data);
				}
			} else {
				addErrorAlert(exception);
			}
			break;
		default:
			if (httpResponse.responseText) {
				var data = JSON.parse(httpResponse.responseText);
				if (data && data.description) {
					addErrorAlert(data.description);
				} else if (data && data.message) {
					addErrorAlert(data.message);
				} else {
					addErrorAlert(data);
				}
			} else {
				addErrorAlert(exception);
			}
		}
	}

})();
